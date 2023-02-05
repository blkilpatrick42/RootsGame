package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.Water;

public class Pine extends Entity{
	public static String identity = "Pine";
	
	public Pine(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),6,1,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		int numWaters = AdjacentTilesHaveIdentity(Water.identity);
		int vitalityMod = 1+numWaters*10;
		//the pine blooms with pinecones
		if(age % 40 == 0 && DiceRoller.RollDice(6)) { 
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new PineWithCones(gridX,gridY), gridX, gridY);
		}
		else if (DiceRoller.RollDice(40) && DiceRoller.RollDice(vitalityMod)) { //the pine dies
			RootsGame.Game.NextWorldState.SubmitFutureEntity(10, new PineDead(gridX,gridY), gridX, gridY);
		}
	}
}
