package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CDAccountTest {

	@Test
	public void initialize_balance_in_CD_account() {
		CDAccount cdAccount = new CDAccount("32323232", 4.0, 1000.0);
		assertEquals(1000.0, cdAccount.getBalance());
	}

	@Test
	public void deposit_does_not_change_a_CD_account() {
		CDAccount cdAccount = new CDAccount("12345678", 4.0, 200);
		cdAccount.deposit(100.0);
		assertEquals(200.0, cdAccount.getBalance());

	}
}