package com.professionalbeginner.domain.core.book;

import com.professionalbeginner.domain.core.book.Review;

/**
 * @author Kempenich Florian
 */
public class IllegalReviewException extends RuntimeException {

    private Review review = Review.NULL;

    public IllegalReviewException(String message, Review review) {
        super(message);
        this.review = review;
    }

    public IllegalReviewException(String message) {
        super(message);
    }

    public IllegalReviewException(String message, Throwable cause) {
        super(message, cause);
    }

    public Review getReview() {
        return review;
    }
}
