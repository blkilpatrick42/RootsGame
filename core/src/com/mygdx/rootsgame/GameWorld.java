package com.mygdx.rootsgame;

import com.mygdx.rootsgame.world.*;
import com.mygdx.rootsgame.entities.*;

public class GameWorld {
	public Tile[][] World;
	boolean initialized = false;
	int clock;
	
	public int worldSizeX;
	public int worldSizeY;
	
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
		String worldSeed = Reader.readStringFromFile("test.txt");
		for(int y = 0; y < worldSizeY; y++) {
			for(int x = 0; x < worldSizeX; x++) {
					World[x][worldSizeY - y - 1] = getTileFromChar(worldSeed.charAt(x + (y*worldSizeX)));
					World[x][worldSizeY - y - 1].SetGridLocation(x, worldSizeY - y - 1);
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
	
	public void initializeE() {
		String entitySeed = Reader.readStringFromFile("test2.txt");
		for(int y = 0; y < worldSizeY; y++) {
			for(int x = 0; x < worldSizeX; x++) {
					World[x][worldSizeY - y - 1].SetSurfaceEntity(getEntityFromChar(entitySeed.charAt(x + ((worldSizeY - y - 1)*worldSizeX)),x,worldSizeY - y - 1));
			}
		}
		initialized = true;
	}
	
	private Entity getEntityFromChar(char tileChar, int x, int y) {
		if(tileChar == 'R')
			return new RedFlower(x,y);
		if(tileChar == 'Y')
			return new YellowFlower(x,y);
		else
			return null;
	}

	//Advances the world clock by one tick
	public void AdvanceClock() {
		clock++;
		for(Tile[] tiles: World) {
			for(Tile tile: tiles) {
				tile.AdvanceClock(this);
			}
		}	
	}
}
