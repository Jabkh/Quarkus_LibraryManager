package com.example.client;

import com.example.dto.AuthorDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient
public interface AuthorServiceClient {

    @GET
    @Path("/authors/{id}")
    AuthorDTO getAuthorById(@PathParam("id") Long id);
}
