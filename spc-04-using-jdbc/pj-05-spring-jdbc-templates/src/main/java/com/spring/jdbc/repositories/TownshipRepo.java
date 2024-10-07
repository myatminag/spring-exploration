package com.spring.jdbc.repositories;

import java.util.List;
import java.util.Optional;

import com.spring.jdbc.domains.TownshipDto;

public interface TownshipRepo {

	List<TownshipDto> search(Integer divisionId, Integer districtId, String name);

	Optional<TownshipDto> findById(int id);
}
