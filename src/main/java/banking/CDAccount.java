package banking;

public class CDAccount extends Account {
	private static final int MATURITY_PERIOD = 12;

	public CDAccount(String id, double apr, double initialBalance) {
		super(id, apr);
		this.setBalance(initialBalance);
		this.AccountType = "Cd";
		this.monthsSinceCreation = 0;
	}

	@Override
	public void deposit(double amount) {
	}

	@Override
	public Integer getMonthsSinceCreation() {
		return monthsSinceCreation;
	}

	@Override
	public Integer incrementMonthsSinceCreation(int months) {
		monthsSinceCreation += months;
		return monthsSinceCreation;
	}

	public boolean hasMatured() {
		return monthsSinceCreation >= MATURITY_PERIOD;
	}

	@Override
	public void calculateAPR(int months) {
		double monthlyAPR = getApr() / 100 / 12;

		for (int i = 0; i < months; i++) {
			for (int j = 0; j < 4; j++) {
				double interest = getBalance() * monthlyAPR;
				addCdApr(interest);
			}
		}
	}

	@Override
	public void withdraw(double amount) {
		if (hasMatured()) {
			if (amount >= getBalance()) {
				setBalance(0);
			}
		}
	}
}