package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.Water;

public class Shrub extends Entity{
	public static String identity = "Shrub";
	
	public Shrub(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),2,1,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		int numWaters = AdjacentTilesHaveIdentity(Water.identity);
		int vitalityMod = 9 - numWaters;
		//the shrub blooms with berries
		if(age % 20 == 0 && DiceRoller.RollDice(vitalityMod)) { 
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new ShrubWithBerries(gridX,gridY), gridX, gridY);
		}
		else if (DiceRoller.RollDice(100) && !DiceRoller.RollDice(vitalityMod)) { //the shrub dies
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new ShrubDead(gridX,gridY), gridX, gridY);
		}
	}
}
