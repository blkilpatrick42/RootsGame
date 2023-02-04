package com.mygdx.rootsgame.world;
import java.util.Arrays;

import com.mygdx.rootsgame.GameWorld;

public class FutureWorld {
	GameWorld oldWorld;
	Tile[][] newWorld;
	int[][] precedence;
			
	
	public FutureWorld(GameWorld basis) {
		this.oldWorld = basis;
		int[][] precedence = new int[oldWorld.worldSizeX][oldWorld.worldSizeY];
		for (int[] column: precedence) {
			Arrays.fill(column, 0);
		}
		Tile[][] newWorld = new Tile[oldWorld.worldSizeX][oldWorld.worldSizeY];
		int i = 0;
		int j = 0;
		for (Tile[] row: newWorld) {
			for(Tile column: row) {
				column = new Soil();
				column.SetGridLocation(i, j);
				j++;
			}
			i++;
			j = 0;
		}
		
	}
	
	public void FutureSubmit(int subPrec, Tile replacement){
		if(subPrec > precedence[replacement.gridX][replacement.gridY]) {
			newWorld[replacement.gridX][replacement.gridY] = replacement;
			precedence[replacement.gridX][replacement.gridY] = subPrec;
		}
	}
	
}
