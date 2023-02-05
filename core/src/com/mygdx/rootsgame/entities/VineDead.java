package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.Water;

public class VineDead extends Entity{
	public static String identity = "VineUnliving"; //called unliving here because we don't want vines to overtake themselves 
	
	public VineDead(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),11,1,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		if(age > 30 && DiceRoller.RollDice(6)) { 
			RootsGame.Game.NextWorldState.SubmitFutureEntity(5, null, gridX, gridY);
		}
	}
}
