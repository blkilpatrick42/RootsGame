package com.mygdx.rootsgame;

import com.mygdx.rootsgame.world.*;
import com.mygdx.rootsgame.entities.*;

public class GameWorld {
	public Tile[][] World;
	boolean initialized = false;
	int clock;
	
	int worldSizeX;
	int worldSizeY;
	
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
		String worldSeed = Reader.readStringFromFile("test.txt");
		for(int y = 0; y < worldSizeY; y++) {
			for(int x = 0; x < worldSizeX; x++) {
				if(x % 2 == 0) {
					World[x][y] = getTileFromChar(worldSeed.charAt(x + (y*worldSizeX)));
					World[x][y].SetGridLocation(x, y);
				}
				else {
					World[x][y] = getTileFromChar(worldSeed.charAt(x + (y*worldSizeX)));
					World[x][y].SetGridLocation(x, y);
				}
			}
		}
		initialized = true;
	}
	
	private Tile getTileFromChar(char tileChar) {
		if(tileChar == 'S')
			return new Soil();
		if(tileChar == 'W')
			return new Water();
		if(tileChar == 'G')
			return new Grass();
		else
			return new Soil();
	}

	//Advances the world clock by one tick
	public void advanceClock() {
		clock++;
		try {
			GameWorld currentWorld = (GameWorld)this.clone();
			for(Tile[] tiles: World) {
				for(Tile tile: tiles) {
					tile.Tick(currentWorld);
				}
			}
		}
		catch(Exception ex) {
			//TODO: :)
		}		
	}
}
