package com.example.repository;

import com.example.domain.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthorRepository implements PanacheRepository<Author> {
    // PanacheRepository provides basic CRUD methods

    public Author findByEmail(String email) {
        return find("email", email).firstResult();
    }
}