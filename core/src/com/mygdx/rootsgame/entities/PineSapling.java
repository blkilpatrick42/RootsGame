package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;

public class PineSapling extends Entity{
	public static String identity = "PineSapling";
	
	public PineSapling(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),5,1,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		//the shrub sapling grows into a shrub
		if(age > 20 && DiceRoller.RollDice(6)) {
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new Pine(gridX,gridY), gridX, gridY);
		}
		else if (age > 60) { //sometimes, the pine sapling does not make it to adulthood
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new PineDead(gridX,gridY), gridX, gridY);
		}
	}
}
