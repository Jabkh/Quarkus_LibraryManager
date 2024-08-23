package com.example.dto;

import com.example.domain.Book;

public class BookWithAuthorDTO {
    private Book book;
    private AuthorDTO author;

    public BookWithAuthorDTO(Book book, AuthorDTO author) {
        this.book = book;
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}