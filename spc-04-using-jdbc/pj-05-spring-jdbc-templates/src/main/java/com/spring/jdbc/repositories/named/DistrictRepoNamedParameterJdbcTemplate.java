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

import com.spring.jdbc.domains.DistrictDto;
import com.spring.jdbc.repositories.DistrictRepo;

@Repository
@Profile("named")
public class DistrictRepoNamedParameterJdbcTemplate implements DistrictRepo {

	@Autowired
	private NamedParameterJdbcTemplate template;
	private RowMapper<DistrictDto> rowMapper;

	private static final String SELECT_SQL = """
			SELECT ds.id, ds.name, dv.id divisionId, dv.name divisionName,
			COUNT(ts.id) townships FROM district ds
			JOIN division dv ON ds.division_id = dv.id
			LEFT JOIN township ts ON ts.district_id = ds.id
			""";

	private final String GROUP_BY = "GROUP BY ds.id, ds.name, dv.id, dv.name";

	public DistrictRepoNamedParameterJdbcTemplate() {
		rowMapper = new DataClassRowMapper<>(DistrictDto.class);
	}

	@Override
	public List<DistrictDto> search(Integer divisionId, String name) {
		var sql = new StringBuffer(SELECT_SQL).append(" WHERE 1 = 1");
		var params = new HashMap<String, Object>();

		if (divisionId != null) {
			sql.append(" AND dv.id = :division");
			params.put("division", divisionId);
		}

		if (StringUtils.hasLength(name)) {
			sql.append(" AND LOWER(ds.name) LIKE :name");
			params.put("name", name.toLowerCase().concat("%"));
		}

		sql.append(" ").append(GROUP_BY);

		return template.query(sql.toString(), params, rowMapper);
	}

	@Override
	public Optional<DistrictDto> findById(int id) {
		var sql = "%s WHERE ds.id = :id %s".formatted(SELECT_SQL, GROUP_BY);

		return template.query(sql, Map.of("id", id), rowMapper).stream().findAny();
	}

}
