package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.WorldObject.TileDir;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.*;

public class PineWithCones extends Entity{
	public static String identity = "PineWithCones";
	
	public PineWithCones(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),7,1,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
	
	public void ExecuteRules() {
		//get adjacent tiles
				Tile adjacentNorth = GetAdjacentTile(TileDir.north);
				Tile adjacentNorthEast = GetAdjacentTile(TileDir.northEast);
				Tile adjacentEast = GetAdjacentTile(TileDir.east);
				Tile adjacentSouthEast = GetAdjacentTile(TileDir.southEast);
				Tile adjacentSouth = GetAdjacentTile(TileDir.south);
				Tile adjacentSouthWest = GetAdjacentTile(TileDir.southWest);
				Tile adjacentWest = GetAdjacentTile(TileDir.west);
				Tile adjacentNorthWest = GetAdjacentTile(TileDir.northWest);
				
				//model pine growth to adjacent tiles
				int pineGrowthPrecedence = 5;
				int chanceModifier = 20;
				if(adjacentNorth != null && (adjacentNorth.surfaceEntity == null || adjacentNorth.surfaceEntity.GetIdentity().equals(ShrubSapling.identity)) && !adjacentNorth.GetIdentity().contains(Water.identity)) {
				   if(DiceRoller.RollDice(chanceModifier)) {
					   Grass newGrassTile = new Grass();
					   newGrassTile.SetGridLocation(gridX, gridY+1);
					   RootsGame.Game.NextWorldState.SubmitFutureEntity(pineGrowthPrecedence, new PineSapling(gridX, gridY+1), gridX, gridY+1);
				   }
				}
				if(adjacentNorthEast != null && (adjacentNorthEast.surfaceEntity== null || adjacentNorthEast.surfaceEntity.GetIdentity().equals(ShrubSapling.identity)) && !adjacentNorthEast.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(pineGrowthPrecedence, new PineSapling(gridX+1, gridY+1), gridX+1, gridY+1);
					}
				}
				if(adjacentEast != null && (adjacentEast.surfaceEntity == null || adjacentEast.surfaceEntity.GetIdentity().equals(ShrubSapling.identity)) && !adjacentEast.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(pineGrowthPrecedence, new PineSapling(gridX+1, gridY), gridX+1, gridY);
					}
				}
				if(adjacentSouthEast != null && (adjacentSouthEast.surfaceEntity == null || adjacentSouthEast.surfaceEntity.GetIdentity().equals(ShrubSapling.identity)) && !adjacentSouthEast.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(pineGrowthPrecedence, new PineSapling(gridX+1, gridY-1), gridX+1, gridY-1);
					}
				}
				if(adjacentSouth != null && (adjacentSouth.surfaceEntity == null || adjacentSouth.surfaceEntity.GetIdentity().equals(ShrubSapling.identity)) && !adjacentSouth.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(pineGrowthPrecedence, new PineSapling(gridX, gridY-1), gridX, gridY-1);
					}
				}
				if(adjacentSouthWest != null && (adjacentSouthWest.surfaceEntity == null || adjacentSouthWest.surfaceEntity.GetIdentity().equals(ShrubSapling.identity)) && !adjacentSouthWest.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(pineGrowthPrecedence, new PineSapling(gridX-1, gridY-1), gridX-1, gridY-1);
					}
				}
				if(adjacentWest != null && (adjacentWest.surfaceEntity == null || adjacentWest.surfaceEntity.GetIdentity().equals(ShrubSapling.identity)) && !adjacentWest.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(pineGrowthPrecedence, new PineSapling(gridX-1, gridY), gridX-1, gridY);
					}
				}
				if(adjacentNorthWest != null && (adjacentNorthWest.surfaceEntity == null || adjacentNorthWest.surfaceEntity.GetIdentity().equals(ShrubSapling.identity)) && !adjacentNorthWest.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(pineGrowthPrecedence, new PineSapling(gridX-1, gridY+1), gridX-1, gridY+1);
					}
					
				//pine exits fruiting stage and returns to regular pine stage
			    if(age > 20) {
			    	RootsGame.Game.NextWorldState.SubmitFutureEntity(pineGrowthPrecedence, new Pine(gridX, gridY), gridX, gridY);
			    }
	}
	}
}
