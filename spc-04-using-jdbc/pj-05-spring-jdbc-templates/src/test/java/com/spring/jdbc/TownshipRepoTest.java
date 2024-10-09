package com.spring.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.spring.jdbc.repositories.TownshipRepo;

@SpringBootTest
@ActiveProfiles("client")
public class TownshipRepoTest {

	@Autowired
	private TownshipRepo repo;

	@ParameterizedTest
	@CsvFileSource(resources = "/township_test_find_by_id.txt", delimiter = '\t')
	void test_find_by_id(int id, String name, int districtId, String districtName, int divisionId,
			String divisionName) {
		var result = repo.findById(id);
		assertTrue(result.isPresent());

		result.ifPresent(dto -> {
			assertEquals(id, dto.id());
			assertEquals(name, dto.name());
			assertEquals(districtId, dto.districtId());
			assertEquals(districtName, dto.districtName());
			assertEquals(divisionId, dto.divisionId());
			assertEquals(divisionName, dto.divisionName());
		});
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 324 })
	void test_find_by_id_not_found(int id) {
		var result = repo.findById(id);
		assertTrue(result.isEmpty());
	}

	@ParameterizedTest
	@CsvSource({ ",,,323", "15,,,45", ",15,,3", "15,85,,13", "15,85,k,3", "15,85,ks,0", })
	void test_search(Integer divisionId, Integer districtId, String name, int size) {
		var result = repo.search(divisionId, districtId, name);
		assertEquals(size, result.size());
	}
}
