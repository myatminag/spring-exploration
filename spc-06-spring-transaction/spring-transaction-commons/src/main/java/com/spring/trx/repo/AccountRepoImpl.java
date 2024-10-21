package com.spring.trx.repo;

import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.spring.trx.dto.AccountInfo;
import com.spring.trx.dto.AmountUpdateForm;

@Repository
public class AccountRepoImpl implements AccountRepo {

	private static final String SELECT_SQL = "SELECT * FROM account WHERE account_number = :accountNumber";
	private static final String UPDATE_SQL = """
				UPDATE account
				SET version = :version, amount = :amount
				WHERE account_number = :accountNumber
			""";

	private NamedParameterJdbcTemplate template;
	private RowMapper<AccountInfo> rowMapper;

	public AccountRepoImpl(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		rowMapper = new DataClassRowMapper<>(AccountInfo.class);
	}

	@Override
	public Optional<AccountInfo> findByAccountId(String accountNumber) {
		return template.query(SELECT_SQL, Map.of("accountNumber", accountNumber), rowMapper).stream().findAny();
	}

	@Override
	public void updateAmount(AmountUpdateForm fromAmountForm) {
		template.update(UPDATE_SQL, new SimplePropertySqlParameterSource(fromAmountForm));
	}

}
