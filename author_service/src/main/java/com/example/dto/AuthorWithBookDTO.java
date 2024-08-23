package com.example.dto;

import com.example.domain.Author;


public class AuthorWithBookDTO {
    private Author author;
    private BookDTO bookDTO;

    public AuthorWithBookDTO(Author author, BookDTO bookDTO) {
        this.author = author;
        this.bookDTO = bookDTO;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }
}