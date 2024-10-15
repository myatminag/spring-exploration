package com.spring.jdbc.repo;

import com.spring.jdbc.domain.ProductDetails;
import com.spring.jdbc.domain.ProductForm;

public interface ProductRepo {

	int create(ProductForm form);

	ProductDetails findById(int id);
}
