package com.spring.jdbc.domains;

public record TownshipDto(int id, String name, int districtId, String districtName, int divisionId,
		String divisionName) {

}
