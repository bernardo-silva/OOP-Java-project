package videopoker;

import java.util.function.Function;

import cards.HandOfCards;

public enum StrategyCheckers {
	STRAT1(Strategy::strat1),
	STRAT2(Strategy::strat2);

	Function<HandOfCards, int[]> checker;
	
	StrategyCheckers(Function<HandOfCards, int[]> _checker){
		checker = _checker;
	}
	
	int[] check(HandOfCards hand) {
		return checker.apply(hand);
	}

}
