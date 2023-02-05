package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;

public class ShrubSapling extends Entity{
	public static String identity = "ShrubSapling";
	
	public ShrubSapling(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),1,1,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		//the shrub sapling grows into a shrub
		if(age > 10 && DiceRoller.RollDice(6)) {
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new Shrub(gridX,gridY), gridX, gridY);
		}
		else if (age > 30) { //sometimes, the shrub sapling does not make it to adulthood
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new ShrubDead(gridX,gridY), gridX, gridY);
		}
	}
}
