package com.spring.trx.dto;

public record TransferForm(String accountFrom, String accountTo, int amount, String remark) {

}
