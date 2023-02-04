package com.mygdx.rootsgame.util;
import java.util.Random;
import java.util.stream.IntStream;

public class DiceRoller {
	public static boolean RollDice(int sides) {
		Random rand = new Random(System.currentTimeMillis());
		int randomNum = rand.nextInt(sides) + 1;
		return randomNum == 1;
	}
}
