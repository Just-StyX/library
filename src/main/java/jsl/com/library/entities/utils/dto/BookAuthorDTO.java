package jsl.com.library.entities.utils.dto;

import jsl.com.library.entities.utils.Author;
import jsl.com.library.entities.utils.Category;

public record BookAuthorDTO(
        String title,
        String isbn,
        Author author,
        int pages,
        Category category,
        String version
) {
}
