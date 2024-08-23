package com.example.client;

import com.example.dto.UserDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient
public interface UserServiceClient {

    @GET
    @Path("/users/{id}")
    UserDTO getAuthorById(@PathParam("id") Long id);
}
