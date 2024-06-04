package jsl.com.library.userdetails;

import jsl.com.library.entities.Authority;
import jsl.com.library.repository.LibraryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class LibraryUserDetailsManager implements UserDetailsManager {
    private final LibraryRepository libraryRepository;
    private final PasswordEncoder passwordEncoder;

    public LibraryUserDetailsManager(LibraryRepository libraryRepository, PasswordEncoder passwordEncoder) {
        this.libraryRepository = libraryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserDetails user) {
        var libraryPerson = (LibraryUserDetails) user;
        var libraryUser = libraryPerson.libraryUser();
        var authority = new Authority();
        authority.setAuthority("read");
        libraryUser.getAuthorities().add(authority);
        libraryUser.setPassword(passwordEncoder.encode(libraryUser.getPassword()));
        libraryRepository.save(libraryUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        var libraryPerson = (LibraryUserDetails) user;
        var libraryUser = libraryRepository.findByUsername(libraryPerson.getUsername());
        if (libraryUser.isPresent()) {
            var newLibraryUser = libraryPerson.libraryUser();
            var foundLibraryUser = libraryUser.get();
            foundLibraryUser.setFirstName(newLibraryUser.getFirstName());
            foundLibraryUser.setLastName(newLibraryUser.getLastName());
            foundLibraryUser.setUsername(newLibraryUser.getUsername());
            libraryRepository.save(foundLibraryUser);
        }
    }

    @Override
    public void deleteUser(String email) {
        libraryRepository.deleteByUsername(email);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        var libraryUser = libraryRepository.findByUsername(oldPassword);
        libraryUser.ifPresent(value -> {
            value.setPassword(newPassword);
            libraryRepository.save(value);
        });
    }

    @Override
    public boolean userExists(String email) {
        return libraryRepository.findByUsername(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var libraryUser = libraryRepository.findByUsername(email);
        return libraryUser.map(LibraryUserDetails::new).orElse(null);
    }
}
