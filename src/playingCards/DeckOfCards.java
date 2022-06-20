package playingCards;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class implements a deck of cards, used to deal cards from.
 * <p>
 * A standard deck of 52 cards can be created, as well as an arbitrary one read
 * from a file. The deck can be shuffled an reset to the initial state.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class DeckOfCards {
	private final ArrayList<Card> fullDeck = new ArrayList<Card>();
	private ArrayList<Card> deck = new ArrayList<Card>();

	/**
	 * Constructs a standard deck of 52 cards.
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
	 * Constructs an arbitrary deck read from a file.
	 * @param filename name of the file with the card representations
         * separated by whitespace
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
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("Error on card file " + filename);
			System.out.println("Exiting.");
			System.exit(1);
		}
	}

	/**
	 * Deals a specified number of cards. 
	 * @param nCards number of cards to deal
	 * @return ArrayList of cards dealt from the deck
	 * @exception IllegalArgumentException if the number of dealt cards is non positive
         * or if the amount of cards to deal is greater than the amount of
         * available cards.
	 */
	public ArrayList<Card> deal(int nCards) {
		if (nCards <= 0)
			throw new IllegalArgumentException("Number of dealt cards must be greater than 0.");
		if (nCards > deck.size())
			throw new IllegalArgumentException("Insufficient amount of cards to deal.");

		ArrayList<Card> giveCards = new ArrayList<Card>(nCards);
		for (int i = 0; i < nCards; i++) {
			giveCards.add(deck.get(0));
			deck.remove(0);
		}
		return giveCards;
	}

	/**
	 * Returns an ArrayList with the cards in the deck.
	 * @return an ArrayList with the cards in the deck.
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}

	/**
	 * Returns an ArrayList with the initial cards in the deck.
	 * @return an ArrayList with the initial cards in the deck.
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
	 * Resets the deck to the initial state.
	 */
	public void reset() {
		deck = new ArrayList<Card>(fullDeck);
	}

	/**
	 * Returns the number of cards in the deck.
	 * @return the number of cards in the deck
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

	/**
	 * Returns a string representation of the deck.
         * @return string representation of the deck
	 */
	@Override
	public String toString() {
		return "" + deck;
	}
}
