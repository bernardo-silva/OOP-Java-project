package doubleBonusT7;

import videoPoker.DebugPlayer;
import videoPoker.Player;
import videoPoker.SimulationPlayer;
import videoPoker.VideoPoker;

/**
 * Implements the main function that creates the Double Bonus 10/7 variant of
 * {@link videoPoker}. 
 * <p>
 * The program should be provided with four arguments:
 * <ul>
 * <li> "-s": to run the game in simulation mode ({@link SimulationPlayer})
 *   <ul>
 *   <li> credit: player initial credit
 *   <li> bet: default amount to be betted, between 1 and 5
 *   <li> nbdeals: number of rounds to play
 *   </ul>
 * <li> "-d": to run the game in debug mode ({@link DebugPlayer})
 *   <ul>
 *   <li> credit: player initial credit
 *   <li> cmd-file: path to the file containing the commands the player should
 *   execute
 *   <li> card-file: path to the file containing the available playing cards
 *   </ul>
 * </ul>
 *
 * @author Bernardo Silva 
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class DoubleBonusT7 {
	private static void printCommandLineHelp() {
		System.out.println("Incorrect number of parameters. Expected:");
		System.out.println("Debug mode: -d <credit> <cmd-file> <card-file>");
		System.out.println("Simulation mode: -s <credit> <bet> <nbdeals>");
	}

        
        /**
         * Implements the main function that creates the Double Bonus 10/7 variant of
         * {@link videoPoker}. 
         * <p>
         * The program should be provided with four arguments:
         * <ul>
         * <li> "-s": to run the game in simulation mode ({@link SimulationPlayer})
         *   <ul>
         *   <li> credit: player initial credit
         *   <li> bet: default amount to be betted, between 1 and 5
         *   <li> nbdeals: number of rounds to play
         *   </ul>
         * <li> "-d": to run the game in debug mode ({@link DebugPlayer})
         *   <ul>
         *   <li> credit: player initial credit
         *   <li> cmd-file: path to the file containing the commands the player should
         *   execute
         *   <li> card-file: path to the file containing the available playing cards
         *   </ul>
         * </ul>
         * @param args command line arguments
         */
	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println(args.length);
			printCommandLineHelp();
			System.exit(0);
		}

		Player player;
		VideoPoker game = null;
		DoubleBonusT7Strategy strategy = new DoubleBonusT7Strategy();
		String pokerHandsFile = "resources/DoubleBonusT7Hands.txt";

		switch (args[0]) {
		case "-d":
			try {
				int credit = Integer.parseInt(args[1]);
				player = new DebugPlayer(credit, args[2]);
				game = new VideoPoker(pokerHandsFile, args[3], player, strategy, true);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Exiting.");
				System.exit(1);
			}
			break;
		case "-s":
			try {
				int credit = Integer.parseInt(args[1]);
				int bet = Integer.parseInt(args[2]);
				int nbdeals = Integer.parseInt(args[3]);

				player = new SimulationPlayer(credit, bet, nbdeals);
				game = new VideoPoker(pokerHandsFile, player, strategy);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Exiting.");
				System.exit(1);
			}
			break;
		default:
			printCommandLineHelp();
			System.exit(1);
		}

		game.playGame();
	}

}
