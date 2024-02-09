package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
	CheckingAccount checkingAccount;
	SavingsAccount savingsAccount;

	@BeforeEach
	public void setUp() {
		checkingAccount = new CheckingAccount("12345678", 4.0);
		savingsAccount = new SavingsAccount("00000001", 4.0);
	}

	@Test
	public void initialize_APR_in_account() {
		assertEquals(4.0, checkingAccount.getApr());
	}

	@Test
	public void deposit_increases_balance_in_account() {
		checkingAccount.deposit(100.0);
		assertEquals(100.0, checkingAccount.getBalance());
	}

	@Test
	public void withdraw_decreases_balance_in_account() {
		checkingAccount.deposit(100.0);
		checkingAccount.withdraw(50.0);
		assertEquals(50.0, checkingAccount.getBalance());
	}

	@Test
	public void withdraw_from_below_zero_balance_in_account() {
		checkingAccount.deposit(100.0);
		checkingAccount.withdraw(150.0);
		assertEquals(0.0, checkingAccount.getBalance());
	}

	@Test
	public void deposit_into_same_account_twice() {
		checkingAccount.deposit(100.0);
		checkingAccount.deposit(50.0);
		assertEquals(150.0, checkingAccount.getBalance());
	}

	@Test
	public void withdraw_from_same_account_twice() {
		checkingAccount.deposit(100.0);
		checkingAccount.withdraw(30.0);
		checkingAccount.withdraw(20.0);
		assertEquals(50.0, checkingAccount.getBalance());
	}

	@Test
	public void transfer_check_fromId() {
		checkingAccount.deposit(100.0);
		savingsAccount.deposit(100.0);
		checkingAccount.transfer(savingsAccount, 20);
		assertEquals(80.0, checkingAccount.getBalance());
	}

	@Test
	public void transfer_check_toId() {
		checkingAccount.deposit(100.0);
		savingsAccount.deposit(100.0);
		checkingAccount.transfer(savingsAccount, 20);
		assertEquals(120.0, savingsAccount.getBalance());
	}

	@Test
	public void passTime() {
		savingsAccount.deposit(100.0);
		savingsAccount.passTime(1);
		assertEquals(100.33333333333333, savingsAccount.getBalance());
	}

	@Test
	public void passTime2() {
		savingsAccount.deposit(100.0);
		savingsAccount.withdraw(100);
		savingsAccount.passTime(1);
		assertEquals(0, savingsAccount.getBalance());
	}

}
