package com.hdecor.service;

import java.util.List;

import com.hdecor.model.Product;

public interface ProductService {

	public int deleteProduct(long id);

	public List<Product> getAll();

	public Product getById(long l);
	
	public List<Product> getProductsByUserId(long userId);

	public Product getAllDetails(long id);

}
