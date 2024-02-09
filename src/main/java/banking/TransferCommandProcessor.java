package banking;

public class TransferCommandProcessor {
	private Bank bank;

	public TransferCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void processTransferCommand(String[] parts) {
		String fromId = parts[1];
		String toId = parts[2];
		double amount = Double.parseDouble(parts[3]);

		Account account = bank.getAccount(fromId);
		Account account2 = bank.getAccount(toId);
		if (account != null && account2 != null) {
			account.transfer(account2, amount);
		}
	}
}
