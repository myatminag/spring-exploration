package com.spring.jdbc.repositories;

import java.util.List;
import java.util.Optional;

import com.spring.jdbc.domains.DivisionDto;

public interface DivisionRepo {

	List<DivisionDto> search(String name);

	Optional<DivisionDto> findById(int id);
}
