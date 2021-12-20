package com.hdecor.service;

import java.util.List;

import com.hdecor.model.Color;

public interface ColorService {

	public long addUpdateColor(Color color);

	public boolean deleteColor(long id);

	public List<Color> getAll();

	public Color getById(long id);

}