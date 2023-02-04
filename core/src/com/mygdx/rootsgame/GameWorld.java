package com.mygdx.rootsgame;

import com.mygdx.rootsgame.world.*;
import com.mygdx.rootsgame.entities.*;

public class GameWorld {
	public Tile[][] World;
	boolean initialized = false;
	int clock;
	
	int worldSizeX;
	int worldSizeY;
	
	//TODO: static FutureWorldMember
	
	//gameworld constructor which creates a game world of xSize tiles wide and ySize tiles high
	public GameWorld(int xSize, int ySize) {
		World = new Tile[xSize][ySize];
		clock = 0;
		worldSizeX = xSize;
		worldSizeY = ySize;
	}
	
	//populates the game world with tiles
	public void initialize() {
		//TODO: go through GameWorld and set them to objects
		for(int y = 0; y < worldSizeY; y++) {
			for(int x = 0; x < worldSizeX; x++) {
				if(x % 2 == 0) {
					World[x][y] = new Soil();
					World[x][y].SetGridLocation(x, y);
					if(x % 6 == 0) {
						World[x][y].SetSurfaceEntity(new RedFlower(x,y));
					}
					if(x % 7 == 0) {
						World[x][y].SetSurfaceEntity(new YellowFlower(x,y));
					}
				}
				else {
					World[x][y] = new Water();
					World[x][y].SetGridLocation(x, y);
				}
			}
		}
		initialized = true;
	}
	
	//Advances the world clock by one tick
	public void AdvanceClock() {
		clock++;
		try {
			for(Tile[] tiles: World) {
				for(Tile tile: tiles) {
					tile.AdvanceClock(this);
				}
			}
		}
		catch(Exception ex) {
			//TODO: :)
		}		
	}
}
