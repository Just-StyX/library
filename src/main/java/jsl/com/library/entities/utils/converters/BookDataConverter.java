package jsl.com.library.entities.utils.converters;

import jsl.com.library.entities.Book;
import jsl.com.library.entities.utils.Author;
import jsl.com.library.entities.utils.dto.BookAuthorDTO;
import jsl.com.library.entities.utils.dto.BookDTO;

public class BookDataConverter {
    public static Book convertDtoToBook(BookDTO bookDTO) {
        var author = new Author(bookDTO.authorName(), bookDTO.authorEmail());
        return Book.init()
                .fileExtension(bookDTO.fileExtension())
                .isbn(bookDTO.isbn())
                .author(author)
                .category(bookDTO.category())
                .pages(bookDTO.pages())
                .quantity(bookDTO.quantity())
                .version(bookDTO.version())
                .title(bookDTO.title());
    }

    public static BookAuthorDTO convertBookToDto(Book book) {
        return new BookAuthorDTO(
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getNumOfPages(),
                book.getCategory(),
                book.getVersion()
        );
    }
}
