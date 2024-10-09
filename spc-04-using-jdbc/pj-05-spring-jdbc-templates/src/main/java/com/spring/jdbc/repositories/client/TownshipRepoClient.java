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

import com.spring.jdbc.domains.TownshipDto;
import com.spring.jdbc.repositories.TownshipRepo;

@Repository
@Profile("client")
public class TownshipRepoClient implements TownshipRepo {

	@Autowired
	private JdbcClient client;
	private RowMapper<TownshipDto> rowMapper = new DataClassRowMapper<>(TownshipDto.class);

	@Value("${app.sql.township-select}")
	private String selectSql;

	@Override
	public List<TownshipDto> search(Integer divisionId, Integer districtId, String name) {
		var sql = new StringBuffer(selectSql).append(" WHERE 1 = 1");
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

		return client.sql(sql.toString()).params(params).query(rowMapper).list();
	}

	@Override
	public Optional<TownshipDto> findById(int id) {
		return client.sql("%s WHERE ts.id = :id".formatted(selectSql)).params(Map.of("id", id)).query(rowMapper)
				.optional();
	}

}
