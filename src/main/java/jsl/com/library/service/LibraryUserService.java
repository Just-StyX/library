package jsl.com.library.service;

import jsl.com.library.entities.Authority;
import jsl.com.library.entities.BookUsers;
import jsl.com.library.entities.LibraryUser;
import jsl.com.library.entities.UserUtilities;
import jsl.com.library.entities.utils.converters.BookDataConverter;
import jsl.com.library.entities.utils.converters.UserDataConverter;
import jsl.com.library.entities.utils.dto.BookAuthorDTO;
import jsl.com.library.entities.utils.dto.LibraryUserDTO;
import jsl.com.library.exception.BookNotAvailableException;
import jsl.com.library.repository.BookRepository;
import jsl.com.library.repository.BookUsersRepository;
import jsl.com.library.repository.LibraryRepository;
import jsl.com.library.repository.UserUtilitiesRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LibraryUserService {
    private final LibraryRepository userLibraryRepository;
    private final BookRepository bookLibraryRepository;
    private final BookUsersRepository bookUsersRepository;
    private final UserUtilitiesRepository userUtilitiesRepository;

    public LibraryUserService(LibraryRepository userLibraryRepository, BookRepository bookLibraryRepository,
                              BookUsersRepository bookUsersLibraryRepository, UserUtilitiesRepository userUtilitiesRepository) {
        this.userLibraryRepository = userLibraryRepository;
        this.bookLibraryRepository = bookLibraryRepository;
        this.bookUsersRepository = bookUsersLibraryRepository;
        this.userUtilitiesRepository = userUtilitiesRepository;
    }

    public List<BookAuthorDTO> findAllBooksByUser(String userId) {
        var user = userLibraryRepository.findById(userId);
        if (user.isPresent()) {
            var foundUser = user.get();
            return foundUser.getBooks().stream().map(BookDataConverter::convertBookToDto).toList();
        }
        return null;
    }

    public LibraryUserDTO findById(String libraryUserId) {
        return UserDataConverter.convertUserToDto(Objects.requireNonNull(userLibraryRepository.findById(libraryUserId).orElse(null)));
    }

    @Cacheable("users")
    public List<LibraryUserDTO> findAll() {
        return userLibraryRepository.findAll().stream().map(UserDataConverter::convertUserToDto).toList();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void save(LibraryUser user) {
        userLibraryRepository.save(user);
    }

    public List<BookAuthorDTO> findCompletedBooks(String userId) {
        var userUtilities = userUtilitiesRepository.findByUserId(userId);
        return userUtilities.stream().filter(UserUtilities::isCompleted)
                .map(userUtilities1 -> bookLibraryRepository.findById(userUtilities1.getBookId()).orElse(null)).filter(Objects::nonNull)
                .map(BookDataConverter::convertBookToDto).toList();
    }

    public List<BookAuthorDTO> findInCompletedBooks(String userId) {
        var userUtilities = userUtilitiesRepository.findByUserId(userId);
        return userUtilities.stream().filter(userUtilities1 -> !userUtilities1.isCompleted())
                .map(userUtilities1 -> bookLibraryRepository.findById(userUtilities1.getBookId()).orElse(null)).filter(Objects::nonNull)
                .map(BookDataConverter::convertBookToDto).toList();
    }

    public List<LibraryUserDTO> libraryUsers() {
        return userLibraryRepository.findAll().stream().map(UserDataConverter::convertUserToDto).toList();
    }

    public void borrowBook(Authentication authentication, String bookId) {
        var user = userLibraryRepository.findByUsername(authentication.getName());
        var book = bookLibraryRepository.findById(bookId);
        if (user.isPresent() && book.isPresent()) {
            var foundUser = user.get();
            var foundBook = book.get();
            if (foundBook.isAvailable()) {
                var authority = new Authority();
                authority.addUser(foundUser);
                authority.setAuthority(foundBook.getIsbn());
                foundBook.addLibraryUser(foundUser);
                foundBook.reduceByOne();
                bookLibraryRepository.save(foundBook);
                var bookUsers = BookUsers.init()
                        .userId(foundUser.getId())
                        .bookId(bookId)
                        .borrowedDate()
                        .submitted(false);
                var userUtility = UserUtilities.init()
                        .isCompleted(false)
                        .userId(foundUser.getId())
                        .bookId(bookId).currentPage(0).isSubmitted(false).isStarted(true);
                bookUsersRepository.save(bookUsers);
                userUtilitiesRepository.save(userUtility);
            } else {
                throw new BookNotAvailableException("Book not available");
            }
        }
    }

    public void submitBook(Authentication authentication, String bookId) {
        var user = userLibraryRepository.findByUsername(authentication.getName()).orElse(null);
        assert user != null;
        var bookUsersUpdate = bookUsersRepository.findByUserIdAndBookId(user.getId(), bookId).submitted(true).submittedDate();
        var userUtilityUpdate = userUtilitiesRepository.findByUserIdAndBookId(user.getId(), bookId).isSubmitted(true).isCompleted(true).isStarted(false);
        bookUsersRepository.save(bookUsersUpdate);
        userUtilitiesRepository.save(userUtilityUpdate);
    }
}
