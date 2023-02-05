package com.mygdx.rootsgame.entities;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.Soil;
import com.mygdx.rootsgame.world.Tile;
import com.mygdx.rootsgame.world.Water;

public class Flower extends Entity{
	public static String identity = "Flower";
	
	//State where 0 = sprout, 1-3 = blooming, 4 = decay
	public int FlowerState;
	
	//Color where R = red, B = blue, Y = Yellow, etc
	public char FlowerColor;
	
	public Flower(int x, int y, char color, int state) {
		SetGridLocation(x,y);
		setColor(color);
		setState(state);
		
		//Determine sprite, sus by default
		int spriteLoc = 13;
		if(state == 0) {
			spriteLoc = 10;
		}
		else if(state == 4) {
			spriteLoc = 11;
		}
		else {
			switch(color){
				case 'R':
					spriteLoc = 3;
				case 'Y':
					spriteLoc = 5;
				case 'B':
					spriteLoc = 4;
				case 'G':
					spriteLoc = 7;
				case 'P':
					spriteLoc = 9;
				case 'O':
					spriteLoc = 8;
				case 'W':
					spriteLoc = 6;
				}
		}
		
		aspect = new VisualAspect(Reader.GetSpriteSheet(),spriteLoc,0,16);
	}
	
	
	public String GetIdentity() {
		return this.identity;
	}	
	
	public char GetColor() {
		return this.FlowerColor;
	}	
	
	public String GetColorString() {
		String colorString = "";
		switch(FlowerColor) {
			case 'R':
				colorString = "Red";
			case 'Y':
				colorString = "Yellow";
			case 'B':
				colorString = "Blue";
			case 'G':
				colorString = "Green";
			case 'P':
				colorString = "Purple";
			case 'O':
				colorString = "Orange";
			case 'W':
				colorString = "White";
		}
		return colorString;
	}	
	
	public int GetState() {
		return this.FlowerState;
	}		
	
	public String GetStateString() {
		String stateString = "";
		switch(FlowerState) {
			case 0:
				stateString = "Sprout";
			case 1:
				stateString = "Bloom1";
			case 2:
				stateString = "Bloom2";
			case 3:
				stateString = "Bloom3";
			case 4:
				stateString = "Decay";
		}
		return stateString;
	}
	
	public void setColor(char newColor) {
		this.FlowerColor = newColor;
	}
	
	public void setState(int newState) {
		this.FlowerState = newState;
	}
	
	public int[] CountNeighborColors() {
		//init an array with one value per flower color
		int[] neighborColors = new int[7];
		
		//made up OO bullshit
		ArrayList<Entity> entities = new ArrayList<Entity>(8);
		ArrayList<Tile> tiles = GetAdjacentTiles(1);
		
		for (Tile t : tiles) {
			if(t != null) {
				entities.add(t.surfaceEntity);
			}
		}

		for(Entity e : entities){
			if(e != null && e.GetIdentity().contains(Flower.identity)) {
				Flower f = (Flower)e;
				//If flower is in bloom
				if(f.FlowerState > 0 && f.FlowerState < 4) {
					//Add to count for its color
					switch(f.FlowerColor) {
						case 'r':
							neighborColors[0]++;
							break;
						case 'b':
							neighborColors[1]++;
							break;
						case 'y':
							neighborColors[2]++;
							break;
						case 'p':
							neighborColors[3]++;
							break;
						case 'o':
							neighborColors[4]++;
							break;
						case 'g':
							neighborColors[5]++;
							break;
						case 'w':
							neighborColors[6]++;
							break;
					}
				}
			}
		}
				
		return neighborColors;
	}
	
	public void ExecuteRules() {
		int numFlowers = AdjacentTilesHaveEntity(Flower.identity);
		int numWaters = AdjacentTilesHaveIdentity(Water.identity);
		//int chanceModifier = 9 - numFlowers;
		//int liveChanceModifier = 8 + numWaters*5;
		
		//get adjacent tiles
		Tile adjacentNorth = GetAdjacentTile(TileDir.north);
		Tile adjacentNorthEast = GetAdjacentTile(TileDir.northEast);
		Tile adjacentEast = GetAdjacentTile(TileDir.east);
		Tile adjacentSouthEast = GetAdjacentTile(TileDir.southEast);
		Tile adjacentSouth = GetAdjacentTile(TileDir.south);
		Tile adjacentSouthWest = GetAdjacentTile(TileDir.southWest);
		Tile adjacentWest = GetAdjacentTile(TileDir.west);
		Tile adjacentNorthWest = GetAdjacentTile(TileDir.northWest);
		
		//model flower growth to adjacent tiles
		int flowerGrowthPrecedence = 1;
		
		//count how many adjacent tiles of each color
		int[] neighborColors = CountNeighborColors();
		List<String> validSeedList = new ArrayList<>();
		
		//If 1+ adjacent water
		if(numWaters >= 1) {
			//If current tile is sapling, grow to bloom1
			
			//If current tile is bloom, 33% chance to grow to bloom2, bloom3, or decay
			
			//Otherwise, if 2+ adjacent flowers, try to plant sprout
			if(numFlowers >= 2) {
				//Automatic white seed if 1+ Purple, Orange, Green seed
				if(neighborColors[0]>=1 && neighborColors[1]>=1 && neighborColors[2]>=1) {
					validSeedList.add("w");
				}
				//If no new white, 50% chance to add new seed
				else if(DiceRoller.RollDice(2)){
					//Red seed valid if 2+ red neighbors
					if(neighborColors[0]>=2) {
						validSeedList.add("r");
					}
					//Blue seed valid if 2+ blue neighbors
					if(neighborColors[1]>=2) {
						validSeedList.add("b");						
					}

					//Yellow seed valid if 2+ yellow neighbors
					if(neighborColors[2]>=2) {
						validSeedList.add("y");
					}

					//Purple seed valid if 1+ red and 1+ blue neighbors OR if 2+ purple neighbors
					if((neighborColors[3]>=2)||(neighborColors[0]>=1 && neighborColors[1]>=1)) {
						validSeedList.add("p");
					}

					//Orange seed valid if 1+ red and 1+ yellow neighbors OR 2+ orange neighbors
					if((neighborColors[4]>=2)||(neighborColors[0]>=1 && neighborColors[2]>=1)) {
						validSeedList.add("o");
					}

					//Green seed valid if 1+ blue and 1+ yellow neighbors OR 2+ green neighbors
					if((neighborColors[5]>=2)||(neighborColors[1]>=1 && neighborColors[2]>=1)) {
						validSeedList.add("g");
					}

					//White seed valid if 2+ white neighbors
					if(neighborColors[6]>=2) {
						validSeedList.add("w");
					}
				}
			}
			//choose random seed from valid options
			//rolldice(validSeedList);
			
			//Add selected seed to futureWorld for this tile
		}
		//if no water, and blooming seed exists in square
		//set plant to decay
		
		//if current tile is decay flower, set to disappear next turn
		
		
		if(adjacentNorth != null && adjacentNorth.GetIdentity().contains(Soil.identity)) {
			   if(DiceRoller.RollDice(2)) {
				   Flower newFlowerTile = new Flower (gridX,gridY,'b',0);
				   newFlowerTile.SetGridLocation(gridX, gridY+1);
				   RootsGame.Game.NextWorldState.SubmitFutureEntity(flowerGrowthPrecedence, newFlowerTile, gridX, gridY+1);
			   }
			}
		}
}
