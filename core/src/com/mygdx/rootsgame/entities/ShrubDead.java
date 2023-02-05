package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.Water;

public class ShrubDead extends Entity{
	public static String identity = "ShrubDead"; 
	
	public ShrubDead(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),4,1,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		//the dead shrub disappears
		if(age > 20 && DiceRoller.RollDice(6)) { 
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, null, gridX, gridY);
		}
	}
}
