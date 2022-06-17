package videopoker;

import java.util.ArrayList;
import java.util.function.Function;

import playingCards.HandOfCards;

public enum StrategyCheckers {
	STRAT1(Strategy::strat1),
	STRAT2(Strategy::strat2),
	STRAT3(Strategy::strat3),
	STRAT4(Strategy::strat4),
	STRAT5(Strategy::strat5),
	STRAT6(Strategy::strat6),
	STRAT7(Strategy::strat7),
	STRAT8(Strategy::strat8),
	STRAT9(Strategy::strat9),
	STRAT10(Strategy::strat10),
	STRAT11(Strategy::strat11),
	STRAT12(Strategy::strat12),
	STRAT13(Strategy::strat13),
	STRAT14(Strategy::strat14),
	STRAT15(Strategy::strat15),
	STRAT16(Strategy::strat16),
	STRAT17(Strategy::strat17),
	STRAT18(Strategy::strat18),
	STRAT19(Strategy::strat19),
	STRAT20(Strategy::strat20),
	STRAT21(Strategy::strat21),
	STRAT22(Strategy::strat22),
	STRAT23(Strategy::strat23),
	STRAT24(Strategy::strat24),
	STRAT25(Strategy::strat25),
	STRAT26(Strategy::strat26),
	STRAT27(Strategy::strat27),
	STRAT28(Strategy::strat28),
	STRAT29(Strategy::strat29),
	STRAT30(Strategy::strat30),
	STRAT31(Strategy::strat31),
	STRAT32(Strategy::strat32),
	STRAT33(Strategy::strat33);

	Function<HandOfCards, ArrayList<Integer>> checker;
	
	StrategyCheckers(Function<HandOfCards, ArrayList<Integer>> _checker){
		checker = _checker;
	}
	
	ArrayList<Integer> check(HandOfCards hand) {
		return checker.apply(hand);
	}

}
