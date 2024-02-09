package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator validator;
	Bank bank;
	CheckingAccount checkingAccount;
	SavingsAccount savingsAccount;
	CDAccount cdAccount;
	CheckingAccount checkingAccount2;
	SavingsAccount savingsAccount2;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		validator = new CommandValidator(bank);
		checkingAccount = new CheckingAccount("12345678", 4.0);
		savingsAccount = new SavingsAccount("00000001", 4.0);
		cdAccount = new CDAccount("99999999", 4.0, 1000);
		checkingAccount2 = new CheckingAccount("87654321", 4.0);
		savingsAccount2 = new SavingsAccount("10000000", 4.0);
	}

	@Test
	public void parts_test() {
		assertFalse(validator.isValidCommand("create checking 12345678 0 77"));
	}

	@Test
	public void parts_test_2() {
		assertFalse(validator.isValidCommand(""));
	}

	@Test
	public void parts_test_3() {
		assertFalse(validator.isValidCommand("create "));
	}

	@Test
	public void test_valid_create_account_command_1() {
		assertTrue(validator.isValidCommand("create checking 12345678 0"));
	}

	@Test
	public void test_valid_create_account_command_2() {
		assertTrue(validator.isValidCommand("create checking 12345678 0.3"));
	}

	@Test
	public void test_valid_create_account_command_3() {
		assertTrue(validator.isValidCommand("create checking 12345678 1.50"));
	}

	@Test
	public void test_valid_create_account_command_4() {
		assertTrue(validator.isValidCommand("create checking 12345678 1.3333"));
	}

	@Test
	public void test_valid_create_account_command_5() {
		assertTrue(validator.isValidCommand("create checking 12345678 9"));
	}

	@Test
	public void test_valid_create_account_command_6() {
		assertTrue(validator.isValidCommand("create checking 12345678 10"));
	}

	@Test
	public void test_invalid_create_account_command_7() {
		assertFalse(validator.isValidCommand("create checking 12345678 11"));
	}

	@Test
	public void test_invalid_create_account_command_8() {
		assertFalse(validator.isValidCommand("create checking 12345678 1000"));
	}

	@Test
	public void test_invalid_create_account_command_9() {
		assertFalse(validator.isValidCommand("create checking 12345678 -1"));
	}

	@Test
	public void test_invalid_create_account_command_10() {
		assertFalse(validator.isValidCommand("create checking 12345678 a"));
	}

	@Test
	public void test_invalid_create_account_command_11() {
		assertFalse(validator.isValidCommand("create checking 12345678 @"));
	}

	@Test
	public void test_invalid_create_account_command_12() {
		assertFalse(validator.isValidCommand("create checking 1234567 4.0"));
	}

	@Test
	public void test_valid_create_account_command_13() {
		assertTrue(validator.isValidCommand("create checking 12345678 4.0"));
	}

	@Test
	public void test_invalid_create_account_command_14() {
		assertFalse(validator.isValidCommand("create checking 123456789 4.0"));
	}

	@Test
	public void test_invalid_create_account_command_15() {
		assertFalse(validator.isValidCommand("create checking 12345678.0 4.0"));
	}

	@Test
	public void test_invalid_create_account_command_16() {
		assertFalse(validator.isValidCommand("create checking -12345678 4.0"));
	}

	@Test
	public void test_invalid_create_account_command_17() {
		assertFalse(validator.isValidCommand("create checking 1234567a 4.0"));
	}

	@Test
	public void test_invalid_create_account_command_18() {
		assertFalse(validator.isValidCommand("create checking 1234567@ 4.0"));
	}

	@Test
	public void test_valid_create_account_command_19() {
		assertTrue(validator.isValidCommand("CreaTe chECKiNg 12345678 4.0"));
	}

	@Test
	public void test_valid_create_account_command_20() {
		assertFalse(validator.isValidCommand("create     checking        12345678          4.0"));
	}

	@Test
	public void test_valid_create_account_command_21() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("create checking 12345678 4.0"));
	}

	@Test
	public void test_valid_create_account_command_22() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("create checking 00000001 4.0"));
	}

	@Test
	public void test_invalid_create_checking_account_command_missing_command() {
		assertFalse(validator.isValidCommand("checking 12345678 4.0"));
	}

	@Test
	public void test_invalid_create_checking_account_command_missing_account_type() {
		assertFalse(validator.isValidCommand("create 12345678 4.0"));
	}

	@Test
	public void test_invalid_create_checking_account_command_missing_id() {
		assertFalse(validator.isValidCommand("create checking 4.0"));
	}

	@Test
	public void test_invalid_create_checking_account_command_missing_apr() {
		assertFalse(validator.isValidCommand("create checking 12345678"));
	}

	// create cd tests

	@Test
	public void test_invalid_create_account_command_48() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0 -1"));
	}

	@Test
	public void test_invalid_create_account_command_49() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0 0"));
	}

	@Test
	public void test_invalid_create_account_command_50() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0 1"));
	}

	@Test
	public void test_invalid_create_account_command_53() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0 100"));
	}

	@Test
	public void test_invalid_create_account_command_min_boundary_1() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0 999"));
	}

	@Test
	public void test_valid_create_account_command_54() {
		assertTrue(validator.isValidCommand("create CD 12345678 4.0 1000"));
	}

	@Test
	public void test_valid_create_account_command_51() {
		assertTrue(validator.isValidCommand("create CD 12345678 4.0 1000.3"));
	}

	@Test
	public void test_valid_create_account_command_52() {
		assertTrue(validator.isValidCommand("create CD 12345678 4.0 1000.3333"));
	}

	@Test
	public void test_valid_create_account_command_max_boundary_1() {
		assertTrue(validator.isValidCommand("create CD 12345678 4.0 9999"));
	}

	@Test
	public void test_valid_create_account_command_max_boundary_2() {
		assertTrue(validator.isValidCommand("create CD 12345678 4.0 10000"));
	}

	@Test
	public void test_invalid_create_account_command_max_boundary_3() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0 10001"));
	}

	@Test
	public void test_invalid_create_account_command_55() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0 10000000000"));
	}

	@Test
	public void test_invalid_create_account_command_56() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0 100a"));
	}

	@Test
	public void test_invalid_create_account_command_57() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0 100@"));
	}

	@Test
	public void test_invalid_create_CD_account_command_missing_command() {
		assertFalse(validator.isValidCommand("CD 12345678 4.0 1000"));
	}

	@Test
	public void test_invalid_create_CD_account_command_missing_account_type() {
		assertFalse(validator.isValidCommand("create 12345678 4.0 1000"));
	}

	@Test
	public void test_invalid_create_CD_account_command_missing_id() {
		assertFalse(validator.isValidCommand("create CD 4.0 1000"));
	}

	@Test
	public void test_invalid_create_CD_account_command_missing_apr() {
		assertFalse(validator.isValidCommand("create CD 12345678 1000"));
	}

	@Test
	public void test_invalid_create_CD_account_command_missing_initial_balance() {
		assertFalse(validator.isValidCommand("create CD 12345678 4.0"));
	}

// Validation Deposit Command Tests

	// Checking Deposit Validation Tests

	@Test
	public void test_deposit_command_1() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 100"));
	}

	@Test
	public void test_deposit_command_2() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 12345678 -1"));
	}

	@Test
	public void test_deposit_command_3() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 0"));
	}

	@Test
	public void test_deposit_command_4() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 1"));
	}

	@Test
	public void test_deposit_command_5() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 0.3"));
	}

	@Test
	public void test_deposit_command_6() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 0.33"));
	}

	@Test
	public void test_deposit_command_7() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 12345678 0.333"));
	}

	@Test
	public void test_deposit_command_8() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 12345678 0.3333"));
	}

	@Test
	public void test_deposit_command_9() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 100"));
	}

	@Test
	public void test_deposit_command_10() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 1000"));
	}

	@Test
	public void test_deposit_command_11() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 2000"));
	}

	@Test
	public void test_deposit_command_12() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 2499"));
	}

	@Test
	public void test_deposit_command_13() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 2500"));
	}

	@Test
	public void test_deposit_command_14() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 12345678 2501"));
	}

	@Test
	public void test_deposit_command_15() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 12345678 a"));
	}

	@Test
	public void test_deposit_command_16() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 12345678 @"));
	}

	@Test
	public void test_deposit_command_17() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 1234567 100"));
	}

	@Test
	public void test_deposit_command_18() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("deposit 12345678 100"));
	}

	@Test
	public void test_deposit_command_19() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 123456789 100"));
	}

	@Test
	public void test_deposit_command_20() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 12345678.0 100"));
	}

	@Test
	public void test_deposit_command_21() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit -12345678 100"));
	}

	@Test
	public void test_deposit_command_22() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 1234567a 100"));
	}

	@Test
	public void test_deposit_command_23() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("deposit 1234567@ 100"));
	}

	@Test
	public void test_deposit_command_24() {
		bank.addAccount(checkingAccount);
		assertTrue(validator.isValidCommand("dePosIt 12345678 100"));
	}

	@Test
	public void test_deposit_command_25() {
		bank.addAccount(checkingAccount);
		assertFalse(validator.isValidCommand("dePosIt    12345678    100"));
	}

	// Savings Deposit Validation Tests

	@Test
	public void test_deposit_command_26() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 100"));
	}

	@Test
	public void test_deposit_command_27() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 00000001 -1"));
	}

	@Test
	public void test_deposit_command_28() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 0"));
	}

	@Test
	public void test_deposit_command_29() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 1"));
	}

	@Test
	public void test_deposit_command_30() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 0.3"));
	}

	@Test
	public void test_deposit_command_31() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 0.33"));
	}

	@Test
	public void test_deposit_command_32() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 00000001 0.333"));
	}

	@Test
	public void test_deposit_command_33() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 00000001 0.3333"));
	}

	@Test
	public void test_deposit_command_34() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 100"));
	}

	@Test
	public void test_deposit_command_35() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 1000"));
	}

	@Test
	public void test_deposit_command_36() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 00000001 2000"));
	}

	@Test
	public void test_deposit_command_37() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 999"));
	}

	@Test
	public void test_deposit_command_38() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 1000"));
	}

	@Test
	public void test_deposit_command_39() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 00000001 1001"));
	}

	@Test
	public void test_deposit_command_40() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 00000001 a"));
	}

	@Test
	public void test_deposit_command_41() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 00000001 @"));
	}

	@Test
	public void test_deposit_command_42() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 0000001 100"));
	}

	@Test
	public void test_deposit_command_43() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("deposit 00000001 100"));
	}

	@Test
	public void test_deposit_command_44() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 000000012 100"));
	}

	@Test
	public void test_deposit_command_45() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 00000001.0 100"));
	}

	@Test
	public void test_deposit_command_46() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit -00000001 100"));
	}

	@Test
	public void test_deposit_command_47() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 0000000a 100"));
	}

	@Test
	public void test_deposit_command_48() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 0000000@ 100"));
	}

	@Test
	public void test_deposit_command_49() {
		bank.addAccount(savingsAccount);
		assertTrue(validator.isValidCommand("dePosIt 00000001 100"));
	}

	@Test
	public void test_deposit_command_50() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("dePosIt    00000001    100"));
	}

	@Test
	public void test_deposit_command_51() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit"));
	}

	@Test
	public void test_deposit_command_52() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 00000001 100 12"));
	}

	@Test
	public void test_deposit_command_53() {
		bank.addAccount(savingsAccount);
		assertFalse(validator.isValidCommand("deposit 12312312 100"));
	}

	// CD Deposit Validation Tests

	@Test
	public void test_CD_deposit_command() {
		CDAccount cdAccount = new CDAccount("00000001", 4.0, 200);
		bank.addAccount(cdAccount);
		assertFalse(validator.isValidCommand("deposit 00000001 1000"));
	}

// withdrawal validation tests

	@Test
	public void test_valid_withdraw_command() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 50"));
	}

	@Test
	public void test_valid_withdraw_command_2() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678 -1"));
	}

// Validation Withdraw Command Tests

	// Checking Withdraw Validation Tests

	@Test
	public void test_withdraw_command_1() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 100"));
	}

	@Test
	public void test_withdraw_command_2() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678 -1"));
	}

	@Test
	public void test_withdraw_command_3() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 0"));
	}

	@Test
	public void test_withdraw_command_4() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 1"));
	}

	@Test
	public void test_withdraw_command_5() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 0.3"));
	}

	@Test
	public void test_withdraw_command_6() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 0.33"));
	}

	@Test
	public void test_withdraw_command_7() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678 0.333"));
	}

	@Test
	public void test_withdraw_command_8() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678 0.3333"));
	}

	@Test
	public void test_withdraw_command_9() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 100"));
	}

	@Test
	public void test_withdraw_command_10() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678 1000"));
	}

	@Test
	public void test_withdraw_command_11() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678 10000"));
	}

	@Test
	public void test_withdraw_command_12() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 399"));
	}

	@Test
	public void test_withdraw_command_13() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 400"));
	}

	@Test
	public void test_withdraw_command_14() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678 401"));
	}

	@Test
	public void test_withdraw_command_15() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678 a"));
	}

	@Test
	public void test_withdraw_command_16() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678 @"));
	}

	@Test
	public void test_withdraw_command_17() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 1234567 100"));
	}

	@Test
	public void test_withdraw_command_18() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("withdraw 12345678 100"));
	}

	@Test
	public void test_withdraw_command_19() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 123456789 100"));
	}

	@Test
	public void test_withdraw_command_20() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 12345678.0 100"));
	}

	@Test
	public void test_withdraw_command_21() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw -12345678 100"));
	}

	@Test
	public void test_withdraw_command_22() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 1234567a 100"));
	}

	@Test
	public void test_withdraw_command_23() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw 1234567@ 100"));
	}

	@Test
	public void test_withdraw_command_24() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertTrue(validator.isValidCommand("wiTHdrAw 12345678 100"));
	}

	@Test
	public void test_withdraw_command_25() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("withdraw    12345678    100"));
	}

	// Savings Withdraw Validation Tests

	@Test
	public void test_withdraw_command_26() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 100"));
	}

	@Test
	public void test_withdraw_command_27() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 00000001 -1"));
	}

	@Test
	public void test_withdraw_command_28() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 0"));
	}

	@Test
	public void test_withdraw_command_29() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 1"));
	}

	@Test
	public void test_withdraw_command_30() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 0.3"));
	}

	@Test
	public void test_withdraw_command_31() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 0.33"));
	}

	@Test
	public void test_withdraw_command_32() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 00000001 0.333"));
	}

	@Test
	public void test_withdraw_command_33() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 00000001 0.3333"));
	}

	@Test
	public void test_withdraw_command_34() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 100"));
	}

	@Test
	public void test_withdraw_command_35() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 1000"));
	}

	@Test
	public void test_withdraw_command_36() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 00000001 2000"));
	}

	@Test
	public void test_withdraw_command_37() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 999"));
	}

	@Test
	public void test_withdraw_command_38() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 1000"));
	}

	@Test
	public void test_withdraw_command_39() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 00000001 1001"));
	}

	@Test
	public void test_withdraw_command_40() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 00000001 a"));
	}

	@Test
	public void test_withdraw_command_41() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 00000001 @"));
	}

	@Test
	public void test_withdraw_command_42() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 0000001 100"));
	}

	@Test
	public void test_withdraw_command_43() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("withdraw 00000001 100"));
	}

	@Test
	public void test_withdraw_command_44() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 000000012 100"));
	}

	@Test
	public void test_withdraw_command_45() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 00000001.0 100"));
	}

	@Test
	public void test_withdraw_command_46() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw -00000001 100"));
	}

	@Test
	public void test_withdraw_command_47() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 0000000a 100"));
	}

	@Test
	public void test_withdraw_command_48() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw 0000000@ 100"));
	}

	@Test
	public void test_withdraw_command_49() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("wiTHdraw 00000001 100"));
	}

	@Test
	public void test_withdraw_command_50() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("withdraw    00000001    100"));
	}

	@Test
	public void test_withdraw_command_pt() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		bank.withdraw("00000001", 10);
		assertFalse(validator.isValidCommand("withdraw 00000001 10"));
	}

	// CD Withdraw Validation Tests

	@Test
	public void test_withdraw_command_51() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 99999999 500"));
	}

	@Test
	public void test_withdraw_command_52() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 99999999 999"));
	}

	@Test
	public void test_withdraw_command_53() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertTrue(validator.isValidCommand("withdraw 99999999 2000"));
	}

	@Test
	public void test_withdraw_command_54() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertTrue(validator.isValidCommand("withdraw 99999999 2001"));
	}

	@Test
	public void test_withdraw_command_55() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertTrue(validator.isValidCommand("withdraw 99999999 2000.3"));
	}

	@Test
	public void test_withdraw_command_56() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertTrue(validator.isValidCommand("withdraw 99999999 2000.33"));
	}

	@Test
	public void test_withdraw_command_57() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 99999999 1000.333"));
	}

	@Test
	public void test_withdraw_command_58() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 99999999 1000.3333"));
	}

	@Test
	public void test_withdraw_command_59() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertTrue(validator.isValidCommand("withdraw 99999999 10000"));
	}

	@Test
	public void test_withdraw_command_60() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertTrue(validator.isValidCommand("withdraw 99999999 100000"));
	}

	@Test
	public void test_withdraw_command_61() {
		bank.addAccount(cdAccount);
		bank.passTime(6);
		assertFalse(validator.isValidCommand("withdraw 99999999 2000"));
	}

	@Test
	public void test_withdraw_command_62() {
		bank.addAccount(cdAccount);
		bank.passTime(11);
		assertFalse(validator.isValidCommand("withdraw 99999999 2000"));
	}

	@Test
	public void test_withdraw_command_63() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertTrue(validator.isValidCommand("withdraw 99999999 2000"));
	}

	@Test
	public void test_withdraw_command_64() {
		bank.addAccount(cdAccount);
		bank.passTime(13);
		assertTrue(validator.isValidCommand("withdraw 99999999 2000"));
	}

	@Test
	public void test_withdraw_command_65() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 99999999 a"));
	}

	@Test
	public void test_withdraw_command_66() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 99999999 @"));
	}

	@Test
	public void test_withdraw_command_67() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 9999999 2000"));
	}

	@Test
	public void test_withdraw_command_68() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertTrue(validator.isValidCommand("withdraw 99999999 2000"));
	}

	@Test
	public void test_withdraw_command_69() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 999999999 2000"));
	}

	@Test
	public void test_withdraw_command_70() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 99999999.0 2000"));
	}

	@Test
	public void test_withdraw_command_71() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw -99999999 2000"));
	}

	@Test
	public void test_withdraw_command_72() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 9999999a 2000"));
	}

	@Test
	public void test_withdraw_command_73() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw 9999999@ 2000"));
	}

	@Test
	public void test_withdraw_command_74() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertTrue(validator.isValidCommand("wiTHdraw 99999999 2000"));
	}

	@Test
	public void test_withdraw_command_75() {
		bank.addAccount(cdAccount);
		bank.passTime(12);
		assertFalse(validator.isValidCommand("withdraw    99999999    2000"));
	}

// Transfer Command Validation Tests

	// tests account transfer money to itself
	@Test
	public void test_transfer_command_1() {
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 12345678 10"));
	}

	@Test
	public void test_transfer_command_2() {
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 00000001 10"));
	}

	// reference code
	@Test
	public void test_transfer_command_99() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 10"));
	}

	// checking to checking

	@Test
	public void test_transfer_command_cc1() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfe 12345678 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc2() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc3() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("Transfer 12345678 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc4() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transFEr 12345678 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc5() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 1234567 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc6() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc7() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 123456789 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc8() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 12345678.0 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc9() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer -12345678 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc10() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 1234567a 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc11() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 1234567@ 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc12() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 8765432 10"));
	}

	@Test
	public void test_transfer_command_cc13() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 10"));
	}

	@Test
	public void test_transfer_command_cc14() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 876543210 10"));
	}

	@Test
	public void test_transfer_command_cc15() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 87654321.0 10"));
	}

	@Test
	public void test_transfer_command_cc16() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 -87654321 10"));
	}

	@Test
	public void test_transfer_command_cc17() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 8765432a 10"));
	}

	@Test
	public void test_transfer_command_cc18() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 8765432@ 10"));
	}

	@Test
	public void test_transfer_command_cc19() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 87654321 -1"));
	}

	@Test
	public void test_transfer_command_cc20() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 0"));
	}

	@Test
	public void test_transfer_command_cc21() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 1"));
	}

	@Test
	public void test_transfer_command_cc22() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 1.3"));
	}

	@Test
	public void test_transfer_command_cc23() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 1.33"));
	}

	@Test
	public void test_transfer_command_cc24() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 87654321 1.333"));
	}

	@Test
	public void test_transfer_command_cc25() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 100"));
	}

	@Test
	public void test_transfer_command_cc26() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 1000"));
	}

	@Test
	public void test_transfer_command_cc27() {
		bank.addAccount(checkingAccount);
		bank.addAccount(checkingAccount2);
		bank.deposit("12345678", 100);
		bank.deposit("87654321", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 87654321 10000"));
	}

	// checking to savings

	@Test
	public void test_transfer_command_cs1() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfe 12345678 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs2() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs3() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("Transfer 12345678 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs4() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transFEr 12345678 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs5() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 1234567 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs6() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs7() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345679 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs8() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345678.0 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs9() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer -12345678 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs10() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 1234567a 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs11() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 1234567@ 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs12() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 0000000 10"));
	}

	@Test
	public void test_transfer_command_cs13() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 10"));
	}

	@Test
	public void test_transfer_command_cs14() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 000000010 10"));
	}

	@Test
	public void test_transfer_command_cs15() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 00000001.0 10"));
	}

	@Test
	public void test_transfer_command_cs16() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 -00000001 10"));
	}

	@Test
	public void test_transfer_command_cs17() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 0000000a 10"));
	}

	@Test
	public void test_transfer_command_cs18() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 0000000@ 10"));
	}

	@Test
	public void test_transfer_command_cs19() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 00000001 -1"));
	}

	@Test
	public void test_transfer_command_cs20() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 0"));
	}

	@Test
	public void test_transfer_command_cs21() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 1"));
	}

	@Test
	public void test_transfer_command_cs22() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 1.3"));
	}

	@Test
	public void test_transfer_command_cs23() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 1.33"));
	}

	@Test
	public void test_transfer_command_cs24() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 00000001 1.333"));
	}

	@Test
	public void test_transfer_command_cs25() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 100"));
	}

	@Test
	public void test_transfer_command_cs26() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 1000"));
	}

	@Test
	public void test_transfer_command_cs27() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 12345678 00000001 1000"));
	}

	// savings to checking

	@Test
	public void test_transfer_command_sc1() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfe 00000001 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc2() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc3() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("Transfer 00000001 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc4() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transFEr 00000001 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc5() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 0000000 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc6() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc7() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 000000010 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc8() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001.0 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc9() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer -00000001 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc10() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 0000000a 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc11() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 0000000@ 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc12() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 1234567 10"));
	}

	@Test
	public void test_transfer_command_sc13() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 10"));
	}

	@Test
	public void test_transfer_command_sc14() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 123456789 10"));
	}

	@Test
	public void test_transfer_command_sc15() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 12345678.0 10"));
	}

	@Test
	public void test_transfer_command_sc16() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 -12345678 10"));
	}

	@Test
	public void test_transfer_command_sc17() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 1234567a 10"));
	}

	@Test
	public void test_transfer_command_sc18() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 1234567@ 10"));
	}

	@Test
	public void test_transfer_command_sc19() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 12345678 -1"));
	}

	@Test
	public void test_transfer_command_sc20() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 0"));
	}

	@Test
	public void test_transfer_command_sc21() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 1"));
	}

	@Test
	public void test_transfer_command_sc22() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 1.3"));
	}

	@Test
	public void test_transfer_command_sc23() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 1.33"));
	}

	@Test
	public void test_transfer_command_sc24() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 12345678 1.333"));
	}

	@Test
	public void test_transfer_command_sc25() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 100"));
	}

	@Test
	public void test_transfer_command_sc26() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 1000"));
	}

	@Test
	public void test_transfer_command_sc27() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("12345678", 100);
		bank.deposit("00000001", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 12345678 1000"));
	}

	// savings to savings

	@Test
	public void test_transfer_command_ss1() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfe 00000001 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss2() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss3() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("Transfer 00000001 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss4() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transFEr 00000001 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss5() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 0000000 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss6() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss7() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 000000010 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss8() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 00000001.0 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss9() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer -00000001 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss10() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 0000000a 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss11() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 0000000@ 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss12() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 1000000 10"));
	}

	@Test
	public void test_transfer_command_ss13() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 10"));
	}

	@Test
	public void test_transfer_command_ss14() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 100000000 10"));
	}

	@Test
	public void test_transfer_command_ss15() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 10000000.0 10"));
	}

	@Test
	public void test_transfer_command_ss16() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 -10000000 10"));
	}

	@Test
	public void test_transfer_command_ss17() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 1000000a 10"));
	}

	@Test
	public void test_transfer_command_ss18() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 1000000@ 10"));
	}

	@Test
	public void test_transfer_command_ss19() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 10000000 -1"));
	}

	@Test
	public void test_transfer_command_ss20() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 0"));
	}

	@Test
	public void test_transfer_command_ss21() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 1"));
	}

	@Test
	public void test_transfer_command_ss22() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 1.3"));
	}

	@Test
	public void test_transfer_command_ss23() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 1.33"));
	}

	@Test
	public void test_transfer_command_ss24() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 10000000 1.333"));
	}

	@Test
	public void test_transfer_command_ss25() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 100"));
	}

	@Test
	public void test_transfer_command_ss26() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 1000"));
	}

	@Test
	public void test_transfer_command_ss27() {
		bank.addAccount(savingsAccount);
		bank.addAccount(savingsAccount2);
		bank.deposit("00000001", 100);
		bank.deposit("10000000", 100);
		assertTrue(validator.isValidCommand("transfer 00000001 10000000 10000"));
	}

	// cd account tests

	@Test
	public void test_transfer_command_100() {
		bank.addAccount(cdAccount);
		bank.addAccount(checkingAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("transfer 99999999 12345678 10"));
	}

	@Test
	public void test_transfer_command_101() {
		bank.addAccount(checkingAccount);
		bank.addAccount(cdAccount);
		bank.deposit("12345678", 100);
		assertFalse(validator.isValidCommand("transfer 12345678 99999999 10"));
	}

	@Test
	public void test_transfer_command_102() {
		bank.addAccount(cdAccount);
		bank.addAccount(savingsAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 99999999 00000001 10"));
	}

	@Test
	public void test_transfer_command_103() {
		bank.addAccount(savingsAccount);
		bank.addAccount(cdAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 99999999 10"));
	}

	@Test
	public void test_transfer_command_104() {
		bank.addAccount(savingsAccount);
		bank.addAccount(cdAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer 00000001 99999999 10 1"));
	}

	@Test
	public void test_transfer_command_105() {
		bank.addAccount(savingsAccount);
		bank.addAccount(cdAccount);
		bank.deposit("00000001", 100);
		assertFalse(validator.isValidCommand("transfer"));
	}

// Pass Time Validator Tests

	@Test
	public void test_pass_time_command_1() {
		assertFalse(validator.isValidCommand("pass -1"));
	}

	@Test
	public void test_pass_time_command_2() {
		assertFalse(validator.isValidCommand("pass 0"));
	}

	@Test
	public void test_pass_time_command_3() {
		assertTrue(validator.isValidCommand("pass 1"));
	}

	@Test
	public void test_pass_time_command_4() {
		assertFalse(validator.isValidCommand("pass 1.3"));
	}

	@Test
	public void test_pass_time_command_5() {
		assertFalse(validator.isValidCommand("pass 1.3333"));
	}

	@Test
	public void test_pass_time_command_6() {
		assertTrue(validator.isValidCommand("pass 59"));
	}

	@Test
	public void test_pass_time_command_7() {
		assertTrue(validator.isValidCommand("pass 60"));
	}

	@Test
	public void test_pass_time_command_8() {
		assertFalse(validator.isValidCommand("pass 61"));
	}

	@Test
	public void test_pass_time_command_9() {
		assertTrue(validator.isValidCommand("pASs 1"));
	}

	@Test
	public void test_pass_time_command_10() {
		assertFalse(validator.isValidCommand("pass    1"));
	}

	@Test
	public void test_pass_time_command_11() {
		assertFalse(validator.isValidCommand("pass"));
	}

	@Test
	public void test_pass_time_command_12() {
		assertFalse(validator.isValidCommand("pass 23 wq"));
	}

}