package jsl.com.library.entities.utils.converters;

import jsl.com.library.entities.LibraryUser;
import jsl.com.library.entities.utils.Address;
import jsl.com.library.entities.utils.dto.LibraryUserDTO;
import jsl.com.library.entities.utils.dto.UserDTO;

public class UserDataConverter {
    public static LibraryUser convertDtoToUser(UserDTO userDTO) {
        var address = new Address(
                userDTO.phone(),
                userDTO.street(),
                userDTO.zip()
        );
        return LibraryUser.init()
                .firstname(userDTO.firstName())
                .lastname(userDTO.lastName())
                .address(address)
                .isEnabled(true)
                .password(userDTO.password())
                .email(userDTO.email());
    }

    public static LibraryUserDTO convertUserToDto(LibraryUser libraryUser) {
        return new LibraryUserDTO(
                libraryUser.getId() ,libraryUser.fullName(), libraryUser.getAddress(), libraryUser.getUsername()
        );
    }
}