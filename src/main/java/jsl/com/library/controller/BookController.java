package jsl.com.library.controller;

import jsl.com.library.entities.utils.converters.BookDataConverter;
import jsl.com.library.entities.utils.dto.BookDTO;
import jsl.com.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    private String getBoot() {
        return "the book";
    }

    @PostMapping
    private ResponseEntity<Void> createBook(@RequestBody BookDTO bookDTO, UriComponentsBuilder builder) {
        var book = bookService.save(BookDataConverter.convertDtoToBook(bookDTO));
        URI location = builder.path("/api/vi/books/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
