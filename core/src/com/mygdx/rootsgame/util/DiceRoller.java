package com.mygdx.rootsgame.util;
import java.util.Random;

public class DiceRoller {
	static Random rand = new Random(System.currentTimeMillis());
	
	public static boolean RollDice(int sides) {		
		int randomNum = rand.nextInt(sides) + 1;
		return randomNum == 1;
	}
}
