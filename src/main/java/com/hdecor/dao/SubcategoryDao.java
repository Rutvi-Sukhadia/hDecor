package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Subcategory;

public interface SubcategoryDao extends GenericDAO<Subcategory> {
	public List<Subcategory> getAll();

	public Subcategory getById(long id);

	public boolean deleteSubcategory(long id);

	public long addUpdateSubcategory(Subcategory subcategory);

	public List<Subcategory> getAllSubcategoriesByCategoryId(long categoryId);

}
