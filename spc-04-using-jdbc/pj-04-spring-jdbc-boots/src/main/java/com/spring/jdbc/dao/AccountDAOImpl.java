package com.spring.jdbc.dao;

import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.spring.jdbc.dto.AccountDTO;
import com.spring.jdbc.dto.AccountForm;

@Repository
public class AccountDAOImpl implements AccountDAO {

	private JdbcTemplate template;
	private RowMapper<AccountDTO> rowMapper;

	public AccountDAOImpl(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
		rowMapper = new DataClassRowMapper<>(AccountDTO.class);
	}

	@Override
	public int create(AccountForm form) {
		var keys = new GeneratedKeyHolder();

		template.update(connect -> {
			var statement = connect.prepareStatement("INSERT INTO account(name, phone) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, form.name());
			statement.setString(2, form.phone());

			return statement;
		}, keys);

		return keys.getKey().intValue();
	}

	@Override
	public long count() {
		return template.queryForObject("SELECT COUNT(id) FROM account", Long.class);
	}

	@Override
	public AccountDTO findById(int id) {
		var list = template.query("SELECT * FROM account WHERE id = ?", rowMapper, id);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

}
