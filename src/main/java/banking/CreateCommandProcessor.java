package banking;

public class CreateCommandProcessor extends CommandProcessor {
	private Bank bank;

	public CreateCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void processCreateCommand(String[] parts) {
		String accountType = parts[1];
		String accountId = parts[2];
		double apr = Double.parseDouble(parts[3]);

		if ("checking".equals(accountType)) {
			CheckingAccount checkingAccount = new CheckingAccount(accountId, apr);
			bank.addAccount(checkingAccount);
		}
		if ("savings".equals(accountType)) {
			SavingsAccount savingsAccount = new SavingsAccount(accountId, apr);
			bank.addAccount(savingsAccount);
		}
		if ("cd".equals(accountType)) {
			double accountInitBal = Double.parseDouble(parts[4]);
			CDAccount cdAccount = new CDAccount(accountId, apr, accountInitBal);
			bank.addAccount(cdAccount);
		}
	}
}
