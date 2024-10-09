package com.spring.jdbc.repositories.named;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.spring.jdbc.domains.DivisionDto;
import com.spring.jdbc.repositories.DivisionRepo;

@Repository
@Profile("named")
public class DivisionRepoNamedParameterJdbcTemplate implements DivisionRepo {

	@Autowired
	private NamedParameterJdbcTemplate template;
	private RowMapper<DivisionDto> rowMapper;

	private static final String SELECT_SQL = """
			SELECT dv.id, dv.name, COUNT(ds.id) districts
			FROM division dv JOIN district ds ON dv.id = ds.division_id
			""";

	private static final String GROUP_BY = "group by dv.id, dv.name";

	public DivisionRepoNamedParameterJdbcTemplate() {
		rowMapper = new DataClassRowMapper<>(DivisionDto.class);
	}

	@Override
	public List<DivisionDto> search(String name) {
		var sql = new StringBuffer(SELECT_SQL);
		var params = new HashMap<String, Object>();

		if (StringUtils.hasLength(name)) {
			sql.append(" WHERE LOWER(dv.name) LIKE :name");
			params.put("name", name.toLowerCase().concat("%"));
		}

		sql.append(" ").append(GROUP_BY);

		return template.query(sql.toString(), params, rowMapper);
	}

	@Override
	public Optional<DivisionDto> findById(int id) {
		var sql = "%s WHERE dv.id = :id %s".formatted(SELECT_SQL, GROUP_BY);

		return template.query(sql, Map.of("id", id), rowMapper).stream().findAny();
	}

}
