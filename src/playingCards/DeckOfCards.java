package playingCards;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {
	private final ArrayList<Card> fullDeck = new ArrayList<Card>();
	private ArrayList<Card> deck = new ArrayList<Card>();

	public DeckOfCards() { // Default deck
		for (int suit = 0; suit <= 3; suit++) {
			for (int face = 2; face <= 14; face++) {
				fullDeck.add(new Card(face, suit));
				deck.add(new Card(face, suit));
			}
		}
	}

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

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Card> getFullDeck() {
		return fullDeck;
	}

	public void print() {
		System.out.println("Deck with " + this.size() + " cards.");
		for (Card card : deck) {
			System.out.print(card + " ");
		}
		System.out.println();
	}

	public void reset() {
		deck = new ArrayList<Card>(fullDeck);
	}

	public int size() {
		return deck.size();
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	@Override
	public String toString() {
		return "" + deck;
	}
}
