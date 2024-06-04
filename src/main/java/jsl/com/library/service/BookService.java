package jsl.com.library.service;

import jsl.com.library.entities.Book;
import jsl.com.library.entities.utils.converters.BookDataConverter;
import jsl.com.library.entities.utils.dto.BookAuthorDTO;
import jsl.com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public BookAuthorDTO findById(String bookId) {
        return BookDataConverter.convertBookToDto(Objects.requireNonNull(bookRepository.findById(bookId).orElse(null)));
    }

    /*
     * TODO check if this works
     */
    public List<BookAuthorDTO> findAllCompletedBooksByUser(String userId) {
        return bookRepository.findAllCompletedBooksByUser(userId).stream().map(BookDataConverter::convertBookToDto).toList();
    }
}
