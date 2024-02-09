package banking;

public class PassTimeCommandValidator extends CommandValidator {
	private Bank bank;

	public PassTimeCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validatePassTimeCommand(String[] parts) {

		if (parts.length != 2) {
			return false;
		}

		String months = parts[1];

		try {
			Integer mths = Integer.parseInt(months);
			if (mths < 1 || mths >= 61) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}
}
