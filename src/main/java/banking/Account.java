package banking;

public abstract class Account {
	protected String AccountType;
	protected Integer monthsSinceCreation;
	protected Integer withdrawalsThisMonth;
	private String id;
	private double balance;
	private double apr;

	public Account(String id, double apr) {
		this.id = id;
		this.balance = 0.0;
		this.apr = apr;
	}

	public String getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getApr() {
		return apr;
	}

	public String getAccountType() {
		return this.AccountType;
	}

	public Integer getWithdrawalsThisMonth() {
		return this.withdrawalsThisMonth;
	}

	public Integer getMonthsSinceCreation() {
		return this.monthsSinceCreation;
	}

	public Integer incrementMonthsSinceCreation(int months) {
		this.monthsSinceCreation += months;
		return this.monthsSinceCreation;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void addCdApr(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		if (balance >= amount) {
			balance -= amount;
		} else {
			balance = 0;
		}
	}

	public void transfer(Account destinationAccount, double amount) {
		this.withdraw(amount);
		destinationAccount.deposit(amount);
	}

	public void passTime(int months) {
		double monthlyAPR = getApr() / 100 / 12;

		for (int i = 0; i < months; i++) {
			double interest = getBalance() * monthlyAPR;
			deposit(interest);
		}
	}

	public void calculateAPR(int months) {
		double monthlyAPR = getApr() / 100 / 12;

		for (int i = 0; i < months; i++) {
			double interest = getBalance() * monthlyAPR;
			deposit(interest);
		}
	}

	public void resetWithdrawals() {
		return;
	}
}