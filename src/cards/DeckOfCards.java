package cards;
import java.io.*;
import java.awt.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards{
	
	private final ArrayList<Card> fullDeck = new ArrayList<Card>(52);
	private ArrayList<Card> deck = new ArrayList<Card>(52);

	public DeckOfCards(String filename)
    {
        try{        
            File fullDeckFile = new File("../files/fulldeck.txt");
            Scanner scanFullDeck = new Scanner(fullDeckFile);

            File deckFile = new File(filename);
            Scanner scanDeck = new Scanner(deckFile);

            scanFullDeck.useDelimiter(" ");
            scanDeck.useDelimiter(" ");
            
            while(scanFullDeck.hasNext()){
                fullDeck.add(new Card(scanFullDeck.next()));
            }

            while (scanDeck.hasNext()){
                deck.add(new Card(scanDeck.next()));
            }
            scanDeck.close();
            scanFullDeck.close();        
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }    
    
    public DeckOfCards(Boolean jokers, Boolean use89T) { //vou deixar isto para o fim
        if (jokers == null) {
            jokers = false;
        }
        if (use89T == null) {
            use89T = false;
        }
    
    } 
	
    public ArrayList<Card> getDeck(){
    	return deck;
    }
    
    public ArrayList<Card> getFullDeck(){
    	return fullDeck;
    }
    
    public void shuffle(){
    	Collections.shuffle(deck);
	}
    public ArrayList<Card> deal(int nCards){
    	ArrayList<Card> giveCards = new ArrayList<Card>(nCards);
    	for(int i = 0; i < nCards;i++){
    		giveCards.add(deck.get(0));
    		deck.remove(0);
    	}
    	return giveCards;
    }
    public void reset() {
		deck = fullDeck;
	}
    
    public void print(boolean full) {
        if (full)System.out.println(fullDeck);
        System.out.println(deck);
    }
    
    public void print() {
        System.out.println(deck);
    }
    
    public static void main(String[] args)
    { 
        DeckOfCards deckteste = new DeckOfCards("../files/card-file.txt");
        System.out.println(deckteste.deck);
        deckteste.reset();
        System.out.println(deckteste.deck);
        deckteste.shuffle();
        System.out.println(deckteste.deck);
        ArrayList<Card> testHand = deckteste.deal(3);
        System.out.println(deckteste.deck);
        System.out.println(testHand);
    }
}
