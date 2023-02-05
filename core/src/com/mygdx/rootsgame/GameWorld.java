package com.mygdx.rootsgame;
import java.lang.Math;

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
	worldSizeY = ySize;}
	
	//populates the game world with tiles
	/*
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
	}*/
	public void initialize() {
		int worldArea = worldSizeX * worldSizeY;
		int mountainCount = 0;
		int riverCount = 0;
		int randX = 0;
		int randY = 0;
		for(int i = 0; i < worldSizeX; i++) {
			for(int j = 0; j < worldSizeY; j++) {
				World[i][j] = new Soil();
				World[i][j].SetGridLocation(i, j);
			}
		}
		//Add mountains
		MountainGrid Mount;
		for (int i = 0; i < (int)(worldArea*0.03); i++) {
			if (mountainCount < (int)(worldArea*0.25)) {
				randX = (int)(Math.random() * worldSizeX);
				randY = (int)(Math.random() * worldSizeY);
				Mount = new MountainGrid(World,World[randX][randY],worldArea,mountainCount,1,worldSizeX,worldSizeY);
				World = Mount.Mountainize(randX, randY).map;
				mountainCount = Mount.mountainCount;
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
		/*while(riverRoll < 95)*/for(int z = 0; z < 10; z++) {
			//Determine width
			riverWidth = (int)(Math.random() * 100);
			if(riverWidth > 91)
				riverWidth = (int)(worldSizeX * 0.15);
			else if(riverWidth > 65)
				riverWidth = (int)(worldSizeX * 0.10);
			else
				riverWidth = (int)(worldSizeX * 0.05);
			//Determine starting coordinate and hemisphere
			randX = (int)(Math.random() * worldSizeX);
			randY = (int)(Math.random() * worldSizeY);
			if(randY < worldSizeY/2) {
				//randY = (int)(Math.random() * 100);
				//randY = ((200-randY)/400)* worldSizeY;
				natFlow = "North";
				curFlow = "North";
			}
			else {
				//randY = (int)(Math.random() * 100);
				//randY = ((200+randY)/400) * worldSizeY;
				natFlow = "South";
				curFlow = "South";
			}
			//Determine length and path
			if(randX + riverWidth +1 > worldSizeX) 
				randX = randX - riverWidth;
			if(randX - riverWidth - 1 < 0)
				randX = randX + riverWidth;
			if(randY + riverWidth +1 > worldSizeY) 
				randY = randY - riverWidth;
			if(randY - riverWidth - 1 < 0)
				randY = randY + riverWidth;
			flowSwitch = 0;
			flowStop = 0;
			while(flowStop == 0) {
				//Clearance check
				if(curFlow.equals("North") || curFlow.equals("South")) {
					for(clearanceCheck = 0; clearanceCheck < riverWidth; clearanceCheck++) {
						if(randX + clearanceCheck < worldSizeX && randX > -1 && randY < worldSizeY && randY > -1) {
								if(World[randX + clearanceCheck][randY].GetIdentity().equals("Mountain")) {
									flowStop = 1;
									break;
								}
							World[randX + clearanceCheck][randY] = new Water();
							World[randX + clearanceCheck][randY].SetGridLocation(randX + clearanceCheck, randY);
						}
					}
				}else {
					for(clearanceCheck = 0; clearanceCheck < riverWidth; clearanceCheck++) {
						if(randX < worldSizeX - 1 && randX > -1 && randY + clearanceCheck  < worldSizeY -1 && randY > -1) {
						if(World[randX + clearanceCheck][randY] != null) {
							if(World[randX][randY + clearanceCheck].GetIdentity().equals("Mountain")) {
								flowStop = 1;
								break;
							}
						}
						World[randX][randY + clearanceCheck] = new Water();
						World[randX][randY + clearanceCheck].SetGridLocation(randX, randY + clearanceCheck);
						}
					}
				}
				riverCount = riverCount + riverWidth;
				if(curFlow.equals("North"))
					if(randY + 1 >= worldSizeY || randX + riverWidth >= worldSizeX)
						flowStop = 1;
					randY++;
				if(curFlow.equals("South"))
					if(randY - 1 <= -1 || randX + riverWidth >= worldSizeX)
						flowStop = 1;
					randY--;
				if(curFlow.equals("East"))
					if(randX + 1 >= worldSizeX || randY + riverWidth >= worldSizeY)
						flowStop = 1;
					randX++;
				if(curFlow.equals("West"))
					if(randX - 1 <= -1 || randY + riverWidth >= worldSizeY)
						flowStop = 1;
					randY--;
				if(flowStop == 1)
					break;
				flowSwitch++;
				if(flowSwitch == riverWidth) {
					flowRoll = (int)(Math.random() * 100);
					//THIS VALUE DETERMINES LENGTH
					if(flowRoll > 95) {
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
						if(randX+i < worldSizeX && randX+i > 0 && randY + j < worldSizeY && randY + j > 0) {
							if(World[randX+i][randY+j] == null) {
								riverCount++;
								World[randX+i][randY+j] = new Water();
								World[randX+i][randY+j].SetGridLocation(randX+i, randY+j);
							}
						}
						if(randX+i < worldSizeX && randX+i > 0 && randY - j > 0 && randY - j < worldSizeY ) {
							if(World[randX+i][randY-j] == null) {
								riverCount++;
								World[randX+i][randY-j] = new Water();
								World[randX+i][randY-j].SetGridLocation(randX+i, randY-j);
							}
						}
						if(randX-i > 0 && randX-i < worldSizeX && randY + j < worldSizeY && randY + j > 0) {
							if(World[randX-i][randY+j] == null) {
								riverCount++;
								World[randX-i][randY+j] = new Water();
								World[randX-i][randY+j].SetGridLocation(randX-i, randY+j);
							}
						}
						if(randX-i > 0 && randY - j > 0 && randY - j < worldSizeY ) {
							if(World[randX-i][randY-j] == null) {
								riverCount++;
								World[randX-i][randY-j] = new Water();
								World[randX-i][randY-j].SetGridLocation(randX-i, randY-j);
							}
						}
					}
				}
			}
			riverRoll = (int)((1 - Math.pow((worldArea-riverCount)/worldArea,1))*100);
		 //River generation done
		//Fill remains with soil
		for(int i = 0; i < worldSizeX; i++) {
			for(int j = 0; j < worldSizeY; j++) {
				if(World[i][j].GetIdentity().equals("Soil")) {
					if(i+1 < worldSizeX && World[i+1][j] != null && World[i+1][j].GetIdentity().equals("Water")) {
						World[i][j] = new Grass();
						World[i][j].SetGridLocation(i, j);
					} else if(i-1 > 0 && World[i-1][j] != null && World[i-1][j].GetIdentity().equals("Water")) {
						World[i][j] = new Grass();
						World[i][j].SetGridLocation(i, j);
					}else if(j+1 < worldSizeY && World[i][j+1] != null && World[i][j+1].GetIdentity().equals("Water")) {
						World[i][j] = new Grass();
						World[i][j].SetGridLocation(i, j);
					}else if(j-1 > 0 && World[i][j-1] != null && World[i][j-1].GetIdentity().equals("Water")) {
						World[i][j] = new Grass();
						World[i][j].SetGridLocation(i, j);
					}
				}
			}
		}
		initialized = true;
		PlacementWorld = World;
	}
	
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
		tileChar = Character.toUpperCase(tileChar);
		switch(tileChar) {
		case 'R':
		case 'B':
		case 'Y':
		case 'P':
		case 'O':
		case 'G':
		case 'W':
			return new Flower (x,y,tileChar,1);
		}

	    if(tileChar == 'S')
			return new ShrubSapling(x,y);
	    if(tileChar == 'M')
			return new Rock(x,y);
	    if (tileChar == 'T')
			return new PineSapling(x,y);
	    if (tileChar == 'V')
			return new Vine(x,y);
		else
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
				if(PlacementWorld[tile.gridX][tile.gridY].surfaceEntity != null)
					tile = PlacementWorld[tile.gridX][tile.gridY];
				else
					tile.AdvanceClock();
			}
		}	
		PlacementWorld = World;
		
		//swap the current game world with the future world
		World = NextWorldState.newWorld;
	}
}
