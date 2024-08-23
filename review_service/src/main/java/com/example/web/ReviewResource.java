package com.example.web;

import com.example.domain.Review;
import com.example.service.ReviewService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {

    @Inject
    ReviewService reviewService;

    @GET
    public List<Review> getAllReviews() {
        return reviewService.getReviews();
    }

    @GET
    @Path("/book/{bookId}")
    public List<Review> getReviewsByBookId(@PathParam("bookId") Long bookId) {
        return reviewService.findReviewsByBookId(bookId);
    }

    @POST
    public Response createReview(@Valid Review review) {
        Review createdReview = reviewService.createReview(review.getUserId(), review.getBookId(), review.getReviewText(), review.getRating());
        return Response.status(Response.Status.CREATED).entity(createdReview).build();
    }

    @PUT
    @Path("/{reviewId}")
    public Response updateReview(@PathParam("reviewId") Long reviewId, @Valid Review review) {
        Review updatedReview = reviewService.updateReview(reviewId, review);
        return Response.ok(updatedReview).build();
    }

    @DELETE
    @Path("/{reviewId}")
    public Response deleteReview(@PathParam("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return Response.noContent().build();
    }
}