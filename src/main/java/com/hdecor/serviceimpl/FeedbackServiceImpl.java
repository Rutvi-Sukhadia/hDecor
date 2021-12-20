package com.hdecor.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hdecor.dao.FeedbackDao;
import com.hdecor.model.Feedback;
import com.hdecor.service.FeedbackService;

@Service("feedbackservice")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackDao feedbackdao;

	public List<Feedback> getAll() {
		return feedbackdao.getAll();
	}

	public boolean deleteFeedback(long id) {
		return feedbackdao.deleteFeedback(id);
	}

	public Feedback getFeedback(long id) {
		return feedbackdao.getById(id);
	}

	public int sendReply(Feedback feedback) {
		return feedbackdao.sendReply(feedback);
	}

}
