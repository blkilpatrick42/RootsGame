package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.WorldObject.TileDir;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.*;

public class ShrubWithBerries extends Entity{
	public static String identity = "Shrub";
	
	public ShrubWithBerries(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),3,1,16);
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
				
				//model grass growth to adjacent tiles
				int shrubGrowthPrecedence = 1;
				int chanceModifier = 20;
				if(adjacentNorth != null && adjacentNorth.surfaceEntity == null && !adjacentNorth.GetIdentity().contains(Water.identity)) {
				   if(DiceRoller.RollDice(chanceModifier)) {
					   Grass newGrassTile = new Grass();
					   newGrassTile.SetGridLocation(gridX, gridY+1);
					   RootsGame.Game.NextWorldState.SubmitFutureEntity(shrubGrowthPrecedence, new ShrubSapling(gridX, gridY+1), gridX, gridY+1);
				   }
				}
				if(adjacentNorthEast != null && adjacentNorthEast.surfaceEntity== null && !adjacentNorthEast.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(shrubGrowthPrecedence, new ShrubSapling(gridX+1, gridY+1), gridX+1, gridY+1);
					}
				}
				if(adjacentEast != null && adjacentEast.surfaceEntity == null && !adjacentEast.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(shrubGrowthPrecedence, new ShrubSapling(gridX+1, gridY), gridX+1, gridY);
					}
				}
				if(adjacentSouthEast != null && adjacentSouthEast.surfaceEntity == null && !adjacentSouthEast.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(shrubGrowthPrecedence, new ShrubSapling(gridX+1, gridY-1), gridX+1, gridY-1);
					}
				}
				if(adjacentSouth != null && adjacentSouth.surfaceEntity == null && !adjacentSouth.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(shrubGrowthPrecedence, new ShrubSapling(gridX, gridY-1), gridX, gridY-1);
					}
				}
				if(adjacentSouthWest != null && adjacentSouthWest.surfaceEntity == null && !adjacentSouthWest.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(shrubGrowthPrecedence, new ShrubSapling(gridX-1, gridY-1), gridX-1, gridY-1);
					}
				}
				if(adjacentWest != null && adjacentWest.surfaceEntity == null && !adjacentWest.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(shrubGrowthPrecedence, new ShrubSapling(gridX-1, gridY), gridX-1, gridY);
					}
				}
				if(adjacentNorthWest != null && adjacentNorthWest.surfaceEntity == null && !adjacentNorthWest.GetIdentity().contains(Water.identity)) {
					if(DiceRoller.RollDice(chanceModifier)) {
						RootsGame.Game.NextWorldState.SubmitFutureEntity(shrubGrowthPrecedence, new ShrubSapling(gridX-1, gridY+1), gridX-1, gridY+1);
					}
					
				//shrub exits fruiting stage and returns to regular shrub stage
			    if(age > 15) {
			    	RootsGame.Game.NextWorldState.SubmitFutureEntity(shrubGrowthPrecedence, new Shrub(gridX, gridY), gridX, gridY);
			    }
	}
	}
}
