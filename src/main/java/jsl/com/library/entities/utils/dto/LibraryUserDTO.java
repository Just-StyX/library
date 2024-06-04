package jsl.com.library.entities.utils.dto;

import jsl.com.library.entities.utils.Address;

import java.io.Serializable;

public record LibraryUserDTO(String id, String fullName, Address address, String username) implements Serializable {}
