package jsl.com.library.entities;

import jakarta.persistence.*;
import jsl.com.library.entities.utils.Address;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Embedded
    private Address address;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private int enabled = 1;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_book",
            joinColumns = @JoinColumn( name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn( name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.getLibraryUsers().add(this);
    }

    public String fullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public LibraryUser firstname(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public LibraryUser lastname(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public LibraryUser address(Address address) {
        this.setAddress(address);
        return this;
    }

    public LibraryUser email(String username) {
        this.setUsername(username);
        return this;
    }

    public LibraryUser password(String password) {
        this.setPassword(password);
        return this;
    }

    public LibraryUser isEnabled(boolean isEnabled) {
        var enabled = isEnabled ? 1 : 0;
        this.setEnabled(enabled);
        return this;
    }

    public static LibraryUser init() { return new LibraryUser(); }
}
