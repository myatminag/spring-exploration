package com.spring.jdbc.repo;

import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.spring.jdbc.domain.ProductDetails;
import com.spring.jdbc.domain.ProductForm;

@Profile("jdbcTemplate")
@Repository
public class ProductRepoJdbcTemplate implements ProductRepo {

	private JdbcTemplate template;
	private RowMapper<ProductDetails> rowMapper;

	private static final String INSERT_SQL = """
				INSERT INTO product (name, category, image, price)
				VALUES (?, ?, ?, ?)
			""";

	public ProductRepoJdbcTemplate(DataSource dataSource) {
		rowMapper = new DataClassRowMapper<>(ProductDetails.class);
		template = new JdbcTemplate(dataSource);
	}

	@Override
	public int create(ProductForm form) {

		var keyHolder = new GeneratedKeyHolder();

		template.update(connection -> {
			var statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, form.name());
			statement.setString(2, form.category());
			statement.setString(3, form.image());
			statement.setInt(4, form.price());

			return statement;
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public ProductDetails findById(int id) {
		return template.query("SELECT * FROM product WHERE id = ?", rowMapper, id).stream().findAny().orElse(null);
	}

}
