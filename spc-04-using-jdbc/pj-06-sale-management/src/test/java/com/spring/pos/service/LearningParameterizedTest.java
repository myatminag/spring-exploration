package com.spring.pos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import com.spring.pos.domain.input.ShoppingCart;
import com.spring.pos.service.data.ShoppingCartProvider;

public class LearningParameterizedTest {

	@ParameterizedTest
	@NullSource
	void using_null_source(LocalDate date) {
		assertNull(date);
	}

	@ParameterizedTest
	@EmptySource
	void using_empty_source(List<String> list) {
		assertEquals(0, list.size());
	}

	@ParameterizedTest
	@NullAndEmptySource
	void using_null_and_empty(String str) {
		if (str == null) {
			assertNull(str);
		} else {
			assertEquals(0, str.length());
		}
	}

	@ParameterizedTest
	@EnumSource(value = DayOfWeek.class, names = { "SATURDAY", "SUNDAY" }, mode = Mode.EXCLUDE)
	void using_enum_source(DayOfWeek input) {
		System.out.println(input);
	}

	@ParameterizedTest
	@MethodSource
	void using_method_source(String input) {
		System.out.println(input);
	}

	@ParameterizedTest
	@MethodSource("using_method_source")
	void using_method_source_with_name(String input) {
		System.out.println(input);
	}

	@ParameterizedTest
	@MethodSource("com.spring.pos.service.data.ShoppingCartProvider#provide")
	void using_external_method_source(ShoppingCart cart) {
		System.out.println(cart);
	}

	@ParameterizedTest
	@ArgumentsSource(value = ShoppingCartProvider.class)
	void using_argument_source(ShoppingCart cart) {
		System.out.println(cart);
	}

	static Stream<String> using_method_source() {
		return Stream.iterate('A', a -> (char) (a.charValue() + 1)).limit(5).map(a -> a.toString());
	}
}
