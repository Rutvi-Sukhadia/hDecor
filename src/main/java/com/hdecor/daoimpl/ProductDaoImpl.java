package com.hdecor.daoimpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.ProductDao;

import com.hdecor.model.Product;
import com.hdecor.util.DbUtility;

@Repository("productDao")
@SessionAttributes("user")

public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

	@Autowired
	HttpSession session;

	public Product saveupdateProduct(Product product) {
		return super.saveUpdateObject(product);
	}

	public List<Product> getActive() {
		// List<Product> list= DbUtility.getListData("select p.productId as
		// productId,p.name as name,p.status as status,p.rent as rent,u.fname as
		// fname,u.lname as lname FROM product p INNER JOIN user u ON p.userId=u.userId
		// WHERE p.isDeleted=0 and u.isDeleted=0",Product.class);
		return super.getByQuery("FROM Product p WHERE p.isDeleted=0");
	}

	public List<Product> getProductsByUserId(long userId) {
		// List<Product> list=DbUtility.getListData("SELECT p.userId as userId FROM
		// product p WHERE p.isDeleted = 0 AND p.userId ="+userId, Product.class);
		return super.getByQuery("FROM Product p WHERE p.isDeleted=0 and p.userId =" + userId);
	}

}
