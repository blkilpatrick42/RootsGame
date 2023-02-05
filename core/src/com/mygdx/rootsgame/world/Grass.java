package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.*;
import com.mygdx.rootsgame.entities.Flower;
import com.mygdx.rootsgame.util.DiceRoller;

public class Grass extends Tile {
	public static String identity = "Grass";	
	
	public Grass() {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),0,0,16);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		int numFlowers = AdjacentTilesHaveEntity(Flower.identity);
		int numWaters = AdjacentTilesHaveIdentity(Water.identity);
		int chanceModifier = 10 - numFlowers;
		int liveChanceModifier = 2 + numWaters*3;
		
		//get adjacent tiles
		Tile adjacentNorth = GetAdjacentTile(TileDir.north);
		Tile adjacentNorthEast = GetAdjacentTile(TileDir.northEast);
		Tile adjacentEast = GetAdjacentTile(TileDir.east);
		Tile adjacentSouthEast = GetAdjacentTile(TileDir.southEast);
		Tile adjacentSouth = GetAdjacentTile(TileDir.south);
		Tile adjacentSouthWest = GetAdjacentTile(TileDir.southWest);
		Tile adjacentWest = GetAdjacentTile(TileDir.west);
		Tile adjacentNorthWest = GetAdjacentTile(TileDir.northWest);
		
		//model grass growth to adjacent tiles
		int soilGrowthPrecedence = 1;
		if(adjacentNorth != null && adjacentNorth.GetIdentity().contains(Soil.identity)) {
		   if(DiceRoller.RollDice(chanceModifier)) {
			   Grass newGrassTile = new Grass();
			   newGrassTile.SetGridLocation(gridX, gridY+1);
			   RootsGame.Game.NextWorldState.SubmitFutureTile(soilGrowthPrecedence, newGrassTile, gridX, gridY+1);
		   }
		}
		if(adjacentNorthEast != null && adjacentNorthEast.GetIdentity().contains(Soil.identity)) {
			if(DiceRoller.RollDice(chanceModifier)) {
				Grass newGrassTile = new Grass();
				newGrassTile.SetGridLocation(gridX+1, gridY+1);
				RootsGame.Game.NextWorldState.SubmitFutureTile(soilGrowthPrecedence, newGrassTile, gridX+1, gridY+1);
			}
		}
		if(adjacentEast != null && adjacentEast.GetIdentity().contains(Soil.identity)) {
			if(DiceRoller.RollDice(chanceModifier)) {
				Grass newGrassTile = new Grass();
				newGrassTile.SetGridLocation(gridX + 1, gridY);
				RootsGame.Game.NextWorldState.SubmitFutureTile(soilGrowthPrecedence, newGrassTile, gridX+1, gridY);
			}
		}
		if(adjacentSouthEast != null && adjacentSouthEast.GetIdentity().contains(Soil.identity)) {
			if(DiceRoller.RollDice(chanceModifier)) {
				Grass newGrassTile = new Grass();
				newGrassTile.SetGridLocation(gridX+1, gridY-1);
				RootsGame.Game.NextWorldState.SubmitFutureTile(soilGrowthPrecedence, newGrassTile, gridX+1, gridY-1);
			}
		}
		if(adjacentSouth != null && adjacentSouth.GetIdentity().contains(Soil.identity)) {
			if(DiceRoller.RollDice(chanceModifier)) {
				Grass newGrassTile = new Grass();
				newGrassTile.SetGridLocation(gridX, gridY-1);
				RootsGame.Game.NextWorldState.SubmitFutureTile(soilGrowthPrecedence, newGrassTile, gridX, gridY-1);
			}
		}
		if(adjacentSouthWest != null && adjacentSouthWest.GetIdentity().contains(Soil.identity)) {
			if(DiceRoller.RollDice(chanceModifier)) {
				Grass newGrassTile = new Grass();
				newGrassTile.SetGridLocation(gridX-1, gridY-1);
				RootsGame.Game.NextWorldState.SubmitFutureTile(soilGrowthPrecedence, newGrassTile, gridX-1, gridY-1);
			}
		}
		if(adjacentWest != null && adjacentWest.GetIdentity().contains(Soil.identity)) {
			if(DiceRoller.RollDice(chanceModifier)) {
				Grass newGrassTile = new Grass();
				newGrassTile.SetGridLocation(gridX-1, gridY);
				RootsGame.Game.NextWorldState.SubmitFutureTile(soilGrowthPrecedence, newGrassTile, gridX-1, gridY);
			}
		}
		if(adjacentNorthWest != null && adjacentNorthWest.GetIdentity().contains(Soil.identity)) {
			if(DiceRoller.RollDice(chanceModifier)) {
				Grass newGrassTile = new Grass();
				newGrassTile.SetGridLocation(gridX-1, gridY+1);
				RootsGame.Game.NextWorldState.SubmitFutureTile(soilGrowthPrecedence, newGrassTile, gridX-1, gridY+1);
			}
		}
		
		//model grass dying
		
		int grassDeathPrecedence = 1;
		if(DiceRoller.RollDice(liveChanceModifier)) {
			//this grass dies, becomes soil again
			Soil newSoilTile = new Soil();
			newSoilTile.SetGridLocation(gridX, gridY);
			RootsGame.Game.NextWorldState.SubmitFutureTile(grassDeathPrecedence, newSoilTile, gridX, gridY);
		}
		
	}
}
