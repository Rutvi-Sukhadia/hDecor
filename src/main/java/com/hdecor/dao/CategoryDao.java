package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Category;

public interface CategoryDao extends GenericDAO<Category> {
	public List<Category> getAll();

	public Category getById(long id);

	public boolean deleteCategory(long id);

	public long addUpdateCategory(Category category);
}
