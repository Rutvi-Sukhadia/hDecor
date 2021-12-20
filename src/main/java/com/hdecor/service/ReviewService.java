package com.hdecor.service;

import java.util.List;

import com.hdecor.model.Review;

public interface ReviewService {
	
	  public List<Review> getAll();

	public List<Review> getReviewsByProductId(long productId); 
	 

}


