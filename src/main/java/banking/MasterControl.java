package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class MasterControl {
	private CommandValidator commandValidator;
	private CommandProcessor commandProcessor;
	private CommandStorage commandStorage;

	public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor,
			CommandStorage commandStorage) {
		this.commandValidator = commandValidator;
		this.commandProcessor = commandProcessor;
		this.commandStorage = commandStorage;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (commandValidator.isValidCommand(command)) {
				commandProcessor.processCommand(command);
				commandStorage.addValidCommand(command);
				if (command.toLowerCase().contains("create")) {
					commandStorage.storeID(command.split("\\s+")[2]);
				}
			} else {
				commandStorage.addInvalidCommand(command);
			}
		}

		for (String id : commandStorage.getIDs()) {
			if (commandProcessor.getAccount(id) != null) {

				DecimalFormat decimalFormat = new DecimalFormat("0.00");
				decimalFormat.setRoundingMode(RoundingMode.FLOOR);

				Account account = commandProcessor.getAccount(id);
				String accountType = account.getAccountType().toLowerCase();
				accountType = accountType.substring(0, 1).toUpperCase() + accountType.substring(1);
				String accountId = account.getId();
				String balance = decimalFormat.format(account.getBalance());
				String apr = decimalFormat.format(account.getApr());

				String result = accountType + " " + accountId + " " + balance + " " + apr;

				commandStorage.storeFinalCommand(result);

				for (String command : commandStorage.getValidCommands()) {
					if (command.contains(id) && !command.toLowerCase().contains("create")) {
						commandStorage.storeFinalCommand(command);
					}
				}
			}
		}

		for (String invalidCommand : commandStorage.getAllInvalidCommands()) {
			commandStorage.storeFinalCommand(invalidCommand);
		}
		return commandStorage.getFinalList();
	}

}