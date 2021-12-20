package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.State;

public interface StateDao extends GenericDAO<State> {
	public List<State> getAll();

	public State getById(long id);

	public boolean deleteState(long id);

	public long addUpdateState(State state);
}
