package com.hdecor.serviceimpl;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.MaterialDao;
import com.hdecor.model.Admin;
import com.hdecor.model.Material;
import com.hdecor.service.MaterialService;

@Service("materialservice")
@Transactional
@SessionAttributes("admin")
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	MaterialDao materialdao;

	@Autowired
	HttpSession session;

	public long addUpdateMaterial(Material material) {
		Admin ad = (Admin) session.getAttribute("admin");
		if (material.getMaterialId() >= 1) {
			material.setModifiedBy(ad.getAdminId());
			material.setModifiedDate(new Date(System.currentTimeMillis()));
		} else {
			material.setCreatedBy(ad.getAdminId());
			material.setCreatedDate(new Date(System.currentTimeMillis()));
		}
		material.setIsActive(1);
		material.setIsDeleted(0);
		return materialdao.addUpdateMaterial(material);
	}

	public boolean deleteMaterial(long id) {
		return materialdao.deleteMaterial(id);
	}

	public List<Material> getAll() {
		return materialdao.getAll();
	}

	public Material getById(long id) {
		return materialdao.getById(id);
	}

}
