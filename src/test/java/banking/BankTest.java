package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
	Bank bank;
	CheckingAccount account;
	SavingsAccount account2;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		account = new CheckingAccount("12345678", 4.0);
		account2 = new SavingsAccount("00000001", 5.0);
	}

	@Test
	public void create_bank_with_no_accounts() {
		assertEquals(0, bank.getAccounts().size());
	}

	@Test
	public void add_account_to_bank() {
		bank.addAccount(account);
		assertEquals(1, bank.getAccounts().size());
	}

	@Test
	public void add_two_accounts_to_bank() {
		bank.addAccount(account);
		bank.addAccount(account2);
		assertEquals(2, bank.getAccounts().size());
	}

	@Test
	public void retrieve_account_from_bank() {
		bank.addAccount(account);
		Account retrievedAccount = bank.getAccount("12345678");
		assertEquals(account, retrievedAccount);
	}

	@Test
	public void deposit_through_bank_by_ID() {
		bank.addAccount(account);
		bank.deposit("12345678", 100.0);
		assertEquals(100.0, account.getBalance());
	}

	@Test
	public void withdraw_through_bank_by_ID() {
		bank.addAccount(account);
		account.deposit(100.0);
		bank.withdraw("12345678", 25.0);
		assertEquals(75.0, account.getBalance());
	}

	@Test
	public void deposit_twice_through_bank() {
		bank.addAccount(account);
		bank.deposit("12345678", 100.0);
		bank.deposit("12345678", 50.0);
		assertEquals(150.0, account.getBalance());
	}

	@Test
	public void withdraw_twice_through_bank() {
		bank.addAccount(account);
		account.deposit(100.0);
		bank.withdraw("12345678", 30.0);
		bank.withdraw("12345678", 20.0);
		assertEquals(50.0, account.getBalance());
	}

	@Test
	public void S_withdraw_through_bank() {
		bank.addAccount(account2);
		account2.deposit(100.0);
		bank.withdraw("00000001", 10.0);
		assertEquals(90.0, account2.getBalance());
	}

	@Test
	public void S_withdraw_twice_through_bank() {
		bank.addAccount(account2);
		account2.deposit(100.0);
		bank.withdraw("00000001", 30.0);
		bank.withdraw("00000001", 20.0);
		assertEquals(70.0, account2.getBalance());
	}

	@Test
	public void CD_withdraw_through_bank() {
		CDAccount cdAccount = new CDAccount("99999999", 4.0, 1000);
		bank.addAccount(cdAccount);
		bank.passTime(12);
		bank.withdraw("99999999", 1173.1986699758538);
		assertEquals(0.0, cdAccount.getBalance());
	}

	@Test
	public void transfer_through_bank() {
		bank.addAccount(account);
		account.deposit(100.0);
		bank.addAccount(account2);
		account2.deposit(100.0);
		bank.transfer("12345678", "00000001", 10);
		assertEquals(90.0, account.getBalance());
	}

	@Test
	public void transfer_with_an_amount_larger_than_balance_through_bank() {
		bank.addAccount(account);
		account.deposit(100.0);
		bank.addAccount(account2);
		account2.deposit(100.0);
		bank.transfer("12345678", "00000001", 200);
		assertEquals(0.0, account.getBalance());
	}

	@Test
	public void pass_time_checking() {
		CheckingAccount checkingAccount3 = new CheckingAccount("12563478", 3.0);
		bank.addAccount(checkingAccount3);
		checkingAccount3.deposit(1000.0);
		bank.passTime(1);
		assertEquals(1002.5, checkingAccount3.getBalance());
	}

	@Test
	public void pass_time_savings() {
		SavingsAccount savingsAccount = new SavingsAccount("12345678", 0.6);
		bank.addAccount(savingsAccount);
		savingsAccount.deposit(1000.0);
		bank.passTime(1);
		assertEquals(1000.50, savingsAccount.getBalance());
	}

	@Test
	public void pass_time_cd() {
		CDAccount cdAccount = new CDAccount("99999999", 2.1, 2000);
		bank.addAccount(cdAccount);
		bank.passTime(1);
		assertEquals(2014.0367928937578, cdAccount.getBalance());
	}

	@Test
	public void pass_time_cd2() {
		CDAccount cdAccount = new CDAccount("99999999", 2.1, 2000);
		bank.addAccount(cdAccount);
		bank.passTime(12);
		cdAccount.withdraw(30000);
		assertEquals(0, cdAccount.getBalance());
	}

	@Test
	public void pass_time_checking2() {
		CheckingAccount checkingAccount3 = new CheckingAccount("12563478", 3.0);
		bank.addAccount(checkingAccount3);
		checkingAccount3.deposit(1000.0);
		checkingAccount3.withdraw(1000.0);
		bank.passTime(1);
		assertEquals(null, bank.getAccount("12563478"));
	}
}
