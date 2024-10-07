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

import com.spring.jdbc.domains.DistrictDto;
import com.spring.jdbc.repositories.DistrictRepo;

@Repository
@Profile("jdbc")
public class DistrictRepoJdbcTemplate implements DistrictRepo {

	@Autowired
	private JdbcTemplate template;
	private RowMapper<DistrictDto> rowMapper;

	private final String SELECT_SQL = """
			SELECT ds.id, ds.name, dv.id divisionId, dv.name divisionName,
			COUNT(ts.id) townships FROM district ds
			JOIN division dv ON ds.division_id = dv.id
			LEFT JOIN township ts ON ts.district_id = ds.id
			""";

	private final String GROUP_BY = "GROUP BY ds.id, ds.name, dv.id, dv.name";

	public DistrictRepoJdbcTemplate() {
		rowMapper = new DataClassRowMapper<>(DistrictDto.class);
	}

	@Override
	public List<DistrictDto> search(Integer divisionId, String name) {
		var sql = new StringBuffer(SELECT_SQL).append(" WHERE 1 = 1");
		var params = new ArrayList<Object>();

		if (divisionId != null) {
			sql.append(" AND dv.id = ?");
			params.add(divisionId);
		}

		if (StringUtils.hasLength(name)) {
			sql.append(" AND LOWER(ds.name) LIKE ?");
			params.add(name.toLowerCase().concat("%"));
		}

		sql.append(" ").append(GROUP_BY);

		return template.query(sql.toString(), rowMapper, params.toArray());
	}

	@Override
	public Optional<DistrictDto> findById(int id) {
		return template.query("%s WHERE ds.id = ? %s".formatted(SELECT_SQL, GROUP_BY), rowMapper, id).stream()
				.findAny();
	}

}
