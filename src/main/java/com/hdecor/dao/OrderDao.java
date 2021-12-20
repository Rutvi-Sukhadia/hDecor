package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Order;

public interface OrderDao extends GenericDAO<Order> {
	public List<Order> getActive();

}