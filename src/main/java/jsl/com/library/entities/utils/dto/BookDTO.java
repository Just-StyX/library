package jsl.com.library.entities.utils.dto;

import jsl.com.library.entities.utils.Category;

public record BookDTO(
        String title,
        String isbn,
        String authorName,
        String authorEmail,
        int pages,
        String fileExtension,
        Category category,
        String version,
        int quantity
) {
}
