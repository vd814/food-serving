package com.swing.foodserving.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.RequestReviewAndRating;

public interface ReviewAndRatingDao {
	
	
	public void saveReviewAndRating(RequestReviewAndRating rr);
	public RequestReviewAndRating getReviewByID(int reviewID);
	public List getAllCompetedRequestWithDatatable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber);
}
