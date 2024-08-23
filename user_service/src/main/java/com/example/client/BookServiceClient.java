package com.example.client;

import com.example.dto.BookDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface BookServiceClient {

    @GET
    @Path("/books/{id}")
    BookDTO getBookById(@PathParam("id") Long id);
}