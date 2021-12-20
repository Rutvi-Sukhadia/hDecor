package com.hdecor.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hdecor.dao.ColorDao;
import com.hdecor.model.Color;

@Repository("ColorDao")
public class ColorDaoImpl extends GenericDaoImpl<Color> implements ColorDao {

	public List<Color> getAll() {
		return super.getByQuery("FROM Color s WHERE s.isDeleted=0");
	}

	public Color getById(long id) {
		return super.getById(Color.class, id);
	}

	public boolean deleteColor(long id) {
		Color obj = super.getById(Color.class, id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getColorId() > 0;
	}

	public long addUpdateColor(Color color) {
		List<Color> list = super.getByQuery("FROM Color s WHERE s.isDeleted=0 and s.colorName= '"+color.getColorName()+"'");
		if(list.size()>0)
			return 0;
		Color new_obj = super.saveUpdateObject(color);
		return new_obj.getColorId();

	}

}