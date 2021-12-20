package com.hdecor.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hdecor.dao.CityDao;
import com.hdecor.model.City;
import com.hdecor.util.DbUtility;

@Repository("citydao")
public class CityDaoImpl extends GenericDaoImpl<City> implements CityDao {

	public List<City> getAll() {

		List<City> list = DbUtility.getListData(
				"SELECT c.cityId as cityId, c.cityName as cityName,c.stateId as stateId, s.stateName as stateName FROM city c INNER JOIN state s ON c.stateId = s.stateId WHERE c.isDeleted=0 and s.isDeleted=0",
				City.class);
		return list;
	}

	public City getById(long id) {
		return super.getById(City.class, id);
	}

	public boolean deleteCity(long id) {
		City obj = super.getById(City.class, id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getCityId() > 0;
	}

	public long addUpdateCity(City city) {
		List<City> list = super.getByQuery("FROM City s WHERE s.isDeleted=0 and s.cityName= '"+city.getCityName()+"' and s.stateId = "+city.getStateId());
		if(list.size()>0)
			return 0;
		City new_obj = super.saveUpdateObject(city);
		return new_obj.getCityId();
	}

	public City editCity(long id) {
		return super.getById(City.class, id);
	}

	public List<City> getAllCitiesByStateId(long stateId) {
		List<City> list = DbUtility.getListData(
				"SELECT c.cityId as cityId,c.cityName as cityName FROM city c WHERE c.isDeleted = 0 AND c.stateId ="
						+ stateId,
				City.class);
		return list;
	}

}
