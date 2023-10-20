package com.mathsly.animelibrary.domain.dto;

import com.mathsly.animelibrary.domain.entities.User;


public class UserDto {
    private Long id;
    private LibraryDto library;

    public UserDto() {
        // Construtor padrão
    }

    public UserDto(User user) {
        this.id = user.getId();
        if (user.getLibrary() != null) {
            this.library = new LibraryDto(user.getLibrary());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        if (this.library != null) {
            user.setLibrary(this.library.toEntity());
        }
        return user;
    }
}
