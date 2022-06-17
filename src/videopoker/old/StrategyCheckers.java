package videopoker.old;

import java.util.ArrayList;
import java.util.function.Function;

import doubleBonusT7.DoubleBonusT7Strategy;
import playingCards.HandOfCards;

public enum StrategyCheckers {
	STRAT1(DoubleBonusT7Strategy::strat1),
	STRAT2(DoubleBonusT7Strategy::strat2),
	STRAT3(DoubleBonusT7Strategy::strat3),
	STRAT4(DoubleBonusT7Strategy::strat4),
	STRAT5(DoubleBonusT7Strategy::strat5),
	STRAT6(DoubleBonusT7Strategy::strat6),
	STRAT7(DoubleBonusT7Strategy::strat7),
	STRAT8(DoubleBonusT7Strategy::strat8),
	STRAT9(DoubleBonusT7Strategy::strat9),
	STRAT10(DoubleBonusT7Strategy::strat10),
	STRAT11(DoubleBonusT7Strategy::strat11),
	STRAT12(DoubleBonusT7Strategy::strat12),
	STRAT13(DoubleBonusT7Strategy::strat13),
	STRAT14(DoubleBonusT7Strategy::strat14),
	STRAT15(DoubleBonusT7Strategy::strat15),
	STRAT16(DoubleBonusT7Strategy::strat16),
	STRAT17(DoubleBonusT7Strategy::strat17),
	STRAT18(DoubleBonusT7Strategy::strat18),
	STRAT19(DoubleBonusT7Strategy::strat19),
	STRAT20(DoubleBonusT7Strategy::strat20),
	STRAT21(DoubleBonusT7Strategy::strat21),
	STRAT22(DoubleBonusT7Strategy::strat22),
	STRAT23(DoubleBonusT7Strategy::strat23),
	STRAT24(DoubleBonusT7Strategy::strat24),
	STRAT25(DoubleBonusT7Strategy::strat25),
	STRAT26(DoubleBonusT7Strategy::strat26),
	STRAT27(DoubleBonusT7Strategy::strat27),
	STRAT28(DoubleBonusT7Strategy::strat28),
	STRAT29(DoubleBonusT7Strategy::strat29),
	STRAT30(DoubleBonusT7Strategy::strat30),
	STRAT31(DoubleBonusT7Strategy::strat31),
	STRAT32(DoubleBonusT7Strategy::strat32),
	STRAT33(DoubleBonusT7Strategy::strat33);

	Function<HandOfCards, ArrayList<Integer>> checker;
	
	StrategyCheckers(Function<HandOfCards, ArrayList<Integer>> _checker){
		checker = _checker;
	}
	
	ArrayList<Integer> check(HandOfCards hand) {
		return checker.apply(hand);
	}

}
