package com.hdecor.serviceimpl;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.CityDao;
import com.hdecor.model.Admin;
import com.hdecor.model.City;
import com.hdecor.service.CityService;

@Service("cityservice")
@Transactional
@SessionAttributes("admin")
public class CityServiceImpl implements CityService {

	@Autowired
	CityDao citydao;
	
	@Autowired
	HttpSession session;
	
	public long addUpdateCity(City city) {
		Admin ad = (Admin)session.getAttribute("admin");
		if(city.getCityId()<=1)
		{
			city.setCreatedBy(ad.getAdminId());
			city.setCreatedDate(new Date(System.currentTimeMillis()));
		}
		else
		{
			city.setModifiedBy(ad.getAdminId());
			city.setModifiedDate(new Date(System.currentTimeMillis()));
		}
		
		return citydao.addUpdateCity(city);
	}

	public boolean deleteCity(long id) {
		return citydao.deleteCity(id);
	}

	public List<City> getAll() {
		return citydao.getAll();
	}

	public City getById(long id) {
		return citydao.getById(id);
	}

	public List<City> getAllCitiesByStateId(long stateId) {
		return citydao.getAllCitiesByStateId(stateId);
	}


}
