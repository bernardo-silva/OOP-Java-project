package videopoker.pokerHands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import playingCards.HandOfCards;

public class Straight extends PokerHand {

	public Straight(String name, int[] payout) {
		super(name, payout);
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		if( hand.getUniqueFaces() !=  5) return false;

		ArrayList<Integer> freqArr = hand.getFrequenciesArr();
		freqArr.replaceAll(e -> (e>0)?1:0);

		List<Integer> pattern = Arrays.asList(1,1,1,1,1);
		int subArrIdx = Collections.indexOfSubList(freqArr, pattern);
		return subArrIdx != -1;
	}
}
