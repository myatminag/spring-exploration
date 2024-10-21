package com.spring.trx.dto;

public record AccountInfo(String accountNumber, String accountName, String phone, int amount, int version) {

	public int nextVersion() {
		return version + 1;
	}

}
