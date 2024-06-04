package jsl.com.library.entities.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Author {
    @Column(name = "author_name", nullable = false)
    private String name;
    @Column(name = "author_email", nullable = false)
    private String email;

    public Author() {}

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Author author = (Author) object;
        return Objects.equals(name, author.name) && Objects.equals(email, author.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
