package videopoker;

public class Main {
	private static void printCommandLineHelp() {
		System.out.println("Incorrect number of parameters. Expected:");
		System.out.println("Debug mode: -d <credit> <cmd-file> <card-file>");
		System.out.println("Simulation mode: -s <credit> <bet> <nbdeals>");
	}

	public static void main(String[] args) {
		if(args.length != 4) {
			printCommandLineHelp();
			System.exit(0);
		}

		Player player;
		VideoPoker game = null;
		String stratfile = "strat.txt";

		switch(args[0]) {
		case "-d":
			try {
				int credit = Integer.parseInt(args[1]);
				player = new DebugPlayer(credit, args[2]);
				game = new VideoPoker("hands.txt", stratfile, args[3], false, player);
			}
			catch(Exception e){
				printCommandLineHelp();
				System.exit(0);
			}
			break;
//		case "-s":
//			try {
//				int credit = Integer.parseInt(args[1]);
//				int bet = Integer.parseInt(args[2]);
//				int nbdeals = Integer.parseInt(args[3]);
//
//				player = new SimulationPlayer(credit, bet, nbdeals);
//				game = new VideoPoker(args[3], stratfile, player);
//			}
//			catch(Exception e){
//				printCommandLineHelp();
//				System.exit(0);
//			}
//			break;
		default:
			printCommandLineHelp();
			System.exit(0);
		}
		
		game.playGame();
	}

}
