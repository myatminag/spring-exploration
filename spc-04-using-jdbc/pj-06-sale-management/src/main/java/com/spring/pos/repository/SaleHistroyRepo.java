package com.spring.pos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.spring.pos.domain.output.SaleInfo;

public interface SaleHistroyRepo {

	int create(String salePerson);

	List<SaleInfo> search(String salePerson, LocalDate from, LocalDate to);

	Optional<SaleInfo> findById(int id);

}
