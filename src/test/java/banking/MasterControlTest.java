package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterControlTest {

	MasterControl masterControl;
	List<String> input;
	private CommandValidator commandValidator;

	@BeforeEach
	void setUp() {
		input = new ArrayList<>();
		Bank bank = new Bank();
		commandValidator = new CommandValidator(bank);
		masterControl = new MasterControl(new CommandValidator(bank), new CommandProcessor(bank), new CommandStorage());
	}

	private void assertSingleCommand(String command, List<String> actual) {
		assertEquals(1, actual.size());
		assertEquals(command, actual.get(0));
	}

	@Test
	void typo_in_create_command_is_invalid() {
		List<String> input = new ArrayList<>();
		input.add("creat checking 12345678 1.0");

		List<String> actual = masterControl.start(input);
		assertEquals(1, actual.size());
		assertEquals("creat checking 12345678 1.0", actual.get(0));

	}

	@Test
	void typo_in_deposit_command_is_invalid() {
		List<String> input = new ArrayList<>();
		input.add("depositt 12345678 100");

		List<String> actual = masterControl.start(input);
		assertEquals(1, actual.size());
		assertEquals("depositt 12345678 100", actual.get(0));
	}

	@Test
	void two_typo_commands_both_invalid() {
		input.add("creat checking 12345678 1.0");
		input.add("depositt 12345678 100");

		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals("creat checking 12345678 1.0", actual.get(0));
		assertEquals("depositt 12345678 100", actual.get(1));
	}

//	@Test
//	void invalid_to_create_accounts_with_same_ID() {
//		input.add("create checking 12345678 1.0");
//		input.add("create checking 12345678 1.0");
//
//		List<String> actual = masterControl.start(input);
//
//		assertSingleCommand("create checking 12345678 1.0", actual);
//
//	}

	@Test
	void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
		input.add("Create savings 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Deposit 12345678 5000");
		input.add("creAte cHecKing 98765432 0.01");
		input.add("Deposit 98765432 300");
		input.add("Transfer 98765432 12345678 300");
		input.add("Pass 1");
		input.add("Create cd 23456789 1.2 2000");
		List<String> actual = masterControl.start(input);

		assertEquals(5, actual.size());
		assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Transfer 98765432 12345678 300", actual.get(2));
		assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
		assertEquals("Deposit 12345678 5000", actual.get(4));
	}

	@Test
	void sample_1() {
		input.add("Create saVIngs 12345678 4.0");
		input.add("Deposit 12345678 700");
		input.add("Deposit 12345678 300");
		input.add("Pass 1");
		input.add("Deposit 333 300");
		input.add("Withdraw 12345678 2.50");
		input.add("Create cd 23456789 1.2 2000");
		List<String> actual = masterControl.start(input);

		assertEquals(6, actual.size());
		assertEquals("Savings 12345678 1000.83 4.00", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Deposit 12345678 300", actual.get(2)); // Deposit
		assertEquals("Withdraw 12345678 2.50", actual.get(3)); // 2.50
		assertEquals("Cd 23456789 2000.00 1.20", actual.get(4));
		assertEquals("Deposit 333 300", actual.get(5));
	}

	@Test
	void sample_2() {
		input.add("Create cd 23456789 1.2 2000");
		input.add("Pass 13");
		input.add("Withdraw 23456789 2500");
		input.add("Create cd 23456000 1.33 2000");
		List<String> actual = masterControl.start(input);

		assertEquals(3, actual.size());
		assertEquals("Cd 23456789 0.00 1.20", actual.get(0));
		assertEquals("Withdraw 23456789 2500", actual.get(1));
		assertEquals("Cd 23456000 2000.00 1.33", actual.get(2));
	}

	// check whitespace
	@Test
	void sample_3() {
		input.add("Create cd 23456789 1.2 2000");
		input.add("Pass   13");
		input.add("Withdraw   23456789 2500");
		input.add("Create cd 23456000 1.33 2000");
		List<String> actual = masterControl.start(input);

		assertEquals(4, actual.size());
		assertEquals("Cd 23456789 2000.00 1.20", actual.get(0));
		assertEquals("Cd 23456000 2000.00 1.33", actual.get(1));
		assertEquals("Pass   13", actual.get(2));
		assertEquals("Withdraw   23456789 2500", actual.get(3));
	}

	@Test
	void sample_4() {
		input.add("Create savings 12345678 4.0");
		input.add("Deposit 12345678 700");
		input.add("Deposit 12345678 300");
		input.add("Withdraw 12345678 2.50");
		input.add("Withdraw 12345678 3.50");
		input.add("Pass 1");
		input.add("Withdraw 12345678 3.50");
		input.add("Create cd 23456789 1.2 2000");
		List<String> actual = masterControl.start(input);

		assertEquals(7, actual.size());
		assertEquals("Savings 12345678 997.32 4.00", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Deposit 12345678 300", actual.get(2));
		assertEquals("Withdraw 12345678 2.50", actual.get(3));
		assertEquals("Withdraw 12345678 3.50", actual.get(4));
		assertEquals("Cd 23456789 2000.00 1.20", actual.get(5));
		assertEquals("Withdraw 12345678 3.50", actual.get(6));
	}

	@Test
	void sample_5() {
		input.add("Create cd 23456789 4.0 1000");
		input.add("Pass 12");
		input.add("Withdraw 23456789 1300");
		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals("Cd 23456789 0.00 4.00", actual.get(0));
		assertEquals("Withdraw 23456789 1300", actual.get(1));
	}
}
