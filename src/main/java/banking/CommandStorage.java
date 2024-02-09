package banking;

import java.util.ArrayList;

public class CommandStorage {
	private ArrayList<String> invalidCommandList;
	private ArrayList<String> validCommandList;
	private ArrayList<String> idList;
	private ArrayList<String> finalList;

	public CommandStorage() {
		invalidCommandList = new ArrayList<>();
		validCommandList = new ArrayList<>();
		idList = new ArrayList<>();
		finalList = new ArrayList<>();
	}

	public void addInvalidCommand(String command) {
		invalidCommandList.add(command);
	}

	public ArrayList<String> getAllInvalidCommands() {
		return invalidCommandList;
	}

	public void addValidCommand(String command) {
		validCommandList.add(command);
	}

	public ArrayList<String> getValidCommands() {
		return validCommandList;
	}

	public void storeID(String id) {
		idList.add(id);
	}

	public ArrayList<String> getIDs() {
		return idList;
	}

	public void storeFinalCommand(String command) {
		finalList.add(command);
	}

	public ArrayList<String> getFinalList() {
		return finalList;
	}

}