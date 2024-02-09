package banking;

public class PassTimeCommandProcessor extends CommandProcessor {
	private Bank bank;

	public PassTimeCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void processPassTimeCommand(String[] parts) {
		int months = Integer.parseInt(parts[1]);

		bank.passTime(months);
	}
}
