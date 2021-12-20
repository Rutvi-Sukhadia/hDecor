package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Material;

public interface MaterialDao extends GenericDAO<Material> {
	public List<Material> getAll();

	public Material getById(long id);

	public boolean deleteMaterial(long id);

	public long addUpdateMaterial(Material material);
}
