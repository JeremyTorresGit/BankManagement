package banking;

public class CheckingAccount extends Account {
	public CheckingAccount(String id, double apr) {
		super(id, apr);
		this.AccountType = "Checking";
	}

}