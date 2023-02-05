package com.mygdx.rootsgame.world;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.rootsgame.*;
import com.mygdx.rootsgame.WorldObject.TileDir;
import com.mygdx.rootsgame.entities.*;
import com.mygdx.rootsgame.util.DiceRoller;

public abstract class Tile extends WorldObject{
	public VisualAspect aspect;
	public Entity surfaceEntity;
	public static String identity = "nullWorldObject";	
	
	public void AdvanceClock(){	
		ExecuteRules();
		if(surfaceEntity != null) {
			surfaceEntity.AdvanceClock();
		}
		age++;
	}
	
	//Sets the entity located on the surface of this tile
	public void SetSurfaceEntity(Entity entity) {
		surfaceEntity = entity;
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
						case 'R':
							neighborColors[0]++;
							break;
						case 'B':
							neighborColors[1]++;
							break;
						case 'Y':
							neighborColors[2]++;
							break;
						case 'P':
							neighborColors[3]++;
							break;
						case 'O':
							neighborColors[4]++;
							break;
						case 'G':
							neighborColors[5]++;
							break;
						case 'W':
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
		int numWaters = AdjacentTilesHaveIdentity(Water.identity, 3);
		int flowerGrowthPrecedence = 1;
		
		//count how many adjacent tiles of each color
		int[] neighborColors = CountNeighborColors();
		List<String> validSeedList = new ArrayList<>();
		
		//If 1+ adjacent water
		if(numWaters >= 1) {
			//Otherwise, if 2+ adjacent flowers, try to plant sprout
			if(numFlowers >= 2) {
				//Automatic white seed if 1+ Purple, Orange, Green seed
				if(neighborColors[0]>=1 && neighborColors[1]>=1 && neighborColors[2]>=1) {
					validSeedList.add("w");
				}
				//If no new white, 50% chance to add new seed (100% for testing)
				else if(DiceRoller.RollDice(1)){
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

				//choose random seed from valid options
				if(validSeedList.size()>0) {
					int seedIndex = DiceRoller.pickInt(validSeedList.size());
					String newSeedColorString = validSeedList.get(seedIndex-1);
					char newSeedColor = Character.toUpperCase(newSeedColorString.charAt(0));
				
					Flower newFlower  = new Flower(gridX, gridY, newSeedColor, 0);
					RootsGame.Game.NextWorldState.SubmitFutureEntity(flowerGrowthPrecedence, newFlower, gridX, gridY);
				}
			}
		}
	}
}
