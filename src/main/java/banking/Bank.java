package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
	private Map<String, Account> accounts;

	public Bank() {
		accounts = new HashMap<>();
	}

	public void addAccount(Account account) {
		accounts.put(account.getId(), account);
	}

	public Account getAccount(String id) {
		return accounts.get(id);
	}

	public Map<String, Account> getAccounts() {
		return accounts;
	}

	public void deposit(String id, double amount) {
		Account account = accounts.get(id);
		if (account != null) {
			account.deposit(amount);
		}
	}

	public void withdraw(String id, double amount) {
		Account account = accounts.get(id);
		if (account != null) {
			account.withdraw(amount);
		}
	}

	public void transfer(String fromId, String toId, double amount) {
		Account sourceAccount = accounts.get(fromId);
		Account destinationAccount = accounts.get(toId);

		if (sourceAccount != null && destinationAccount != null) {
			if (!(sourceAccount.getBalance() >= amount)) {
				amount = sourceAccount.getBalance();
			}
			sourceAccount.withdraw(amount);
			destinationAccount.deposit(amount);
		}
	}

	public void passTime(int months) {
		List<String> accountsToBeRemoved = new ArrayList<>();
		for (Account account : accounts.values()) {
			account.calculateAPR(months);

			if (account.getBalance() < 100) {
				account.withdraw(25);
			}

			if (account.getBalance() <= 0) {
				accountsToBeRemoved.add(account.getId());
			}

			if ("Savings".equals(account.getAccountType())) {
				account.resetWithdrawals();
			}

			if ("Cd".equals(account.getAccountType())) {
				account.incrementMonthsSinceCreation(months);
			}
		}
		for (String id : accountsToBeRemoved) {
			accounts.remove(id, getAccount(id));
		}
	}

	public Account getAccountByID(String accountId) {
		return accounts.get(accountId);
	}

	public Account getBalance(String accountId) {
		return accounts.get(accountId);
	}
}