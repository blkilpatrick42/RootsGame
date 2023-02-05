package com.mygdx.rootsgame;
import java.util.Arrays;

import com.mygdx.rootsgame.world.*;
import com.mygdx.rootsgame.entities.*;

public class FutureWorld {
	GameWorld OldWorld;
	public Tile[][] newWorld;
	int[][] precedence;	
	
	public FutureWorld(GameWorld basis) {
		OldWorld = basis;
		precedence = new int[basis.worldSizeX][basis.worldSizeY];
		for (int[] column: precedence) {
			Arrays.fill(column, 0);
		}
		
		newWorld = new Tile[basis.worldSizeX][basis.worldSizeY];

		for (Tile[] tiles: basis.World) {
			for(Tile tile: tiles) {
				newWorld[tile.gridX][tile.gridY] = tile;
			}
		}	
	}
	
	public void SubmitFutureTile(int newPrecedence, Tile replacement, int x, int y){
		//if the tile future comes without an entity, just keep the existing one
		if(replacement.surfaceEntity == null) {
			replacement.surfaceEntity = newWorld[x][y].surfaceEntity;
		}
		
		if(newPrecedence > precedence[x][y]) {
			newWorld[x][y] = replacement;
			precedence[x][y] = newPrecedence;
		}
	}
	
	public void SubmitFutureEntity(int newPrecedence, Entity replacement, int x, int y){
		Tile replacementTile = newWorld[x][y];
		replacementTile.SetSurfaceEntity(replacement);
		
		if(newPrecedence > precedence[x][y]) {
			newWorld[x][y] = replacementTile;
			precedence[x][y] = newPrecedence;
		}
	}
	
}
