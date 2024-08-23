package com.example.web;

import com.example.domain.Book;
import com.example.dto.BookWithAuthorDTO;
import com.example.service.BookService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService bookService;

    @GET
    public List<Book> getAllBooks() {
        return bookService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Long id) {
        Optional<Book> book = Optional.ofNullable(bookService.findBookById(id));
        if (book.isPresent()) {
            return Response.ok(book.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}/with-author")
    public Response getBookWithAuthor(@PathParam("id") Long id) {
        try {
            BookWithAuthorDTO bookWithAuthor = bookService.getBookWithAuthor(id);
            return Response.ok(bookWithAuthor).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createBook(@Valid Book book) {
        Book createdBook = bookService.createBook(book);
        return Response.status(Response.Status.CREATED).entity(createdBook).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") Long id, @Valid Book book) {
        Optional<Book> updatedBook = bookService.updateBook(id, book);
        if (updatedBook.isPresent()) {
            return Response.ok(updatedBook.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}