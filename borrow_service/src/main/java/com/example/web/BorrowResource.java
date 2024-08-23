package com.example.web;

import com.example.domain.Borrow;
import com.example.service.BorrowService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/borrows")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BorrowResource {

    @Inject
    BorrowService borrowService;


    @GET
    public List<Borrow> getAllBorrows() {
        return borrowService.getBorrows();
    }

    @GET
    @Path("/user/{userId}")
    public List<Borrow> getBorrowsByUserId(@PathParam("userId") Long userId) {
        return borrowService.findBorrowsByUserId(userId);
    }

    @POST
    public Response createBorrow(@Valid Borrow borrow) {
        Borrow createdBorrow = borrowService.createBorrow(borrow.getUserId(), borrow.getBookId());
        return Response.status(Response.Status.CREATED).entity(createdBorrow).build();
    }

    @PUT
    @Path("/return/{borrowId}")
    public Response returnBook(@PathParam("borrowId") Long borrowId) {
        Borrow updatedBorrow = borrowService.returnBook(borrowId);
        return Response.ok(updatedBorrow).build();
    }
}