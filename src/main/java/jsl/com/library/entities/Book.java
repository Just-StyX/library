package jsl.com.library.entities;

import jakarta.persistence.*;
import jsl.com.library.entities.utils.Author;
import jsl.com.library.entities.utils.Category;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "library")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Embedded
    private Author author;

    @Column(name = "pages", nullable = false)
    private int numOfPages;

    @Column(name = "extension", nullable = false)
    private String fileExtension;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "quantity", nullable = false)
    private AtomicInteger quantityOnShelve = new AtomicInteger(0);

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "books")
    private Set<LibraryUser> libraryUsers = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public AtomicInteger getQuantityOnShelve() {
        return quantityOnShelve;
    }

    public void setQuantityOnShelve(AtomicInteger quantityOnShelve) {
        this.quantityOnShelve = quantityOnShelve;
    }

    public Set<LibraryUser> getLibraryUsers() {
        return libraryUsers;
    }

    public void setLibraryUsers(Set<LibraryUser> libraryUsers) {
        this.libraryUsers = libraryUsers;
    }

    public void addLibraryUser(LibraryUser libraryUser) {
        this.libraryUsers.add(libraryUser);
        libraryUser.getBooks().add(this);
    }

    public boolean isAvailable() {
        return this.quantityOnShelve.get() > 0;
    }

    public Book isbn(String isbn) {
        this.setIsbn(isbn);
        return this;
    }

    public Book title(String title) {
        this.setTitle(title);
        return this;
    }

    public Book author(Author author) {
        this.setAuthor(author);
        return this;
    }

    public Book pages(int numOfPages) {
        this.setNumOfPages(numOfPages);
        return this;
    }
     public Book fileExtension(String fileExtension) {
        this.setFileExtension(fileExtension);
        return this;
     }

     public Book category(Category category) {
        this.setCategory(category);
        return this;
     }

     public Book version(String version) {
        this.setVersion(version);
        return this;
     }

     public Book quantity(int quantity) {
        this.quantityOnShelve.addAndGet(quantity);
        return this;
     }

     public Book reduceByOne() {
        this.quantityOnShelve.decrementAndGet();
        return this;
     }

     public static Book init() { return new Book(); }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return numOfPages == book.numOfPages && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(fileExtension, book.fileExtension) && category == book.category && Objects.equals(version, book.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, numOfPages, fileExtension, category, version);
    }

    @Override
    public String toString() {
        return "Book{" +
                "version='" + version + '\'' +
                ", category=" + category +
                ", fileExtension='" + fileExtension + '\'' +
                ", numOfPages=" + numOfPages +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
