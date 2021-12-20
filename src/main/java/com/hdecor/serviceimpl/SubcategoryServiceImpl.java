package com.hdecor.serviceimpl;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.SubcategoryDao;
import com.hdecor.model.Admin;
import com.hdecor.model.Subcategory;
import com.hdecor.service.SubcategoryService;

@Service("subcategoryservice")
@Transactional
@SessionAttributes("admin")
public class SubcategoryServiceImpl implements SubcategoryService {

	@Autowired
	SubcategoryDao subcategorydao;

	@Autowired
	HttpSession session;

	public long addUpdateSubcategory(Subcategory subcategory) {
		Admin ad = (Admin) session.getAttribute("admin");
		if (subcategory.getSubcategoryId() <= 1) {
			subcategory.setCreatedBy(ad.getAdminId());
			subcategory.setCreatedDate(new Date(System.currentTimeMillis()));
		} else {
			subcategory.setModifiedBy(ad.getAdminId());
			subcategory.setModifiedDate(new Date(System.currentTimeMillis()));
		}

		return subcategorydao.addUpdateSubcategory(subcategory);
	}

	public boolean deleteSubcategory(long id) {
		return subcategorydao.deleteSubcategory(id);
	}

	public List<Subcategory> getAll() {
		return subcategorydao.getAll();
	}

	public Subcategory getById(long id) {
		return subcategorydao.getById(id);
	}

	public List<Subcategory> getAllSubcategoriesByCategoryId(long categoryId) {
		return subcategorydao.getAllSubcategoriesByCategoryId(categoryId);
	}

}
