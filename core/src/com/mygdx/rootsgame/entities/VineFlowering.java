package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.WorldObject.TileDir;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.*;

public class VineFlowering extends Entity{
	public static String identity = "VineFlowering";
	
	public VineFlowering(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),10,1,16);
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
				int vineGrowthPrecedence = 10;
				int chanceModifier = 20;
				if(adjacentNorth != null && (adjacentNorth.surfaceEntity == null || adjacentNorth.surfaceEntity.GetIdentity().contains("Dead")) && !adjacentNorth.GetIdentity().contains(Water.identity)) {
					   if(DiceRoller.RollDice(chanceModifier)) {
						   Grass newGrassTile = new Grass();
						   newGrassTile.SetGridLocation(gridX, gridY+1);
						   RootsGame.Game.NextWorldState.SubmitFutureEntity(vineGrowthPrecedence, new Vine(gridX, gridY+1), gridX, gridY+1);
					   }
					}
					if(adjacentNorthEast != null && (adjacentNorthEast.surfaceEntity== null || adjacentNorthEast.surfaceEntity.GetIdentity().contains("Dead")) && !adjacentNorthEast.GetIdentity().contains(Water.identity)) {
						if(DiceRoller.RollDice(chanceModifier)) {
							RootsGame.Game.NextWorldState.SubmitFutureEntity(vineGrowthPrecedence, new Vine(gridX+1, gridY+1), gridX+1, gridY+1);
						}
					}
					if(adjacentEast != null && (adjacentEast.surfaceEntity == null || adjacentEast.surfaceEntity.GetIdentity().contains("Dead")) && !adjacentEast.GetIdentity().contains(Water.identity)) {
						if(DiceRoller.RollDice(chanceModifier)) {
							RootsGame.Game.NextWorldState.SubmitFutureEntity(vineGrowthPrecedence, new Vine(gridX+1, gridY), gridX+1, gridY);
						}
					}
					if(adjacentSouthEast != null && (adjacentSouthEast.surfaceEntity == null || adjacentSouthEast.surfaceEntity.GetIdentity().contains("Dead")) && !adjacentSouthEast.GetIdentity().contains(Water.identity)) {
						if(DiceRoller.RollDice(chanceModifier)) {
							RootsGame.Game.NextWorldState.SubmitFutureEntity(vineGrowthPrecedence, new Vine(gridX+1, gridY-1), gridX+1, gridY-1);
						}
					}
					if(adjacentSouth != null && (adjacentSouth.surfaceEntity == null || adjacentSouth.surfaceEntity.GetIdentity().contains("Dead")) && !adjacentSouth.GetIdentity().contains(Water.identity)) {
						if(DiceRoller.RollDice(chanceModifier)) {
							RootsGame.Game.NextWorldState.SubmitFutureEntity(vineGrowthPrecedence, new Vine(gridX, gridY-1), gridX, gridY-1);
						}
					}
					if(adjacentSouthWest != null && (adjacentSouthWest.surfaceEntity == null || adjacentSouthWest.surfaceEntity.GetIdentity().contains("Dead")) && !adjacentSouthWest.GetIdentity().contains(Water.identity)) {
						if(DiceRoller.RollDice(chanceModifier)) {
							RootsGame.Game.NextWorldState.SubmitFutureEntity(vineGrowthPrecedence, new Vine(gridX-1, gridY-1), gridX-1, gridY-1);
						}
					}
					if(adjacentWest != null && (adjacentWest.surfaceEntity == null || adjacentWest.surfaceEntity.GetIdentity().contains("Dead")) && !adjacentWest.GetIdentity().contains(Water.identity)) {
						if(DiceRoller.RollDice(chanceModifier)) {
							RootsGame.Game.NextWorldState.SubmitFutureEntity(vineGrowthPrecedence, new Vine(gridX-1, gridY), gridX-1, gridY);
						}
					}
					if(adjacentNorthWest != null && (adjacentNorthWest.surfaceEntity == null || adjacentNorthWest.surfaceEntity.GetIdentity().contains("Dead")) && !adjacentNorthWest.GetIdentity().contains(Water.identity)) {
						if(DiceRoller.RollDice(chanceModifier)) {
							RootsGame.Game.NextWorldState.SubmitFutureEntity(vineGrowthPrecedence, new Vine(gridX-1, gridY+1), gridX-1, gridY+1);
						}
					
				//shrub exits fruiting stage and returns to regular shrub stage
			    if(age > 15) {
			    	RootsGame.Game.NextWorldState.SubmitFutureEntity(vineGrowthPrecedence, new Vine(gridX, gridY), gridX, gridY);
			    }
	}
	}
}
