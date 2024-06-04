package jsl.com.library.entities.utils.dto;

public record UserDTO(String firstName,
                      String lastName,
                      String phone,
                      String street,
                      String zip,
                      String email,
                      String password) {
}
