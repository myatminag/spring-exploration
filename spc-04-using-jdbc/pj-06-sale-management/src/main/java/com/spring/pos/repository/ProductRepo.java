package com.spring.pos.repository;

import java.util.Optional;

import com.spring.pos.domain.output.ProductDto;

public interface ProductRepo {

	Optional<ProductDto> findByCode(String code);

}
