package com.hdecor.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hdecor.dao.StateDao;
import com.hdecor.model.State;

@Repository("StateDao")
public class StateDaoImpl extends GenericDaoImpl<State> implements StateDao {

	public List<State> getAll() {
		return super.getByQuery("FROM State s WHERE s.isDeleted=0");
	}

	public State getById(long id) {
		return super.getById(State.class, id);
	}

	public boolean deleteState(long id) {
		State obj = super.getById(State.class, id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getStateId() > 0;
	}

	public long addUpdateState(State state) {
		List<State> list = super.getByQuery("FROM State s WHERE s.isDeleted=0 and s.stateName= '"+state.getStateName()+"'");
		if(list.size()>0)
			return 0;
		State new_obj = super.saveUpdateObject(state);
		return new_obj.getStateId();
	}

}