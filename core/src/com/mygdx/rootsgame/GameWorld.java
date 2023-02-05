package com.mygdx.rootsgame;
import java.lang.Math;

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
		int h = ySize;
		int w = xSize;
		int n = xSize * ySize;
		int m = 0;
		int r = 0;
		int randX = 0;
		int randY = 0;
		//Add mountains
		MountainGrid Mount;
		for (int i = 0; i < (int)(n*0.03); i++) {
			if (m < (int)(n*0.25)) {
				randX = (int)(Math.random() * w);
				randY = (int)(Math.random() * h);
				Mount = new MountainGrid(World,World[randX][randY],n,m,1,w,h);
				World = Mount.Mountainize().map;
				m = Mount.m;
			}
		}
		//Add rivers
		int riverRoll = 100;
		String natFlow;
		String curFlow;
		int riverWidth;
		int flowStop = 0;
		int flowSwitch = 0;
		int flowRoll;
		int clearanceCheck = 0;
		while(riverRoll > (int)((1 - Math.pow((n-r)/n,1))*100)) {
			//Determine width
			riverWidth = (int)(Math.random() * 100);
			if(riverWidth > 91)
				riverWidth = (int)(w * 0.15);
			else if(riverWidth > 65)
				riverWidth = (int)(w * 0.10);
			else
				riverWidth = (int)(w * 0.05);
			//Determine starting coordinate and hemisphere
			randX = (int)(Math.random() * w);
			randY = (int)(Math.random() * h);
			if(randY < h/2) {
				randY = (int)(Math.random() * 100);
				randY = ((100-randY)/400)* h;
				natFlow = "North";
				curFlow = "North";
			}
			else {
				randY = (int)(Math.random() * 100);
				randY = ((300+randY)/400) * h;
				natFlow = "South";
				curFlow = "South";
			}
			//Determine length and path
			if(randX + riverWidth > w) {
				randX = randX - riverWidth;
			}
			if(randY + riverWidth > h) {
				randY = randY - riverWidth;
			}
			flowSwitch = 0;
			while(flowStop == 0) {
				//Clearance check
				if(curFlow.equals("North") || curFlow.equals("South")) {
					for(clearanceCheck = 0; clearanceCheck < riverWidth; clearanceCheck++) {
						if(World[randX + clearanceCheck][randY].GetIdentity().equals("Mountain")) {
							flowStop = 1;
							break;
						}
						World[randX + clearanceCheck][randY] = new Water();
						World[randX + clearanceCheck][randY].SetGridLocation(randX + clearanceCheck, randY);
					}
				}else {
					for(clearanceCheck = 0; clearanceCheck < riverWidth; clearanceCheck++) {
						if(World[randX][randY + clearanceCheck].GetIdentity().equals("Mountain")) {
							flowStop = 1;
							break;
						}
						World[randX][randY + clearanceCheck] = new Water();
						World[randX][randY + clearanceCheck].SetGridLocation(randX, randY + clearanceCheck);
					}
				}
				if(flowStop == 1)
					break;
				r = r + riverWidth;
				if(curFlow.equals("North"))
					randY++;
				if(curFlow.equals("South"))
					randY--;
				if(curFlow.equals("East"))
					randX++;
				if(curFlow.equals("West"))
					randY--;
				flowSwitch++;
				if(flowSwitch == riverWidth) {
					flowRoll = (int)(Math.random() * 100);
					//THIS VALUE DETERMINES LENGTH
					if(flowRoll > 90) {
						flowStop = 2;
						break;
					}
					flowRoll = (int)(Math.random() * 100);
					//THESE VALUES DECIDE TWISTINESS
					if(natFlow.equals("North")) {
						if(curFlow.equals("North")) {
							if(flowRoll < 16)
								curFlow = "West";
							if(flowRoll > 84)
								curFlow = "East";
						}else if(curFlow.equals("West") || curFlow.equals("East")) {
							if(flowRoll < 31)
								curFlow = "North";
						}
					}
					if(natFlow.equals("South")) {
						if(curFlow.equals("South")) {
							if(flowRoll < 16)
								curFlow = "West";
							if(flowRoll > 84)
								curFlow = "East";
						}else if(curFlow.equals("West") || curFlow.equals("East")) {
							if(flowRoll < 31)
								curFlow = "South";
						}
					}
					flowSwitch = 0;
				}
			}
			if(flowStop == 2) {
				for(int i = 0; i < riverWidth; i++) {
					for(int j = 0; j < riverWidth - i; j++) {
						if(!World[randX+i][randY+j].GetIdentity().equals("Mountain")) {
							r++;
							World[randX+i][randY+j] = new Water();
							World[randX+i][randY+j].SetGridLocation(randX+i, randY+j);
						}
						if(!World[randX+i][randY-j].GetIdentity().equals("Mountain")) {
							r++;
							World[randX+i][randY-j] = new Water();
							World[randX+i][randY-j].SetGridLocation(randX+i, randY-j);
						}
						if(!World[randX-i][randY+j].GetIdentity().equals("Mountain")) {
							r++;
							World[randX-i][randY+j] = new Water();
							World[randX-i][randY+j].SetGridLocation(randX-i, randY+j);
						}
						if(!World[randX-i][randY-j].GetIdentity().equals("Mountain")) {
							r++;
							World[randX-i][randY-j] = new Water();
							World[randX-i][randY-j].SetGridLocation(randX-i, randY-j);
						}
					}
				}
			}
			riverRoll = (int)(Math.random() * 100);
		} //River generation done
		//Fill remains with soil
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				if(!World[i][j].GetIdentity().equals("Mountain")) {
					if(!World[i][j].GetIdentity().equals("Water")) {
						//I know this is disgusting but bear with me
						if(World[i+1][j].GetIdentity().equals("Water")||World[i-1][j].GetIdentity().equals("Water")||World[i][j+1].GetIdentity().equals("Water")||World[i][j-1].GetIdentity().equals("Water")) {
							World[i][j] = new Grass();
							World[i][j].SetGridLocation(i, j);
						}else {
						World[i][j] = new Soil();
						World[i][j].SetGridLocation(i, j);
						}
					}
				}
			}
		}
		
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
		initialized = true;
	}
	
	private Tile getTileFromChar(char tileChar) {
		if(tileChar == 'S')
			return new Soil();
		if(tileChar == 'W')
			return new Water();
		if(tileChar == 'G')
			return new Grass();
		if(tileChar == 'M')
			return new Mountain();
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
