package com.hdecor.daoimpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.ReviewDao;
import com.hdecor.model.Review;
import com.hdecor.util.DbUtility;

@Repository("reviewDao")
@SessionAttributes("admin")
public class ReviewDaoImpl extends GenericDaoImpl<Review> implements ReviewDao {

	@Autowired
	HttpSession session;

	public Review saveupdateReview(Review review) {
		return super.saveUpdateObject(review);
	}

	public List<Review> getActive() {
		List<Review> list = DbUtility.getListData("SELECT r.reviewId as reviewId,r.userId as userId ,r.productId as productId,r.reviewDesc as reviewDesc,r.reviewDate as reviewDate FROM review r WHERE r.isDeleted = 0",Review.class);
		return list;
	}

	public List<Review> getReviewsByProductId(long productId) {
		List<Review> list = DbUtility.getListData("SELECT r.reviewDesc,r.reviewDate,r.userId,r.productId FROM review r where r.isDeleted=0 and r.productId="+productId, Review.class);
		return list;
	}

}
