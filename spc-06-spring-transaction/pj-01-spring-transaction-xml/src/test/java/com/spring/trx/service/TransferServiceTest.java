package com.spring.trx.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.spring.trx.dto.TransferForm;
import com.spring.trx.service.args.TransferFormAggregator;

@SpringJUnitConfig(locations = "classpath:/application.xml")
public class TransferServiceTest {

	@Autowired
	private TransferService service;

	@ParameterizedTest
	@CsvSource("0001, 0002, 50000, Test Transfer")
	void test(@AggregateWith(TransferFormAggregator.class) TransferForm form) {
		var id = service.transfer(form);

		assertEquals(1, id);
	}
}
