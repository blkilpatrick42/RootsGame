package com.mygdx.rootsgame.util;
import java.lang.Math;

public class RNG {
	public static int RollDice(int max) {		
		int randomNum = (int)(Math.random() * max);
		return randomNum;
	}
}