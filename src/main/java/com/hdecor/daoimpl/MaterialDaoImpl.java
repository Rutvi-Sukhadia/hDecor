package com.hdecor.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hdecor.dao.MaterialDao;
import com.hdecor.model.Material;

@Repository("MaterialDao")
public class MaterialDaoImpl extends GenericDaoImpl<Material> implements MaterialDao {

	public List<Material> getAll() {
		return super.getByQuery("FROM Material s WHERE s.isDeleted=0");
	}

	public Material getById(long id) {
		return super.getById(Material.class, id);
	}

	public boolean deleteMaterial(long id) {
		Material obj = super.getById(Material.class, id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getMaterialId() > 0;
	}

	public long addUpdateMaterial(Material material) {
		List<Material> list = super.getByQuery("FROM Material s WHERE s.isDeleted=0 and s.materialName= '"+material.getMaterialName()+"'");
		if(list.size()>0)
			return 0;
		Material new_obj = super.saveUpdateObject(material);
		return new_obj.getMaterialId();

	}

}