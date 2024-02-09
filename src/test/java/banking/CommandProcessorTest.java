package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
	private Bank bank;
	private CommandProcessor commandProcessor;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	public void testCreateAccountId() {
		String createCommand = "create checking 12345678 4.0";
		commandProcessor.processCommand(createCommand);

		assertTrue(bank.getAccounts().containsKey("12345678"));
		assertEquals(4.0, bank.getAccount("12345678").getApr());
	}

	@Test
	public void testProcessDepositCommandInAnAccountJustCreated() {
		String createCommand = "create checking 12345678 4.0";
		commandProcessor.processCommand(createCommand);

		String depositCommand = "deposit 12345678 100";

		commandProcessor.processCommand(depositCommand);

		assertEquals(100, bank.getAccount("12345678").getBalance());
	}

	@Test
	public void testProcessDepositCommandInAnAccountWithMoney() {
		String createCommand = "create checking 12345678 4.0";
		commandProcessor.processCommand(createCommand);

		String depositCommand1 = "deposit 12345678 50";
		commandProcessor.processCommand(depositCommand1);

		String depositCommand2 = "deposit 12345678 100";
		commandProcessor.processCommand(depositCommand2);

		assertEquals(150, bank.getAccount("12345678").getBalance());
	}

	@Test
	public void testProcessWithdrawCommandInAnAccountWithMoney() {
		String createCommand = "create checking 12345678 4.0";
		commandProcessor.processCommand(createCommand);

		String depositCommand1 = "deposit 12345678 100";
		commandProcessor.processCommand(depositCommand1);

		String withdrawCommand2 = "withdraw 12345678 50";
		commandProcessor.processCommand(withdrawCommand2);

		assertEquals(50, bank.getAccount("12345678").getBalance());
	}

	@Test
	public void testProcessTransferFromCheckingToSavings1() {
		String createCommand = "create checking 12345678 4.0";
		commandProcessor.processCommand(createCommand);

		String createCommand2 = "create savings 00000001 4.0";
		commandProcessor.processCommand(createCommand2);

		String depositCommand1 = "deposit 12345678 100";
		commandProcessor.processCommand(depositCommand1);

		String depositCommand2 = "deposit 00000001 100";
		commandProcessor.processCommand(depositCommand2);

		String transferCommand = "transfer 12345678 00000001 20";
		commandProcessor.processCommand(transferCommand);

		assertEquals(80, bank.getAccount("12345678").getBalance());
	}

	@Test
	public void testProcessTransferFromCheckingToSavings2() {
		String createCommand = "create checking 12345678 4.0";
		commandProcessor.processCommand(createCommand);

		String createCommand2 = "create savings 00000001 4.0";
		commandProcessor.processCommand(createCommand2);

		String depositCommand1 = "deposit 12345678 100";
		commandProcessor.processCommand(depositCommand1);

		String depositCommand2 = "deposit 00000001 100";
		commandProcessor.processCommand(depositCommand2);

		String transferCommand = "transfer 12345678 00000001 20";
		commandProcessor.processCommand(transferCommand);

		assertEquals(120, bank.getAccount("00000001").getBalance());
	}

	@Test
	public void testProcessPassTimeCheckAPR() {
		String createCommand = "create savings 00000001 4.0";
		commandProcessor.processCommand(createCommand);

		String depositCommand = "deposit 00000001 100";
		commandProcessor.processCommand(depositCommand);

		String passTimeCommand = "pass 12";
		commandProcessor.processCommand(passTimeCommand);

		assertEquals(104.07415429197894, bank.getAccount("00000001").getBalance());
	}

	@Test
	public void testProcessPassTimeCheckAPR2() {
		String createCommand = "create savings 00000001 3.0";
		commandProcessor.processCommand(createCommand);

		String depositCommand = "deposit 00000001 1000";
		commandProcessor.processCommand(depositCommand);

		String passTimeCommand = "pass 1";
		commandProcessor.processCommand(passTimeCommand);

		assertEquals(1002.5, bank.getAccount("00000001").getBalance());
	}

	@Test
	public void testProcessPassTimeCheckAPR3() {
		String createCommand = "create savings 12345678 0.6";
		commandProcessor.processCommand(createCommand);

		String depositCommand = "deposit 12345678 1000";
		commandProcessor.processCommand(depositCommand);

		String passTimeCommand = "pass 1";
		commandProcessor.processCommand(passTimeCommand);

		assertEquals(1000.50, bank.getAccount("12345678").getBalance());
	}

	@Test
	public void testProcessPassTimeCheckAPR4() {
		String createCommand = "create checking 12345678 0.6";
		commandProcessor.processCommand(createCommand);

		String passTimeCommand = "pass 1";
		commandProcessor.processCommand(passTimeCommand);

		assertEquals(null, bank.getAccount("12345678"));
	}

	@Test
	public void test() {
		String createCommand = "Create savings 12345678 0.6";
		commandProcessor.processCommand(createCommand);

		String depositCommand = "Deposit 12345678 700";
		commandProcessor.processCommand(depositCommand);

		String passTimeCommand = "Deposit 12345678 5000";
		commandProcessor.processCommand(passTimeCommand);

		assertEquals(5700, bank.getAccount("12345678").getBalance());
	}

	@Test
	public void sample_1() {
		String createCommand = "create savings 12345678 4.0";
		commandProcessor.processCommand(createCommand);

		String depositCommand = "deposit 12345678 1000";
		commandProcessor.processCommand(depositCommand);

		String passTimeCommand = "pass 1";
		commandProcessor.processCommand(passTimeCommand);

		String withdrawCommand = "withdraw 12345678 2.5";
		commandProcessor.processCommand(withdrawCommand);

		assertEquals(1000.8333333333334, bank.getAccount("12345678").getBalance());
	}
}
