package jsl.com.library.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "authority", nullable = false)
    private String authority;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "authorities")
    private Set<LibraryUser> libraryUsers = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<LibraryUser> getLibraryUsers() {
        return libraryUsers;
    }

    public void setLibraryUsers(Set<LibraryUser> libraryUsers) {
        this.libraryUsers = libraryUsers;
    }

    public void addUser(LibraryUser libraryUser) {
        this.libraryUsers.add(libraryUser);
        libraryUser.getAuthorities().add(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Authority authority1 = (Authority) object;
        return Objects.equals(id, authority1.id) && Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id='" + id + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
