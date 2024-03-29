package videoPoker;

import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * Implements a {@link Player} that returns actions read from a file.
 *
 * @author Bernardo Silva 
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class DebugPlayer extends Player {

	/**
	 * Linked list of actions to be performed on DebugPlayer Mode
	 */
	LinkedList<Action> actions = new LinkedList<Action>();

	/**
         * Constructs a player that reads actions from a file.
	 * @param _money amount of money the player starts with
	 * @param filename name of the file with the actions to perform
	 */
	public DebugPlayer(int _money, String filename) {
		super(_money);
		readActionsFromFile(filename);
	}

	/**
	 * Reads the file and builds the list of actions to be performed
	 * @param filename name of the file with the actions to perform
	 */
	private void readActionsFromFile(String filename) {
		try {
			File actionFile = new File(filename);
			Scanner scanFile = new Scanner(actionFile);

			scanFile.useDelimiter("\\s+");

			String read;
			Action new_action;

			while (scanFile.hasNext()) {
				read = scanFile.next();

				new_action = new Action(read.charAt(0));
				actions.add(new_action);

				switch (read.charAt(0)) {
				case 'h':
					// adds hold positions
					while (scanFile.hasNextInt()) {
						read = scanFile.next();
						new_action.addPosition(Integer.parseInt(read));
					}
					break;

				case 'b':
					// adds the amount of money to bet, if specified
					if (scanFile.hasNextInt()) {
						read = scanFile.next();
						new_action.setBet(Integer.parseInt(read));
					}
					break;
				}
			}
//			System.out.println("actions: " + actions);
			scanFile.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Exiting.");
			System.exit(1);
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("Error on file " + filename);
			System.out.println("Exiting.");
			System.exit(1);
		}
	}

	/**
	 * Asks for an action of the list of actions.
	 * @return the first action of the list of actions, or null if this list in empty
	 */
	@Override
	public Action askAction() {
		if (actions.isEmpty())
			return null;
		return actions.remove();
	}
}
