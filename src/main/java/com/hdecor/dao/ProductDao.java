package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Product;

public interface ProductDao extends GenericDAO<Product> {

	public List<Product> getActive();

	public List<Product> getProductsByUserId(long userId);

}