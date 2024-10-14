package com.spring.pos.repository;

import java.util.List;

import com.spring.pos.domain.input.SaleItem;
import com.spring.pos.domain.output.SaleDetailsItem;

public interface SaleProductRepo {

	void create(int saleId, SaleItem item);

	List<SaleDetailsItem> findBySaleId(int id);
}
