package com.hdecor.service;

import java.util.List;

import com.hdecor.model.City;

public interface CityService {

	public long addUpdateCity(City city);

	public boolean deleteCity(long id);

	public List<City> getAll();

	public City getById(long id);

	public List<City> getAllCitiesByStateId(long stateId);

}
