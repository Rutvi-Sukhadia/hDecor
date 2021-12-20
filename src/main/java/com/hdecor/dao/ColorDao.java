package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Color;

public interface ColorDao extends GenericDAO<Color> {
	public List<Color> getAll();

	public Color getById(long id);

	public boolean deleteColor(long id);

	public long addUpdateColor(Color color);
}
