package com.hdecor.serviceimpl;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.AreaDao;
import com.hdecor.model.Admin;
import com.hdecor.model.Area;
import com.hdecor.service.AreaService;

@Service("areaservice")
@Transactional
@SessionAttributes("admin")
public class AreaServiceImpl implements AreaService {

	@Autowired
	AreaDao areadao;
	
	@Autowired
	HttpSession session;
	
	public long addUpdateArea(Area area) {
		Admin ad=(Admin)session.getAttribute("admin");
		if(area.getAreaId()<=0)
		{
			area.setCreatedBy(ad.getAdminId());
			area.setCreatedDate(new Date(System.currentTimeMillis()));
		}
		else
		{
			area.setModifiedBy(ad.getAdminId());
			area.setModifiedDate(new Date(System.currentTimeMillis()));
		}
		return areadao.addUpdateArea(area);
	}

	public boolean deleteArea(long id) {
		return areadao.deleteArea(id);
	}

	public List<Area> getAll() {
		return areadao.getAll();
	}

	public Area getById(long id) {
		return areadao.getById(id);
	}

}
