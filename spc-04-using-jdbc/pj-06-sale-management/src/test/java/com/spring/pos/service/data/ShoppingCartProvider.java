package com.spring.pos.service.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.spring.pos.domain.input.SaleItem;
import com.spring.pos.domain.input.ShoppingCart;

public class ShoppingCartProvider implements ArgumentsProvider {

	public static Stream<Arguments> provide() {
		return Stream.of(Arguments.of(cartWithNoUsername()), Arguments.of(cartWithNoItems()),
				Arguments.of(cartWithOneItem()));
	}

	private static ShoppingCart cartWithOneItem() {
		var cart = new ShoppingCart();
		cart.setSalePerson("Aung Aung");

		var item = new SaleItem();
		item.setProductCode("P0001");
		item.setQuantity(3);
		item.setUnitPrice(1000);

		cart.setItems(List.of(item));

		return cart;
	}

	private static ShoppingCart cartWithNoItems() {
		var cart = new ShoppingCart();
		cart.setSalePerson("Aung Aung");

		return cart;
	}

	private static ShoppingCart cartWithNoUsername() {
		var cart = new ShoppingCart();
		cart.setItems(new ArrayList<>());

		return cart;
	}

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return provide();
	}
}
