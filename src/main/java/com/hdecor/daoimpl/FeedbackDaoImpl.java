package com.hdecor.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hdecor.dao.FeedbackDao;
import com.hdecor.model.ConfigProperties;
import com.hdecor.model.Feedback;
import com.hdecor.util.Mailer;

@Repository("feedbackDao")
public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {

	public List<Feedback> getAll() {
		return super.getByQuery("FROM Feedback f WHERE f.isDeleted=0");
	}

	public boolean deleteFeedback(long id) {
		Feedback obj = super.getById(Feedback.class, id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getFeedbackId() > 0;
	}

	public Feedback getById(long id) {
		return super.getById(Feedback.class, id);
	}

	public int sendReply(Feedback feedback) {
		try {
			String reply = feedback.getReplymsg();
			Mailer.send(ConfigProperties.getMailId(), ConfigProperties.getMailPassword(), feedback.getEmailId(),
					"Reply to your feedback ", " Dear " + feedback.getName() + ",\n" + reply);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

}
