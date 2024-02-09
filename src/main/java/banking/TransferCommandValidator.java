package banking;

public class TransferCommandValidator extends CommandValidator {
	private Bank bank;

	public TransferCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validateTransferCommand(String[] commandParts) {
		if (commandParts.length != 4) {
			return false;
		}

		String fromId = commandParts[1];
		String toId = commandParts[2];
		String amountStr = commandParts[3];

		if (!isValidAccountId(fromId) || !isValidAccountId(toId)) {
			return false;
		}

		if (!isValidAmount(amountStr)) {
			return false;
		}

		double amount = Double.parseDouble(amountStr);

		Account sourceAccount = bank.getAccount(fromId);
		if (sourceAccount == null) {
			return false;
		}

		Account destinationAccount = bank.getAccount(toId);
		if (destinationAccount == null) {
			return false;
		}

		if (destinationAccount == sourceAccount) {
			return false;
		}

		String accountType = bank.getAccountByID(fromId).getAccountType();
		String accountType2 = bank.getAccountByID(toId).getAccountType();
		if (accountType == "Cd" || accountType2 == "Cd") {
			return false;
		}

		return true;
	}
}
