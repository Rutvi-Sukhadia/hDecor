package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.City;

public interface CityDao extends GenericDAO<City> {
	public List<City> getAll();

	public City getById(long id);

	public boolean deleteCity(long id);

	public long addUpdateCity(City city);

	public List<City> getAllCitiesByStateId(long stateId);
}
