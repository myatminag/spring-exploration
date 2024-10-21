package com.spring.trx.dto;

public record AmountUpdateForm(String accountNumber, int amount, int version) {

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String accountNumber;
		private int amount;
		private int version;

		public AmountUpdateForm build() {
			return new AmountUpdateForm(accountNumber, amount, version);
		}

		public Builder accountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public Builder updatedAmount(int amount) {
			this.amount = amount;
			return this;
		}

		public Builder nextVersion(int version) {
			this.version = version;
			return this;
		}

	}
}
