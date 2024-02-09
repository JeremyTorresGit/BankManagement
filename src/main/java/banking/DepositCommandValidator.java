package banking;

public class DepositCommandValidator extends CommandValidator {
	private Bank bank;

	public DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validateDepositCommand(String[] parts) {
		if (parts.length != 3) {
			return false;
		}

		String accountId = parts[1];
		String amount = parts[2];

		if ((!isValidAccountId(accountId)) || (!isValidAmount(amount))) {
			return false;
		}

		Account account = bank.getAccountByID(accountId);
		if (account == null) {
			return false;
		}

		String accountType = account.getAccountType();

		if (accountType == "Checking" || accountType == "Savings") {
			// acts as a checking account
			if (accountType == "Checking") {
				if (!isValidDepositAmountForChecking(amount)) {
					return false;
				}
			}

			// acts as a savings account
			if (accountType == "Savings") {
				if (!isValidDepositAmountForSavings(amount)) {
					return false;
				}
			}
		}

		if (accountType == "Cd") {
			return false;
		}

		return true;
	}

	public boolean isValidDepositAmountForChecking(String amount) {
		double amt = Double.parseDouble(amount);

		return amt <= 2500;
	}

	public boolean isValidDepositAmountForSavings(String amount) {
		double amt = Double.parseDouble(amount);

		return amt <= 1000;
	}
}
