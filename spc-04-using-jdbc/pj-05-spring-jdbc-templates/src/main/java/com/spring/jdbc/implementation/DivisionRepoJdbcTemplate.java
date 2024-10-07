package com.spring.jdbc.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.spring.jdbc.domains.DivisionDto;
import com.spring.jdbc.repositories.DivisionRepo;

@Repository
@Profile("jdbc")
public class DivisionRepoJdbcTemplate implements DivisionRepo {

	@Autowired
	private JdbcTemplate template;
	private RowMapper<DivisionDto> rowMapper;

	private static final String SELECT_SQL = """
			        SELECT dv.id, dv.name, COUNT(ds.id) districts
			        FROM division dv
			        JOIN district ds ON ds.division_id = dv.id
			""";

	public DivisionRepoJdbcTemplate() {
		rowMapper = new DataClassRowMapper<>(DivisionDto.class);
	}

	@Override
	public List<DivisionDto> search(String name) {
		var sql = new StringBuffer(SELECT_SQL);
		var params = new ArrayList<Object>();

		if (StringUtils.hasLength(name)) {
			sql.append(" WHERE LOWER(dv.name) LIKE ?");
			params.add(name.toLowerCase().concat("%"));
		}

		sql.append(" GROUP BY dv.id, dv.name");

		return template.query(sql.toString(), rowMapper, params.toArray());
	}

	@Override
	public Optional<DivisionDto> findById(int id) {
		return template.query("%s WHERE dv.id = ? GROUP BY dv.id, dv.name".formatted(SELECT_SQL), rowMapper, id)
				.stream().findAny();
	}

}
