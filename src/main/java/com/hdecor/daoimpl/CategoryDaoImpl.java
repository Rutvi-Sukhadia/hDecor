package com.hdecor.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hdecor.dao.CategoryDao;
import com.hdecor.model.Category;

@Repository("CategoryDao")
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao{

	public List<Category> getAll() {
		return super.getByQuery("FROM Category s WHERE s.isDeleted=0");
	}

	public Category getById(long id) {
		return super.getById(Category.class,id);
	}

	public boolean deleteCategory(long id) {
		Category obj = super.getById(Category.class,id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getCategoryId()>0;
	}

	public long addUpdateCategory(Category c) {
		List<Category> list = super.getByQuery("FROM Category s WHERE s.isDeleted=0 and s.categoryName= '"+c.getCategoryName()+"'");
		if(list.size()>0)
			return 0;
		Category new_obj =super.saveUpdateObject(c);
		return new_obj.getCategoryId();
	
	}
	
}