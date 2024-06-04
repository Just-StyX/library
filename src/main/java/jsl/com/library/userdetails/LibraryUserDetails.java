package jsl.com.library.userdetails;

import jsl.com.library.entities.LibraryUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record LibraryUserDetails(LibraryUser libraryUser) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return libraryUser.getAuthorities()
                .stream()
                .map(authority -> (GrantedAuthority) () -> String.valueOf(authority))
                .toList();
    }

    @Override
    public String getPassword() {
        return libraryUser.getPassword();
    }

    @Override
    public String getUsername() {
        return libraryUser.getUsername();
    }
}
