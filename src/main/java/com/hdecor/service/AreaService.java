package com.hdecor.service;

import java.util.List;
import com.hdecor.model.Area;

public interface AreaService {
	
	public long addUpdateArea(Area area);
	public boolean deleteArea(long id);
	public List<Area> getAll();
	public Area getById(long id);
}
