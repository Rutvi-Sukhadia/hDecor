package com.hdecor.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hdecor.dao.SubcategoryDao;
import com.hdecor.model.Subcategory;
import com.hdecor.util.DbUtility;

@Repository("subcategorydao")
public class SubcategoryDaoImpl extends GenericDaoImpl<Subcategory> implements SubcategoryDao {

	public List<Subcategory> getAll() {

		List<Subcategory> list = DbUtility.getListData(
				"SELECT c.subcategoryId as subcategoryId, c.subcategoryName as subcategoryName,c.parentCategoryId as parentCategoryId, s.categoryName as parentCategoryName FROM subcategory c INNER JOIN category s ON c.parentCategoryId = s.categoryId WHERE c.isDeleted=0 and s.isDeleted=0",
				Subcategory.class);
		return list;
	}

	public Subcategory getById(long id) {
		return super.getById(Subcategory.class, id);
	}

	public boolean deleteSubcategory(long id) {
		Subcategory obj = super.getById(Subcategory.class, id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getSubcategoryId() > 0;
	}

	public long addUpdateSubcategory(Subcategory subcategory) {
		List<Subcategory> list = super.getByQuery("FROM Subcategory s WHERE s.isDeleted=0 and s.subcategoryName= '"+subcategory.getSubcategoryName()+"' and s.parentCategoryId ="+subcategory.getParentCategoryId());
		if(list.size()>0)
			return 0;
		Subcategory new_obj = super.saveUpdateObject(subcategory);
		return new_obj.getSubcategoryId();
	}

	public List<Subcategory> getAllSubcategoriesByCategoryId(long categoryId) {
		List<Subcategory> list = DbUtility.getListData(
				"SELECT c.subcategoryId as subcategoryId,c.subcategoryName as subcategoryName FROM subcategory c WHERE c.isDeleted = 0 AND c.parentCategoryId ="
						+ categoryId,
				Subcategory.class);
		return list;
	}

}
