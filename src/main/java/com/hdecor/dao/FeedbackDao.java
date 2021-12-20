package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Feedback;

public interface FeedbackDao extends GenericDAO<Feedback> {

	public List<Feedback> getAll();

	public boolean deleteFeedback(long id);

	public Feedback getById(long id);

	public int sendReply(Feedback feedback);

}
