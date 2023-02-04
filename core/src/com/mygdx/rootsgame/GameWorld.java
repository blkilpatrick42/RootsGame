package com.mygdx.rootsgame;

import com.mygdx.rootsgame.world.*;
import com.mygdx.rootsgame.entities.*;

public class GameWorld {
	public Tile[][] World;
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
					World[x][y] = getTileFromChar(worldSeed.charAt(x + (y*worldSizeX)));
					World[x][y].SetGridLocation(x, y);
			}
		}
		initializeEntities();
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
					World[x][y].SetSurfaceEntity(getEntityFromChar(entitySeed.charAt(x + (y*worldSizeX)),x,y));
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
	
	public long diff, start = System.currentTimeMillis(); //gets current system time in Millisecs
	int slowSpeed = 1;
	int mediumSpeed = 2;
	int fastSpeed = 3;
	public enum TimeSpeed{
		slow,
		medium,
		fast
	}
	
	public int GetTimeSpeed(TimeSpeed speed) {
		int retSpeed = 0;
		switch(speed) {
			 case slow: retSpeed = slowSpeed;
	         break;
			 case medium: retSpeed = mediumSpeed;
	         break;
			 case fast:  retSpeed = fastSpeed;
	         break;
		}
		return retSpeed;
	}
	
	//Tick function, limits the program to the fps
    public void sleep(int fps) {
        if (fps > 0) {
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000 / fps;
            if (diff < targetDelay) {
                try {
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {
                }
            }
            start = System.currentTimeMillis();
        }
    }

	//Advances the world clock by one tick
	public void AdvanceClock() {
		sleep(GetTimeSpeed(TimeSpeed.slow));
		NextWorldState = new FutureWorld(this);
		clock++;
		
		//run rules for all entities in the grid, which will all make
		//calls to influence the static FutureWorld member NextWorldState
		for(Tile[] tiles: World) {
			for(Tile tile: tiles) {
				tile.AdvanceClock();
			}
		}	
		
		//swap the current game world with the future world
		World = NextWorldState.newWorld;
	}
}
