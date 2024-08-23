package com.example.repository;

import com.example.domain.Review;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ReviewRepository implements PanacheRepository<Review> {
    // PanacheRepository provides basic CRUD methods

    public List<Review> findByBookId(Long bookId) {
        return list("bookId", bookId);
    }
}