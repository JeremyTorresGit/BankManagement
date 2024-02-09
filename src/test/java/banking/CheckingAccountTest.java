package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CheckingAccountTest {
	@Test
	public void initialize_balance_in_checking_and_savings_account() {
		CheckingAccount checkingAccount = new CheckingAccount("12345678", 4.0);
		assertEquals(0.0, checkingAccount.getBalance());
	}
}
