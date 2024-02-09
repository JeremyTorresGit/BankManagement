package banking;

public class DepositCommandProcessor extends CommandProcessor {
	private Bank bank;

	public DepositCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void processDepositCommand(String[] parts) {
		String accountId = parts[1];
		double amount = Double.parseDouble(parts[2]);

		Account account = bank.getAccount(accountId);
		if (account != null) {
			account.deposit(amount);
		}
	}
}
