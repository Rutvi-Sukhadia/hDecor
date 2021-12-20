package com.hdecor.serviceimpl;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.CategoryDao;
import com.hdecor.model.Admin;
import com.hdecor.model.Category;
import com.hdecor.service.CategoryService;

@Service("categoryservice")
@Transactional
@SessionAttributes("admin")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao categorydao;
	
	@Autowired
	HttpSession session;
	
	public long addUpdateCategory(Category category) {
		Admin ad = (Admin)session.getAttribute("admin");
		if(category.getCategoryId()>=1)
		{
			category.setModifiedBy(ad.getAdminId());
			category.setModifiedDate(new Date(System.currentTimeMillis()));
		}
		else
		{
			category.setCreatedBy(ad.getAdminId());
			category.setCreatedDate(new Date(System.currentTimeMillis()));
		}
		category.setIsActive(1);
		category.setIsDeleted(0);
		return categorydao.addUpdateCategory(category);
	}

	public boolean deleteCategory(long id) {
		return categorydao.deleteCategory(id);
	}

	public List<Category> getAll() {
		return categorydao.getAll();
	}
	
	public Category getById(long id) {
		return categorydao.getById(id);
	}
	
}
