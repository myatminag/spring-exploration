package com.spring.jdbc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.jdbc.dto.AccountForm;

@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
public class AccountDAOTest {

//	static GenericXmlApplicationContext context;

	@Autowired
	AccountDAO dao;

//	@BeforeAll
//	static void beforeAll() {
//		context = new GenericXmlApplicationContext("classpath:/application.xml");
//	}
//
//	@BeforeEach
//	void beforeEach() {
//		dao = context.getBean("dao", AccountDAOImpl.class);
//	}

	@Order(1)
	@ParameterizedTest
	@CsvSource({ "Aung Aung,0911112222,1", "Thidar,0911112223,2", "Nilar,0911112224,3", })
	void test_insert(String name, String phone, int expectedId) {
		var form = new AccountForm(name, phone);
		var id = dao.create(form);

		assertEquals(expectedId, id);
	}

	@Test
	@Order(2)
	void test_select_count() {
		var count = dao.count();

		assertEquals(3, count);
	}

	@Order(3)
	@ParameterizedTest
	@CsvSource({ "Aung Aung, 0911112222, 1", "Thidar, 0911112223, 2", "Nilar, 0911112224,  3", })
	void test_find_by_id(String name, String phone, int id) {
		var dto = dao.findById(id);

		assertEquals(name, dto.name());
		assertEquals(phone, dto.phone());
	}

	@Order(4)
	@ParameterizedTest
	@ValueSource(ints = { 4, 5, 0 })
	void test_id_not_found(int id) {
		var dto = dao.findById(id);

		assertNull(dto);
	}

//	@AfterAll
//	static void afterAll() {
//		if (context != null) {
//			context.close();
//		}
//	}
}
