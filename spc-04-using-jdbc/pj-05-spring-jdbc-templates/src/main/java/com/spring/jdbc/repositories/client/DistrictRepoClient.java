package com.spring.jdbc.repositories.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.spring.jdbc.domains.DistrictDto;
import com.spring.jdbc.repositories.DistrictRepo;

@Repository
@Profile("client")
public class DistrictRepoClient implements DistrictRepo {

	@Autowired
	private JdbcClient client;
	private RowMapper<DistrictDto> rowMapper = new DataClassRowMapper<>(DistrictDto.class);

	@Value("${app.sql.district-select}")
	private String selectSql;
	@Value("${app.sql.district-group-by}")
	private String groupBy;

	@Override
	public List<DistrictDto> search(Integer divisionId, String name) {
		var sql = new StringBuffer(selectSql).append(" WHERE 1 = 1");
		var params = new HashMap<String, Object>();

		if (divisionId != null) {
			sql.append(" AND dv.id = :division");
			params.put("division", divisionId);
		}

		if (StringUtils.hasLength(name)) {
			sql.append(" AND LOWER(ds.name) LIKE :name");
			params.put("name", name.toLowerCase().concat("%"));
		}

		sql.append(" ").append(groupBy);

		return client.sql(sql.toString()).params(params).query(rowMapper).list();
	}

	@Override
	public Optional<DistrictDto> findById(int id) {
		return client.sql("%s WHERE ds.id = :id %s".formatted(selectSql, groupBy)).params(Map.of("id", id))
				.query(rowMapper).optional();
	}

}
