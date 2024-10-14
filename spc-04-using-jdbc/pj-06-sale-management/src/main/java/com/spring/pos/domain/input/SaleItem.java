package com.spring.pos.domain.input;

import lombok.Data;

@Data
public class SaleItem {

	private String productCode;
	private int saleId;
	private int unitPrice;
	private int quantity;
}
