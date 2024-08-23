package com.example.repository;

import com.example.domain.Borrow;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BorrowRepository implements PanacheRepository<Borrow> {
    // PanacheRepository provides basic CRUD methods

    public List<Borrow> findByUserId(Long userId) {
        return list("userId", userId);
    }
}