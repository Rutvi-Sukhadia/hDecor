package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Review;

public interface ReviewDao extends GenericDAO<Review> {
	public List<Review> getActive();

	public List<Review> getReviewsByProductId(long productId);

}