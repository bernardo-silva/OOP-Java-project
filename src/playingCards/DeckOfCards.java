package playingCards;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * _________________________________________________________________
 *
 */
public class DeckOfCards {
	private final ArrayList<Card> fullDeck = new ArrayList<Card>();
	private ArrayList<Card> deck = new ArrayList<Card>();

	/**
	 * Constructor method for a default deck; builds the full deck on both variables fullDeck and deck
	 */
	public DeckOfCards() {
		for (int suit = 0; suit <= 3; suit++) {
			for (int face = 2; face <= 14; face++) {
				fullDeck.add(new Card(face, suit));
				deck.add(new Card(face, suit));
			}
		}
	}

	/**
	 * Constructor method for a deck specified within a file; builds the full deck on both variables fullDeck and deck
	 * @param filename name of the file with the deck of cards
	 */
	public DeckOfCards(String filename) {
		try {
			File deckFile = new File(filename);
			Scanner scanDeck = new Scanner(deckFile);
			Card card;

			scanDeck.useDelimiter("\\s+");

			while (scanDeck.hasNext()) {
				card = new Card(scanDeck.next());

				fullDeck.add(card);
				deck.add(card);
			}

			scanDeck.close();
		} catch (FileNotFoundException e) {
			System.out.println("Card file " + filename + " not found.");
			System.out.println("Exiting.");
			System.exit(1);
		}
	}

	/**
	 * Deals a specified number of cards. 
	 * @param nCards number of cards to deal
	 * @return array of cards from the deck
	 * @exception IllegalArgumentException if the number of dealt cards is non positive
	 */
	public ArrayList<Card> deal(int nCards) {
		if (nCards <= 0)
			throw new IllegalArgumentException("Number of dealt cards must be greater than 0.");

		ArrayList<Card> giveCards = new ArrayList<Card>(nCards);
		for (int i = 0; i < nCards; i++) {
			giveCards.add(deck.get(0));
			deck.remove(0);
		}
		return giveCards;
	}

	/**
	 * Gets the deck.
	 * @return the deck
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}

	/**
	 * Gets the full deck.
	 * @return the full deck
	 */
	public ArrayList<Card> getFullDeck() {
		return fullDeck;
	}

	/**
	 * Prints the number of cards in the deck, as well as each individual card.
	 */
	public void print() {
		System.out.println("Deck with " + this.size() + " cards.");
		for (Card card : deck) {
			System.out.print(card + " ");
		}
		System.out.println();
	}

	/**
	 * Resets the deck to the fulldeck.
	 */
	public void reset() {
		deck = new ArrayList<Card>(fullDeck);
	}

	/**
	 * Gets the size of the deck.
	 * @return the size of the deck
	 */
	public int size() {
		return deck.size();
	}

	/**
	 * Shuffles the deck.
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}

	@Override
	public String toString() {
		return "" + deck;
	}
}
