package com.example.service;

import com.example.client.BookServiceClient;
import com.example.client.UserServiceClient;
import com.example.domain.Borrow;
import com.example.dto.UserDTO;
import com.example.repository.BorrowRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.util.List;

public class BorrowService {

    @Inject
    BorrowRepository borrowRepository;

    @Inject
    @RestClient
    UserServiceClient userServiceClient;

    @Inject
    @RestClient
    BookServiceClient bookServiceClient;

    @GET
    public List<Borrow> getBorrows() {
        return borrowRepository.listAll();
    }

    @Transactional
    public Borrow createBorrow(Long userId, Long bookId) {
        // Check if user exists
        UserDTO user = userServiceClient.getAuthorById(userId);
        if (user == null) {
            throw new NotFoundException("User with id " + userId + " not found");
        }

        // Create and save borrow record
        Borrow borrow = new Borrow();
        borrow.setUserId(userId);
        borrow.setBookId(bookId);
        borrow.setBorrowDate(LocalDate.now());
        borrowRepository.persist(borrow);

        return borrow;
    }

    public List<Borrow> findBorrowsByUserId(Long userId) {
        return borrowRepository.findByUserId(userId);
    }

    public Borrow findBorrowById(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId);
        if (borrow == null) {
            throw new NotFoundException("Borrow record with id " + borrowId + " not found");
        }
        return borrow;
    }

    @Transactional
    public Borrow updateBorrow(Long borrowId, Borrow updatedBorrow) {
        Borrow borrow = borrowRepository.findById(borrowId);
        if (borrow == null) {
            throw new NotFoundException("Borrow record with id " + borrowId + " not found");
        }

        borrow.setUserId(updatedBorrow.getUserId());
        borrow.setBookId(updatedBorrow.getBookId());
        borrow.setBorrowDate(updatedBorrow.getBorrowDate());
        borrow.setReturnDate(updatedBorrow.getReturnDate());
        borrowRepository.persist(borrow);

        return borrow;
    }

    @Transactional
    public void deleteBorrow(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId);
        if (borrow == null) {
            throw new NotFoundException("Borrow record with id " + borrowId + " not found");
        }
        borrowRepository.delete(borrow);
    }

    @Transactional
    public Borrow returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId);
        if (borrow == null) {
            throw new NotFoundException("Borrow record with id " + borrowId + " not found");
        }

        borrow.setReturnDate(LocalDate.now());
        borrowRepository.persist(borrow);

        return borrow;
    }
}