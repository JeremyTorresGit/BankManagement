package banking;

public class CommandProcessor {
	private Bank bank;
	private CreateCommandProcessor createCommandProcessor;
	private DepositCommandProcessor depositCommandProcessor;
	private WithdrawCommandProcessor withdrawCommandProcessor;
	private TransferCommandProcessor transferCommandProcessor;
	private PassTimeCommandProcessor passTimeCommandProcessor;

	public CommandProcessor() {
	}

	public CommandProcessor(Bank bank) {
		this.bank = bank;
		createCommandProcessor = new CreateCommandProcessor(bank);
		depositCommandProcessor = new DepositCommandProcessor(bank);
		withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
		transferCommandProcessor = new TransferCommandProcessor(bank);
		passTimeCommandProcessor = new PassTimeCommandProcessor(bank);
	}

	public Account getAccount(String id) {
		return bank.getAccount(id);
	}

	public void processCommand(String createCommand) {
		String lowerCaseString = createCommand.toLowerCase();
		String[] parts = lowerCaseString.split(" ");

		switch (parts[0]) {
		case "create":
			createCommandProcessor.processCreateCommand(parts);
			break;
		case "deposit":
			depositCommandProcessor.processDepositCommand(parts);
			break;
		case "withdraw":
			withdrawCommandProcessor.processWithdrawCommand(parts);
			break;
		case "transfer":
			transferCommandProcessor.processTransferCommand(parts);
			break;
		case "pass":
			passTimeCommandProcessor.processPassTimeCommand(parts);
			break;
		default:
			return;
		}
	}
}
