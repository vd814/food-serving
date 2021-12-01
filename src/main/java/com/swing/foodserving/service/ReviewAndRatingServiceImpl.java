package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.dao.ReviewAndRatingDao;
import com.swing.foodserving.entity.RequestReviewAndRating;

@Service
@Transactional
public class ReviewAndRatingServiceImpl implements ReviewAndRatingService{
	
	@Autowired
	ReviewAndRatingDao reviewAndRatingDao;

	@Override
	public void addReviewAndRating(int requestID, String review, float rating) {
		RequestReviewAndRating rr = new RequestReviewAndRating();
		rr.setRequestID(requestID);
		rr.setReview(review);
		rr.setRating(rating);
		reviewAndRatingDao.saveReviewAndRating(rr);
	}

	@Override
	public RequestReviewAndRating getReviewByID(int reviewID) {
		return reviewAndRatingDao.getReviewByID(reviewID);
	}

	@Override
	public void updateReviewAndRating(int reviewID, String review, float rating) {
		RequestReviewAndRating rr = reviewAndRatingDao.getReviewByID(reviewID);
		rr.setRating(rating);
		rr.setReview(review);
		reviewAndRatingDao.saveReviewAndRating(rr);
	}
	
	@Override
	public List getAllCompetedRequestWithDatatable(int paginationStart, int paginationEnd, HttpSession hs,
			String searchValue, String sortingValue, String columeNumber) {
		return reviewAndRatingDao.getAllCompetedRequestWithDatatable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
	}

}
