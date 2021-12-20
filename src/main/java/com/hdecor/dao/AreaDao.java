package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Area;

public interface AreaDao extends GenericDAO<Area> {
	public List<Area> getAll();

	public Area getById(long id);

	public boolean deleteArea(long id);

	public long addUpdateArea(Area area);

	public Area getAllDetails(long areaId);

}
