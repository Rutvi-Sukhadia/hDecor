package com.hdecor.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hdecor.dao.AreaDao;
import com.hdecor.model.Area;
import com.hdecor.util.DbUtility;

@Repository("areadao")
public class AreaDaoImpl extends GenericDaoImpl<Area>  implements AreaDao {

	public List<Area> getAll() {

		List<Area> list = DbUtility.getListData("SELECT a.areaId as areaId,"
				+ " a.areaName as areaName,c.cityName as cityName, s.stateName as stateName "
				+ "FROM area a INNER JOIN city c ON a.cityId = c.cityId INNER JOIN state s"
				+ " ON c.stateId = s.stateId  WHERE a.isDeleted=0 and c.isDeleted=0 and "
				+ "s.isDeleted=0", Area.class);
		return list;
	}

	public Area getById(long id) {
		return super.getById(Area.class, id);
	}

	public boolean deleteArea(long id) {
		Area obj = super.getById(Area.class,id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getAreaId()>0;
	}

	public long addUpdateArea(Area area) {
		List<Area> list = super.getByQuery("FROM Area s WHERE s.isDeleted=0 and s.areaName= '"+area.getAreaName()+"' and s.cityId = "+area.getCityId());
		if(list.size()>0)
			return 0;
		Area new_obj =super.saveUpdateObject(area);
		return new_obj.getAreaId();
	}
	
	public Area getAllDetails(long areaId) {
		List<Area> list = DbUtility.getListData("SELECT a.areaId as areaId,"
				+ " a.areaName as areaName,c.cityId as cityId,c.cityName as cityName,"
				+ "s.stateId as stateId, s.stateName as stateName "
				+ "FROM area a INNER JOIN city c ON a.cityId = c.cityId INNER JOIN state s"
				+ " ON c.stateId = s.stateId  WHERE a.isDeleted=0 and c.isDeleted=0 and "
				+ "s.isDeleted=0 and a.areaId="+areaId, Area.class);
		return list.get(0);
	}

}