package com.hdecor.service;

import java.util.List;

import com.hdecor.model.State;

public interface StateService {

	public long addUpdateState(State state);

	public boolean deleteState(long id);

	public List<State> getAll();

	public State getById(long id);

}