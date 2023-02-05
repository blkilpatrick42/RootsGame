package com.mygdx.rootsgame.util;
import java.lang.Math;

public class DiceRollerPF {
	public static boolean RollDice(int max, int toPass) {		
		int randomNum = (int)(Math.random() * max);
		return randomNum >= toPass;
	}
}