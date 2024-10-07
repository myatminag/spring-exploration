package com.spring.jdbc.repositories;

import java.util.List;
import java.util.Optional;

import com.spring.jdbc.domains.DistrictDto;

public interface DistrictRepo {

	List<DistrictDto> search(Integer divisionId, String name);

	Optional<DistrictDto> findById(int id);
}
