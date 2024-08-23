package com.example.dto;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private Long bookId;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, Long bookId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}