package com.example.repository;

import com.example.domain.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {
    // PanacheRepository provides basic CRUD methods

    public Book findByIsbn(String isbn) {
        return find("isbn", isbn).firstResult();
    }
}