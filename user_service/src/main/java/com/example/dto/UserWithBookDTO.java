package com.example.dto;

import com.example.dto.BookDTO;
import com.example.domain.User;

public class UserWithBookDTO {
    private User user;
    private BookDTO bookDTO;

    public UserWithBookDTO(User user, BookDTO bookDTO) {
        this.user = user;
        this.bookDTO = bookDTO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }
}