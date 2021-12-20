package com.hdecor.serviceimpl;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hdecor.model.ConfigProperties;
import com.hdecor.model.Product;
import com.hdecor.model.Review;
import com.hdecor.model.User;
import com.hdecor.dao.ProductDao;
import com.hdecor.dao.ReviewDao;
import com.hdecor.dao.UserDao;
import com.hdecor.service.ReviewService;
import com.hdecor.util.FTPUtils;

@Service("reviewservice")
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao reviewDao;

	@Autowired
	HttpSession session;

	@Autowired
	UserDao userdao;
	
	@Autowired
	ProductDao productdao;

	public List<Review> getAll() {
		List<Review> reviewList = reviewDao.getActive();
		for (Review review : reviewList) {
		User user = userdao.getById(User.class, review.getUserId());
			review.setUserName(user.getFname()+" "+user.getLname());
			Product p=productdao.getById(Product.class,review.getProductId());
			review.setName(p.getName());
		}
		return reviewList;

	}

	public List<Review> getReviewsByProductId(long productId) {
		List<Review> list = reviewDao.getReviewsByProductId(productId);
		for (Review review : list) {
			User user = userdao.getById(User.class, review.getUserId());
			review.setUserName(user.getFname()+" "+user.getLname());
			String UPLOADED_FOLDER1 = ConfigProperties.getFolderPath() + "userProfilePicture"+File.separator;
			String newPP = user.getProfilePicture();
			File ProfileImage = new File(UPLOADED_FOLDER1+newPP);
			if(!ProfileImage.exists())
			{
				FTPUtils.download(newPP, UPLOADED_FOLDER1+newPP);
			}
			review.setUserProfilePicture(user.getProfilePicture());
			
		}
		return list;
	}

}
