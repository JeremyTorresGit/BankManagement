package banking;

public class WithdrawCommandValidator extends CommandValidator {
	private Bank bank;

	public WithdrawCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validateWithdrawCommand(String[] parts) {
		String accountId = parts[1];
		String amount = parts[2];

		if ((!isValidAccountId(accountId)) || (!isValidAmount(amount))) {
			return false;
		}

		String accountType = bank.getAccountByID(accountId).getAccountType();

		// acts as a checking account
		if ("Checking".equals(accountType)) {
			if (!isValidWithdrawAmountForChecking(amount)) {
				return false;
			}
		}

		// acts as a savings account
		if ("Savings".equals(accountType)) {
			if (!isValidWithdrawAmountForSavings(amount)) {
				return false;
			}

			Integer withdrawalsThisMonth = bank.getAccountByID(accountId).getWithdrawalsThisMonth();
			if (withdrawalsThisMonth > 0) {
				return false;
			}

		}

		// acts as a checking account
		if ("Cd".equals(accountType)) {
			if (!isValidCDWithdrawal(accountId, amount)) {
				return false;
			}

			Integer monthsSinceCreation = bank.getAccountByID(accountId).getMonthsSinceCreation();
			if (monthsSinceCreation < 12) {
				return false;
			}

		}

		return true;

	}

	public boolean isValidWithdrawAmountForChecking(String amount) {
		double amt = Double.parseDouble(amount);

		return amt <= 400;
	}

	public boolean isValidWithdrawAmountForSavings(String amount) {
		double amt = Double.parseDouble(amount);

		return amt <= 1000;
	}

	public boolean isValidCDWithdrawal(String accountId, String amount) {
		double amt = Double.parseDouble(amount);
		double balance = bank.getBalance(accountId).getBalance();

		if (amt < balance) {
			return false;
		}
		return true;
	}
}
