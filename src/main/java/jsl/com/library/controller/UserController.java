package jsl.com.library.controller;

import jsl.com.library.entities.LibraryUser;
import jsl.com.library.entities.utils.converters.UserDataConverter;
import jsl.com.library.entities.utils.dto.LibraryUserDTO;
import jsl.com.library.entities.utils.dto.UserDTO;
import jsl.com.library.service.LibraryUserService;
import jsl.com.library.userdetails.LibraryUserDetails;
import jsl.com.library.userdetails.LibraryUserDetailsManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final LibraryUserDetailsManager libraryUserDetailsManager;
    private final LibraryUserService libraryUserService;

    public UserController(LibraryUserDetailsManager libraryUserDetailsManager, LibraryUserService libraryUserService) {
        this.libraryUserDetailsManager = libraryUserDetailsManager;
        this.libraryUserService = libraryUserService;
    }

    @GetMapping
    private ResponseEntity<List<LibraryUserDTO>> getUser() throws InterruptedException {
        return ResponseEntity.ok(libraryUserService.findAll());
    }

    @PostMapping
    private ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        LibraryUser libraryUser = UserDataConverter.convertDtoToUser(userDTO);
        libraryUserDetailsManager.createUser(new LibraryUserDetails(libraryUser));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{bookId}")
    private ResponseEntity<Void> borrowBook(Authentication authentication, @PathVariable String bookId) {
        libraryUserService.borrowBook(authentication, bookId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{bookId}")
    private ResponseEntity<Void> submitBook(Authentication authentication, @PathVariable String bookId) {
        libraryUserService.submitBook(authentication, bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
