package com.example.service;

import com.example.client.BookServiceClient;
import com.example.domain.User;
import com.example.dto.UserWithBookDTO;
import com.example.dto.BookDTO;
import com.example.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    @RestClient
    BookServiceClient bookServiceClient;

    @Transactional
    public UserWithBookDTO getUserWithBook(Long userId) {
        // Fetch the user
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new NotFoundException("User with id " + userId + " not found");
        }

        // Fetch the book via the client REST
        BookDTO bookDTO = bookServiceClient.getBookById(user.getBookId());
        if (bookDTO == null) {
            throw new NotFoundException("Book with id " + user.getBookId() + " not found");
        }

        // Assemble the data
        return new UserWithBookDTO(user, bookDTO);
    }

    public User findUserById(Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        return user;
    }

    @Transactional
    public List<User> listAll() {
        return userRepository.listAll();
    }

    @Transactional
    public User createUser(User user) {
        userRepository.persist(user);
        return user;
    }

    @Transactional
    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> userOptional = userRepository.findByIdOptional(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setBookId(updatedUser.getBookId());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }
}