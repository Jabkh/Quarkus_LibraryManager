package com.example.service;

import com.example.client.BookServiceClient;
import com.example.domain.Author;
import com.example.dto.AuthorWithBookDTO;
import com.example.dto.BookDTO;
import com.example.repository.AuthorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuthorService {

    @Inject
    AuthorRepository authorRepository;

    @Inject
    @RestClient
    BookServiceClient bookServiceClient;

    public List<Author> listAll() {
        return authorRepository.listAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findByIdOptional(id);
    }

    @Transactional
    public Author createAuthor(Author author) {
        authorRepository.persist(author);
        return author;
    }

    @Transactional
    public Optional<Author> updateAuthor(Long id, Author updatedAuthor) {
        Optional<Author> authorOptional = authorRepository.findByIdOptional(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(updatedAuthor.getName());
            author.setEmail(updatedAuthor.getEmail());
            author.setBio(updatedAuthor.getBio());
            return Optional.of(author);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deleteAuthor(Long id) {
        return authorRepository.deleteById(id);
    }

    public long countAuthors() {
        return authorRepository.count();
    }

    @Transactional
    public AuthorWithBookDTO getAuthorWithBook(Long authorId) {
        // Find the author
        Author author = authorRepository.findById(authorId);
        if (author == null) {
            throw new NotFoundException("Author with id " + authorId + " not found");
        }

        // Find the book by author ID using RestClient
        BookDTO bookDTO = bookServiceClient.getBookByAuthorId(authorId);
        if (bookDTO == null) {
            throw new NotFoundException("Book not found for author with id " + authorId);
        }

        // Assemble the data
        return new AuthorWithBookDTO(author, bookDTO);
    }
}