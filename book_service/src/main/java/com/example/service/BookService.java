package com.example.service;

import com.example.client.AuthorServiceClient;
import com.example.domain.Book;
import com.example.dto.AuthorDTO;
import com.example.dto.BookWithAuthorDTO;
import com.example.repository.BookRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;


public class BookService {

    @Inject
    @RestClient
    AuthorServiceClient authorServiceClient;

    @Inject
    BookRepository bookRepository;

    public List<Book> listAll() {
        return bookRepository.listAll();
    }

    @Transactional
    public Book createBook(Book author) {
        bookRepository.persist(author);
        return author;
    }

    @Transactional
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        Optional<Book> bookOptional = bookRepository.findByIdOptional(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(updatedBook.getTitle());
            book.setIsbn(updatedBook.getIsbn());
            book.setAuthorId(updatedBook.getAuthorId());
            book.setPublishedDate(updatedBook.getPublishedDate());
            return Optional.of(book);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deleteBook(Long id) {
        return bookRepository.deleteById(id);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findByIdOptional(id);
    }

    @Transactional
    public BookWithAuthorDTO getBookWithAuthor(Long bookId) {
        // Recherche du livre
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new NotFoundException("Book with id " + bookId + " not found");
        }

        // Recherche de l'auteur via le client REST
        AuthorDTO author = authorServiceClient.getAuthorById(book.getAuthorId());
        if (author == null) {
            throw new NotFoundException("Author with id " + book.getAuthorId() + " not found");
        }

        // Assemblage des données
        return new BookWithAuthorDTO(book, author);
    }

    public Book findBookById(Long bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new NotFoundException("Book not found with id: " + bookId);
        }
        return book;
    }
}
