package com.hdecor.serviceimpl;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.StateDao;
import com.hdecor.model.Admin;
import com.hdecor.model.State;
import com.hdecor.service.StateService;

@Service("stateservice")
@Transactional
@SessionAttributes("admin")
public class StateServiceImpl implements StateService {

	@Autowired
	StateDao statedao;

	@Autowired
	HttpSession session;

	public long addUpdateState(State state) {
		Admin ad = (Admin) session.getAttribute("admin");
		if (state.getStateId() >= 1) {
			state.setModifiedBy(ad.getAdminId());
			state.setModifiedDate(new Date(System.currentTimeMillis()));
		} else {
			state.setCreatedBy(ad.getAdminId());
			state.setCreatedDate(new Date(System.currentTimeMillis()));
		}
		state.setIsActive(1);
		state.setIsDeleted(0);
		return statedao.addUpdateState(state);
	}

	public boolean deleteState(long id) {
		return statedao.deleteState(id);
	}

	public List<State> getAll() {
		return statedao.getAll();
	}

	public State getById(long id) {
		return statedao.getById(id);
	}

}
