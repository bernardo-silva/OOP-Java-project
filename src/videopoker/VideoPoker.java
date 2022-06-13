/**
 * 
 */
package videopoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author bs
 *
 */
public class VideoPoker {
	Player player;
	DeckOfCards deck;
	
	private List<String> hands;
	private HashMap<String, String[]> handsParameters;
	private HashMap<String, Integer> payouts;
	private List<String> optimalStrat;
	
	/**
	 * @param handFile 
	 * 
	 */
	public VideoPoker(String stratFile, String handFile) {
		player = new Player();
		deck = new DeckOfCards();
		
		optimalStrat = stratFromFile(stratFile);
		handsParameters = new HashMap<String, String[]>();
		payouts = new HashMap<String, Integer>();
		handsFromFile(handFile);
		System.out.println(hands);
	}

	private List<String> stratFromFile(String stratFile) {
		// TODO Auto-generated method stub
		return null;
	}
//	private void handsFromJson(String handFile) {
//		try {
//			File f = new File(handFile);
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder;
//			dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(handFile);
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//	        NodeList nList = doc.getElementsByTagName("hand");
//	        System.out.println("----------------------------");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	private void handsFromFile(String handFile) {
		File f = new File(handFile);
		Scanner scan;
		try {
			scan = new Scanner(f);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				System.out.println(line);
//				String[] parameters = line.split("#", 0)[0].split(" ",0);
//				String name = line.split("#", 0)[1];
//
//				hands.add(name);
//				handsParameters.put(name, parameters);
//				//TODO: Implement payouts
//				payouts.put(name, 1);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Bad Hand File");
			e.printStackTrace();
		}
	}
	
	private void scanHand(Scanner scan) {
		String line = scan.nextLine();
		String[] params = line.split(":",2);
		
		String name = params[0];
		int payout =  Integer.parseInt(params[1]);
		
		String rule;
		List<handRule> rules = new ArrayList<>();
		while(scan.hasNextLine()) {
			line = scan.nextLine();
			if(line.isBlank()) return;
			params = line.split("=",2);
			rule = params[0];

			switch (rule) {
			case "numberRank":
				String[] ranks = rule.split(";",2)[0].split(",",0);
				int value = Integer.parseInt(rule.split(";",2)[0].split(",",0)[1]);
				rules.add(new numberRank(ranks, value));
			}


		}
		
		
	}

	public void payout() {
		
	}

	public int[] advice(){
		return null;
	}

	private String checkHand(){
		boolean satisfies = true;
		for(String hand : hands) {

			for(String par : handsParameters.get(hand)) {
				String rule = par.split(":", 2)[0];
				String value = par.split(":", 2)[1];
				
				satisfies = checkRule(rule, value);

				if(!satisfies) break;
			}
		}
		return null;
	}

	private boolean checkRule(String rule, String value) {
		switch (rule) {
			case "nb":
				String[] nbRules = value.split(":", 0);
				break;
			case "st":
				if(player.hand.straightDistance != 0)
					return false;
				break;
			case "fl":
				if(player.hand.flushDistance != 0)
					return false;
				break;
			case "hi":
				break;
		}
		return false;
	}

	public static void main(String[] args) {

		VideoPoker vp = new VideoPoker("", "hands2.txt");
	}
}
