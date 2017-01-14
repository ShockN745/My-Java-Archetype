package com.professionalbeginner.domain.review;

/**
 * @author Kempenich Florian
 */
public class IllegalReviewException extends RuntimeException{

    private Review review = Review.NULL;

    public IllegalReviewException(String message, Review review) {
        super(message);
        this.review = review;
    }

    public IllegalReviewException(String message) {
        super(message);
    }

    public Review getReview() {
        return review;
    }
}