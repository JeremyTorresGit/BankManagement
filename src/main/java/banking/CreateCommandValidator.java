package banking;

public class CreateCommandValidator extends CommandValidator {
	private Bank bank;

	public CreateCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validateCreateCommand(String[] parts) {

		String accountType = parts[1];
		String accountId = parts[2];

		if (bank.getAccounts().containsKey(accountId)) {
			return false;
		}

		if (parts.length == 3) {
			return false;
		}

		String accountApr = parts[3];

		if (parts.length == 4) {
			if ("cd".equals(accountType)) {
				return false;
			}
			if (!isValidAccountId(accountId) || !isValidApr(accountApr)) {
				return false;
			}
		}
		if (parts.length == 5) {
			String cdInitBalance = parts[4];
			if ((!isValidAccountId(accountId) || !isValidApr(accountApr)) || !isValidAmountForCD(cdInitBalance)) {
				return false;
			}
		}
		return true;
	}

}
