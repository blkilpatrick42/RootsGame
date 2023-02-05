package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.Water;

public class PineDead extends Entity{
	public static String identity = "PineDead"; 
	
	public PineDead(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),8,1,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		if(age > 20 && DiceRoller.RollDice(2)) { 
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, null, gridX, gridY);
		}
	}
}
