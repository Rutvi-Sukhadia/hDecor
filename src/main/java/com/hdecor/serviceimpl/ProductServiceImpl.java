package com.hdecor.serviceimpl;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.model.ConfigProperties;
import com.hdecor.model.Product;
import com.hdecor.model.Subcategory;
import com.hdecor.model.User;
import com.hdecor.util.FTPUtils;
import com.hdecor.dao.CategoryDao;
import com.hdecor.dao.ColorDao;
import com.hdecor.dao.MaterialDao;
import com.hdecor.dao.ProductDao;
import com.hdecor.dao.SubcategoryDao;
import com.hdecor.dao.UserDao;
import com.hdecor.service.ProductService;

@Service("productservice")
@Transactional
@SessionAttributes("admin")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Autowired
	HttpSession session;

	@Autowired
	UserDao userdao;

	@Autowired
	SubcategoryDao subcategorydao;

	@Autowired
	CategoryDao categorydao;

	@Autowired
	MaterialDao materialdao;

	@Autowired
	ColorDao colordao;

	public int deleteProduct(long id) {
		Product product = productDao.getById(Product.class, id);
		if (product != null) {
			product.setIsDeleted(1);
			productDao.updateObject(product);
			return 1;
		}
		return 0;
	}

	public List<Product> getAll() {

		List<Product> productList = productDao.getActive();

		for (Product p : productList) {
			User u = userdao.getById(User.class, p.getUserId());
			Subcategory s = subcategorydao.getById(p.getSubcategoryId());
			p.setSubcategoryName(s.getSubcategoryName());

			p.setCategoryName(categorydao.getById(s.getParentCategoryId()).getCategoryName());
			p.setCategoryId(s.getParentCategoryId());

			p.setMaterialName(materialdao.getById(p.getMaterialId()).getMaterialName());
			p.setColorName(colordao.getById(p.getColorId()).getColorName());
			p.setUsername(u.getFname() + " " + u.getLname());
		}

		return productList;
	}

	public Product getById(long id) {
		Product product = productDao.getById(Product.class, id);
		String UPLOADED_FOLDER1 = ConfigProperties.getFolderPath() + "productPicture" + File.separator;
	
	    String newPP = product.getProductPicture();

		File ProductPicture = new File(UPLOADED_FOLDER1 + newPP);

		if (!ProductPicture.exists()) {
			FTPUtils.download(newPP, UPLOADED_FOLDER1 + newPP);
		}

		return product;
	}

	public List<Product> getProductsByUserId(long userId) {
		List<Product> productList = productDao.getProductsByUserId(userId);
		for (Product p : productList) {
			User u = userdao.getById(User.class, p.getUserId());
			Subcategory s = subcategorydao.getById(p.getSubcategoryId());
			p.setSubcategoryName(s.getSubcategoryName());

			p.setCategoryName(categorydao.getById(s.getParentCategoryId()).getCategoryName());
			p.setCategoryId(s.getParentCategoryId());

			p.setMaterialName(materialdao.getById(p.getMaterialId()).getMaterialName());
			p.setColorName(colordao.getById(p.getColorId()).getColorName());
			p.setUsername(u.getFname() + " " + u.getLname());
		}

		return productList;
	}

	public Product getAllDetails(long id) {
		Product p = productDao.getById(Product.class, id);
		User u = userdao.getById(User.class, p.getUserId());
		String UPLOADED_FOLDER1 = ConfigProperties.getFolderPath() + "productPicture" + File.separator;

		
		 String newPP1 = u.getProfilePicture(); 
		 File ProfileImage = new File(UPLOADED_FOLDER1+newPP1); 
		 if(!ProfileImage.exists()) {
			 FTPUtils.download(newPP1, UPLOADED_FOLDER1+newPP1); }
		 	p.setUserProfilePicture(u.getProfilePicture());
		 
		
		String newPP = p.getProductPicture();
		String parray[] = newPP.split("\\|");
		for (int i = 0; i < parray.length; i++) {
			File ProductPicture = new File(UPLOADED_FOLDER1 + parray[i]);

			if (!ProductPicture.exists()) {
				FTPUtils.download(parray[i], UPLOADED_FOLDER1 + parray[i]);
			}
		}
		
		Subcategory s=subcategorydao.getById(p.getSubcategoryId());
		p.setSubcategoryName(s.getSubcategoryName());
		
		p.setCategoryName(categorydao.getById(s.getParentCategoryId()).getCategoryName());
		p.setCategoryId(s.getParentCategoryId());
		
		p.setMaterialName( materialdao.getById(p.getMaterialId()).getMaterialName());
		p.setColorName( colordao.getById(p.getColorId()).getColorName());
		p.setUsername(u.getFname() + " " + u.getLname());
		p.setUserProfilePicture(u.getProfilePicture());
		p.setMobileNo(u.getMobileNo());
		p.setEmail(u.getEmailId());
		return p;
	}


}
