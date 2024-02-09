package banking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandValidator {
	private Bank bank;
	private CreateCommandValidator createCommandValidator;
	private DepositCommandValidator depositCommandValidator;
	private WithdrawCommandValidator withdrawCommandValidator;
	private TransferCommandValidator transferCommandValidator;
	private PassTimeCommandValidator passTimeCommandValidator;

	public CommandValidator() {
	}

	public CommandValidator(Bank bank) {
		this.bank = bank;
		createCommandValidator = new CreateCommandValidator(bank);
		depositCommandValidator = new DepositCommandValidator(bank);
		withdrawCommandValidator = new WithdrawCommandValidator(bank);
		transferCommandValidator = new TransferCommandValidator(bank);
		passTimeCommandValidator = new PassTimeCommandValidator(bank);
	}

	public static boolean isValidWhitespace(String command) {
		String regex = "^(\\S+\\s*)*$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(command);

		return matcher.matches();
	}

	// functions to test validation of each category

	public boolean isValidCommand(String oldCommand) {
		String lowerCaseString = oldCommand.toLowerCase();

		boolean isValid = isValidWhitespace(lowerCaseString);
		if (!(isValid)) {
			return false;
		}

		if (oldCommand.trim().isEmpty()) {
			// Handle the case where the command is null or empty
			return false;
		}

		String[] parts = lowerCaseString.split(" ");
		if ((parts.length < 2) || (parts.length > 5)) {
			return false;

		}

		String action = parts[0];

		switch (action) {
		case "create":
			return createCommandValidator.validateCreateCommand(parts);
		case "deposit":
			return depositCommandValidator.validateDepositCommand(parts);
		case "withdraw":
			return withdrawCommandValidator.validateWithdrawCommand(parts);
		case "transfer":
			return transferCommandValidator.validateTransferCommand(parts);
		case "pass":
			return passTimeCommandValidator.validatePassTimeCommand(parts);
		default:
			return false;
		}
	}

	public boolean isValidAccountId(String accountId) {
		try {
			int id = Integer.parseInt(accountId);
			return id >= 0 && accountId.length() == 8;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isValidApr(String accountApr) {
		try {
			double apr = Double.parseDouble(accountApr);
			return apr >= 0 && apr <= 10;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isValidAmount(String amount) {
		try {
			int decimalPointIndex = amount.indexOf(".");

			if (decimalPointIndex != -1) {
				int decimalPlaces = amount.length() - decimalPointIndex - 1;
				return decimalPlaces <= 2;
			}

			double amt = Double.parseDouble(amount);

			return amt >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isValidAmountForCD(String amount) {
		try {
			double amt = Double.parseDouble(amount);

			return amt >= 1000 && amt <= 10000;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}