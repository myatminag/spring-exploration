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

import com.spring.jdbc.domains.TownshipDto;
import com.spring.jdbc.repositories.TownshipRepo;

@Repository
@Profile("jdbc")
public class TownshipRepoJdbcTemplate implements TownshipRepo {

	@Autowired
	private JdbcTemplate template;
	private RowMapper<TownshipDto> rowMapper;

	private static final String SELECT_SQL = """
			SELECT ts.id, ts.name, ds.id districtId, ds.name districtName,
			dv.id divisionId, dv.name divisionName FROM township ts
			JOIN district ds ON ds.id = ts.district_id
			JOIN division dv ON dv.id = ds.division_id
			""";

	public TownshipRepoJdbcTemplate() {
		rowMapper = new DataClassRowMapper<>(TownshipDto.class);
	}

	@Override
	public List<TownshipDto> search(Integer divisionId, Integer districtId, String name) {
		var sql = new StringBuffer(SELECT_SQL).append(" WHERE 1 = 1");
		var params = new ArrayList<Object>();

		if (divisionId != null) {
			sql.append(" AND dv.id = ?");
			params.add(divisionId);
		}

		if (districtId != null) {
			sql.append(" AND ds.id = ?");
			params.add(districtId);
		}

		if (StringUtils.hasLength(name)) {
			sql.append(" WHERE LOWER(ts.name) LIKE ?");
			params.add(name.toLowerCase().concat("%"));
		}

		return template.query(sql.toString(), rowMapper, params.toArray());
	}

	@Override
	public Optional<TownshipDto> findById(int id) {
		return template.query("%s WHERE ts.id = ?".formatted(SELECT_SQL), rowMapper, id).stream().findAny();
	}

}
