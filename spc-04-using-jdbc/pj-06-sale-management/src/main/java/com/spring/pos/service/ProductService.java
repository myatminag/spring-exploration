package com.spring.pos.service;

import com.spring.pos.domain.output.ProductDto;

public interface ProductService {

	ProductDto findByCode(String code);
}
