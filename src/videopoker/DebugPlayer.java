package videopoker;

import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList; 


public class DebugPlayer extends Player{
	
	 ArrayList<Action> action_list;
	
	 
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

            while (scanFile.hasNext()){
    
            	//check character -> check if it needs to check numbers -> build action list
                //action_list.add(new Action(scanFile.next()));
            }
            
            System.out.println(action_list);

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
}


/* TO TEST THIS:
PS C:\Users\vicen\Desktop\POO\oop-project\src> javac .\videopoker\DebugPlayer.java
PS C:\Users\vicen\Desktop\POO\oop-project\src> jar cmf manif.txt Debug.jar .\videopoker
PS C:\Users\vicen\Desktop\POO\oop-project\src> java -jar Debug.jar
 * */
