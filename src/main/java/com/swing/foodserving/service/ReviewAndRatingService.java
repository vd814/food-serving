package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.RequestReviewAndRating;

public interface ReviewAndRatingService {
	public void addReviewAndRating(int requestID, String review, float rating);
	public RequestReviewAndRating getReviewByID(int reviewID);
	public void updateReviewAndRating(int reviewID,String review, float rating);
	public List getAllCompetedRequestWithDatatable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber);
}
