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

import com.spring.jdbc.domains.TownshipDto;
import com.spring.jdbc.repositories.TownshipRepo;

@Repository
@Profile("named")
public class TownshipRepoNamedParameterJdbcTemplate implements TownshipRepo {

	@Autowired
	private NamedParameterJdbcTemplate template;
	private RowMapper<TownshipDto> rowMapper;

	private static final String SELECT_SQL = """
			SELECT ts.id, ts.name, ds.id districtId, ds.name districtName,
			dv.id divisionId, dv.name divisionName FROM township ts
			JOIN district ds ON ds.id = ts.district_id
			JOIN division dv ON dv.id = ds.division_id
			""";

	public TownshipRepoNamedParameterJdbcTemplate() {
		rowMapper = new DataClassRowMapper<>(TownshipDto.class);
	}

	@Override
	public List<TownshipDto> search(Integer divisionId, Integer districtId, String name) {
		var sql = new StringBuffer(SELECT_SQL).append(" WHERE 1 = 1");
		var params = new HashMap<String, Object>();

		if (divisionId != null) {
			sql.append(" AND dv.id = :division");
			params.put("division", divisionId);
		}

		if (districtId != null) {
			sql.append(" AND ds.id = :district");
			params.put("district", districtId);
		}

		if (StringUtils.hasLength(name)) {
			sql.append(" AND LOWER(ts.name) LIKE :name");
			params.put("name", name.toLowerCase().concat("%"));
		}

		return template.query(sql.toString(), params, rowMapper);
	}

	@Override
	public Optional<TownshipDto> findById(int id) {
		var sql = "%s WHERE ts.id = :id".formatted(SELECT_SQL);

		return template.query(sql.toString(), Map.of("id", id), rowMapper).stream().findAny();
	}

}
