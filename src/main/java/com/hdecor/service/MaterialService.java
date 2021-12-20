package com.hdecor.service;

import java.util.List;

import com.hdecor.model.Material;

public interface MaterialService {

	public long addUpdateMaterial(Material material);

	public boolean deleteMaterial(long id);

	public List<Material> getAll();

	public Material getById(long id);

}