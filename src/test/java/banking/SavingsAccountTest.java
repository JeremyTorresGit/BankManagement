package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SavingsAccountTest {
	@Test
	public void initialize_balance_in_savings_account() {
		SavingsAccount savingsAccount = new SavingsAccount("00000001", 4.0);
		assertEquals(0.0, savingsAccount.getBalance());
	}

	@Test
	public void deposit_increases_balance_in_savings_account() {
		SavingsAccount savingsAccount = new SavingsAccount("00000001", 4.0);
		savingsAccount.deposit(100.0);
		assertEquals(100.0, savingsAccount.getBalance());
	}
}