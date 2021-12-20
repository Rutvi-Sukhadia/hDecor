package com.hdecor.service;

import java.util.List;

import com.hdecor.model.Subcategory;

public interface SubcategoryService {

	public long addUpdateSubcategory(Subcategory subcategory);

	public boolean deleteSubcategory(long id);

	public List<Subcategory> getAll();

	public Subcategory getById(long id);

	public List<Subcategory> getAllSubcategoriesByCategoryId(long categoryId);

}
