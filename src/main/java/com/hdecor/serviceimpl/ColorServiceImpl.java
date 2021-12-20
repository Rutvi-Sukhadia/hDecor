package com.hdecor.serviceimpl;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.ColorDao;
import com.hdecor.model.Admin;
import com.hdecor.model.Color;
import com.hdecor.service.ColorService;


@Service("colorservice")
@Transactional
@SessionAttributes("admin")
public class ColorServiceImpl implements ColorService{

	@Autowired
	ColorDao colordao;
	
	@Autowired
	HttpSession session;
	
	public long addUpdateColor(Color color) {
		Admin ad = (Admin)session.getAttribute("admin");
		if(color.getColorId()>=1)
		{
			color.setModifiedBy(ad.getAdminId());
			color.setModifiedDate(new Date(System.currentTimeMillis()));
		}
		else
		{
			color.setCreatedBy(ad.getAdminId());
			color.setCreatedDate(new Date(System.currentTimeMillis()));
		}
		color.setIsActive(1);
		color.setIsDeleted(0);
		return colordao.addUpdateColor(color);
	}

	public boolean deleteColor(long id) {
		return colordao.deleteColor(id);
	}

	public List<Color> getAll() {
		return colordao.getAll();
	}

	public Color getById(long id) {
		return colordao.getById(id);
	}
	

}
