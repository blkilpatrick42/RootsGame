package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.Water;

public class Vine extends Entity{
	public static String identity = "Vine";
	
	public Vine(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),9,1,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		int numShrubs = AdjacentTilesHaveIdentity(Shrub.identity);
		int numPines = AdjacentTilesHaveIdentity(Pine.identity);
		int vitalityMod = 1+(numShrubs+numPines);
		//the vine blooms with flowers
		if(age % 10 == 0 && DiceRoller.RollDice(6)) { 
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new VineFlowering(gridX,gridY), gridX, gridY);
		}
		else if (DiceRoller.RollDice(6) && DiceRoller.RollDice(vitalityMod)) { //the shrub dies
			RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new VineDead(gridX,gridY), gridX, gridY);
		}
	}
}
