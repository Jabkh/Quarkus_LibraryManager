package com.example.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Book extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @NotNull
    private Long authorId; // Stocke l'ID de l'auteur

    @NotNull
    private LocalDate publishedDate;

    public Book() {
    }

    public Book(@NotBlank String title, @NotBlank String isbn, @NotNull Long authorId, @NotNull LocalDate publishedDate) {
        this.title = title;
        this.isbn = isbn;
        this.authorId = authorId;
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public @NotBlank String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotBlank String isbn) {
        this.isbn = isbn;
    }

    public @NotNull Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(@NotNull Long authorId) {
        this.authorId = authorId;
    }

    public @NotNull LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(@NotNull LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
}
