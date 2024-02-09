package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {

	private CommandStorage commandStorage;

	@BeforeEach
	public void setUp() {
		commandStorage = new CommandStorage();
	}

	@Test
	public void testAddInvalidCommandToTestSize() {
		String invalidCommand = "invalid command";
		commandStorage.addInvalidCommand(invalidCommand);

		assertEquals(1, commandStorage.getAllInvalidCommands().size());
	}

	@Test
	public void testAddInvalidCommandToTestElementInArray() {
		String invalidCommand = "invalid command";
		commandStorage.addInvalidCommand(invalidCommand);

		assertTrue(commandStorage.getAllInvalidCommands().contains(invalidCommand));
	}

	@Test
	public void testGetAllInvalidCommandsToTestSize() {
		String invalidCommand1 = "invalid command 1";
		String invalidCommand2 = "invalid command 2";
		commandStorage.addInvalidCommand(invalidCommand1);
		commandStorage.addInvalidCommand(invalidCommand2);

		assertEquals(2, commandStorage.getAllInvalidCommands().size());
	}

	@Test
	public void testGetAllInvalidCommandsToTestFirstCommand() {
		String invalidCommand1 = "invalid command 1";
		String invalidCommand2 = "invalid command 2";
		commandStorage.addInvalidCommand(invalidCommand1);
		commandStorage.addInvalidCommand(invalidCommand2);

		assertTrue(commandStorage.getAllInvalidCommands().contains(invalidCommand1));
	}

	@Test
	public void testGetAllInvalidCommandsToTestSecondCommand() {
		String invalidCommand1 = "invalid command 1";
		String invalidCommand2 = "invalid command 2";
		commandStorage.addInvalidCommand(invalidCommand1);
		commandStorage.addInvalidCommand(invalidCommand2);

		assertTrue(commandStorage.getAllInvalidCommands().contains(invalidCommand2));
	}
}