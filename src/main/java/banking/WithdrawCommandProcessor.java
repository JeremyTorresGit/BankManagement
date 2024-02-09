package banking;

public class WithdrawCommandProcessor extends CommandProcessor {
	private Bank bank;

	public WithdrawCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void processWithdrawCommand(String[] parts) {
		String accountId = parts[1];
		double amount = Double.parseDouble(parts[2]);

		Account account = bank.getAccount(accountId);
		if (account != null) {
			account.withdraw(amount);
		}
	}

}
