package videopoker;

import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList; 


public class DebugPlayer extends Player{
	
	 ArrayList<Action> action_list = new ArrayList<Action>();
	
	 
	 public DebugPlayer(int _money, String filename) {
		 super(_money);
		 get_action_list(filename);
	 }
	 
	 
	 public void get_action_list(String filename) {
		 //reads the file and builds the list of actions
		 
        try{        
            File actionFile = new File(filename);
            Scanner scanFile = new Scanner(actionFile);

            scanFile.useDelimiter(" ");

            String temp;
            Action new_action;
            
            while (scanFile.hasNext()){
            	
            	temp = scanFile.next();       
            	
            	
        		new_action = new Action(temp.charAt(0));
        		action_list.add(new_action); 
        		
        		switch(temp.charAt(0)) {
        		
	        		case 'h':
	        			// adds hold positions
	            		while(scanFile.hasNextInt()) {
	            			temp = scanFile.next();						// prob can remove this variable temp
	            			int position = Integer.parseInt(temp);     // prob can remove this variable position            			
	            			new_action.add_positions(position); 	// Q: should we check exceptions of parseInt, or it is good  with just the while condition ?
	            		}
	            		continue;
	            		
	        		case 'b':
	        			// adds the amount of money to bet, if specified
	            		if(scanFile.hasNextInt()) {
	                		temp = scanFile.next();
	                		int money = Integer.parseInt(temp);	                		
	                		new_action.add_money(money);            			
	            		}
	            		continue;
	        			
        		}

            	
//            	System.out.println(temp);
            	
              		
            }
            
            System.out.println("action_list: "+ action_list);

            scanFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }		 
		 
	 }
	 
	 
	 
    public static void main(String[] args)
    { 
        DebugPlayer teste = new DebugPlayer(20, "C:\\Users\\vicen\\Desktop\\POO\\oop-project\\cmd-file.txt");
    }


	@Override
	public Action askAction() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int askBet() {
		// TODO Auto-generated method stub
		return 0;
	}
}


/* TO TEST THIS:
PS C:\Users\vicen\Desktop\POO\oop-project\src> javac .\videopoker\DebugPlayer.java
PS C:\Users\vicen\Desktop\POO\oop-project\src> jar cmf manif.txt Debug.jar .\videopoker
PS C:\Users\vicen\Desktop\POO\oop-project\src> java -jar Debug.jar
 * */
