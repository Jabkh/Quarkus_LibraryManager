package com.example.web;

import com.example.domain.Author;
import com.example.dto.AuthorWithBookDTO;
import com.example.service.AuthorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @Inject
    AuthorService authorService;

    @GET
    public List<Author> getAllAuthors() {
        return authorService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") Long id) {
        return authorService.findById(id)
                .map(author -> Response.ok(author).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/{id}/with-book")
    public Response getAuthorWithBook(@PathParam("id") Long id) {
        try {
            AuthorWithBookDTO authorWithBook = authorService.getAuthorWithBook(id);
            return Response.ok(authorWithBook).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createAuthor(@Valid Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return Response.status(Response.Status.CREATED).entity(createdAuthor).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") Long id, @Valid Author author) {
        return authorService.updateAuthor(id, author)
                .map(updatedAuthor -> Response.ok(updatedAuthor).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") Long id) {
        boolean deleted = authorService.deleteAuthor(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}