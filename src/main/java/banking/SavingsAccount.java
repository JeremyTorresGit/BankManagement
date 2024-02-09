package banking;

public class SavingsAccount extends Account {
	private static final int MAX_WITHDRAWAL_PER_MONTH = 1;

	public SavingsAccount(String id, double apr) {
		super(id, apr);
		this.AccountType = "Savings";
		this.withdrawalsThisMonth = 0;
	}

	@Override
	public void resetWithdrawals() {
		withdrawalsThisMonth = 0;
	}

	@Override
	public Integer getWithdrawalsThisMonth() {
		return withdrawalsThisMonth;
	}

	public boolean canWithdraw(double amount) {

		if (withdrawalsThisMonth >= MAX_WITHDRAWAL_PER_MONTH) {
			return false;
		}
		return true;
	}

	@Override
	public void withdraw(double amount) {
		if (canWithdraw(amount)) {
			super.withdraw(amount);
			withdrawalsThisMonth++;
		}
	}
}