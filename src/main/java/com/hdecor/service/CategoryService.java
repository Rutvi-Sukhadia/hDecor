package com.hdecor.service;

import java.util.List;

import com.hdecor.model.Category;

public interface CategoryService {
	public boolean deleteCategory(long id);
	public List<Category>getAll();
	public long addUpdateCategory(Category category);
	public Category getById(long id);
}
