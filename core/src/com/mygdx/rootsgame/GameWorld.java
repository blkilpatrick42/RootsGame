package com.mygdx.rootsgame;

import com.mygdx.rootsgame.world.*;

import java.util.Arrays;
import java.lang.*;

import com.mygdx.rootsgame.entities.*;

public class GameWorld {
	public Tile[][] World;
	public Tile[][] PlacementWorld;
	boolean initialized = false;
	int clock;
	
	public int worldSizeX;
	public int worldSizeY;
	
	public static FutureWorld NextWorldState;
	
	//gameworld constructor which creates a game world of xSize tiles wide and ySize tiles high
	public GameWorld(int xSize, int ySize) {
		World = new Tile[xSize][ySize];
		clock = 0;
		worldSizeX = xSize;
		worldSizeY = ySize;
	}
	
	//populates the game world with tiles
	public void initialize() {
		String worldSeed = Reader.readStringFromFile("tiles.txt");
		for(int y = 0; y < worldSizeY; y++) {
			for(int x = 0; x < worldSizeX; x++) {
					World[x][worldSizeY - y - 1] = getTileFromChar(worldSeed.charAt(x + (y*worldSizeX)));
					World[x][worldSizeY - y - 1].SetGridLocation(x, worldSizeY - y - 1);
			}
		}
		initializeEntities();
		PlacementWorld = World;
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
	
	public void initializeEntities() {
		String entitySeed = Reader.readStringFromFile("entities.txt");
		for(int y = 0; y < worldSizeY; y++) {
			for(int x = 0; x < worldSizeX; x++) {
					World[x][worldSizeY - y - 1].SetSurfaceEntity(getEntityFromChar(entitySeed.charAt(x + ((worldSizeY - y - 1)*worldSizeX)),x,worldSizeY - y - 1));
			}
		}
		initialized = true;
	}
	
	private Entity getEntityFromChar(char tileChar, int x, int y) {

		switch(Character.toUpperCase(tileChar)) {
		case 'R':
		case 'B':
		case 'Y':
		case 'P':
		case 'W':
		case 'G':
		case 'O':
			return new Flower (x,y,tileChar,1);
		}
		return null;
	}

	//Advances the world clock by one tick
	public void AdvanceClock() {
		//sleep(GetTimeSpeed(TimeSpeed.slow));
		NextWorldState = new FutureWorld(this);
		clock++;
		
		//run rules for all entities in the grid, which will all make
		//calls to influence the static FutureWorld member NextWorldState
		for(Tile[] tiles: World) {
			for(Tile tile: tiles) {
				if(PlacementWorld[tile.gridX][tile.gridY].surfaceEntity != null && tile.surfaceEntity == null)
					tile = PlacementWorld[tile.gridX][tile.gridY];
				else
					tile.AdvanceClock();
			}
		}	
		
		//swap the current game world with the future world
		World = NextWorldState.newWorld;
	}
}
