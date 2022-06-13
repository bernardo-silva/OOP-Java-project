package videopoker;

import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;


public class DebugPlayer extends Player{

	 LinkedList<Action> actions = new LinkedList<Action>();
	 
	 public DebugPlayer(int _money, String filename) {
		 super(_money);
		 readActionsFromFile(filename);
	 }
	 
	 
	 public void readActionsFromFile(String filename) {
		 //reads the file and builds the list of actions
        try{        
            File actionFile = new File(filename);
            Scanner scanFile = new Scanner(actionFile);

            scanFile.useDelimiter(" ");

            String read;
            Action new_action;
            
            while (scanFile.hasNext()){
            	read = scanFile.next();       
            	
        		new_action = new Action(read.charAt(0));
        		actions.add(new_action); 
        		
        		switch(read.charAt(0)) {
				case 'h':
					// adds hold positions
					int position;
					while(scanFile.hasNextInt()) {
						read = scanFile.next();						// prob can remove this variable read
						position = Integer.parseInt(read);     // prob can remove this variable position            			
						new_action.addPosition(position); 	// Q: should we check exceptions of parseInt, or it is good  with just the while condition ?
					}
					break;
					
				case 'b':
					// adds the amount of money to bet, if specified
					if(scanFile.hasNextInt()) {
						read = scanFile.next();
						int betAmount = Integer.parseInt(read);	                		
						new_action.setBet(betAmount);            			
					}
					break;
        		}
//            	System.out.println(temp);
            }
            System.out.println("actions: "+ actions);
            scanFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }		 
		 
	 }
	 
	@Override
	public Action askAction() {
		if (actions.isEmpty()) return null;
		return actions.remove();
	}

	@Override
	public int askBet() {
		int setBet = actions.remove().getBet();
		if(setBet == 0) {
			if(lastBet == 0) {
				setBet =  5;
				lastBet = 5;
			}
			else setBet = lastBet;
		}
		return lastBet;
	}
}


/* TO TEST THIS:
PS C:\Users\vicen\Desktop\POO\oop-project\src> javac .\videopoker\DebugPlayer.java
PS C:\Users\vicen\Desktop\POO\oop-project\src> jar cmf manif.txt Debug.jar .\videopoker
PS C:\Users\vicen\Desktop\POO\oop-project\src> java -jar Debug.jar
 * */
