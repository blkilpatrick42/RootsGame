package com.mygdx.rootsgame;
import com.mygdx.rootsgame.util.DiceRollerPF;
import com.mygdx.rootsgame.world.Mountain;
import com.mygdx.rootsgame.world.Tile;

public class MountainGrid {
	Tile[][] map;
	Tile startMountain;
	int mapArea;
	int mountainCount;
	int mountainFrequencyCoeff;
	int mapXDimension;
	int mapYDimension;
	
	public MountainGrid(Tile[][] provMap, Tile provStart, int provN, int provM, int provO, int provX, int provY) {
		map = provMap;
		startMountain = provStart;
		mapArea = provN;
		mountainCount = provM;
		mountainFrequencyCoeff = provO;
		mapXDimension = provX;
		mapYDimension = provY;
	}
	
	public MountainGrid Mountainize(int gridX, int gridY) {
		int holdX = gridX;
		int holdY = gridY;
		this.startMountain = new Mountain();
		this.startMountain.SetGridLocation(holdX, holdY);
		this.mountainCount++;
		MountainGrid copy = new MountainGrid(this.map, this.startMountain, this.mapArea, this.mountainCount, this.mountainFrequencyCoeff, this.mapXDimension, this.mapYDimension);
		
		//Mountainize N
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY < copy.mapYDimension) {
				if(!copy.map[holdX][holdY + 1].GetIdentity().equals("Mountain")) {
					copy.startMountain = copy.map[holdX][holdY + 1];
					copy = copy.Mountainize(holdX, holdY+1);
				}
			}	
		}
		
		//Mountainize NE
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY < copy.mapYDimension && holdX < copy.mapXDimension) {
				if(!copy.map[holdX+1][holdY + 1].GetIdentity().equals("Mountain")) {
					copy.startMountain = copy.map[holdX + 1][holdY + 1];
					copy = copy.Mountainize(holdX+1,holdY+1);
				}
			}	
		}
		//Mountainize E
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdX < copy.mapXDimension) {
				if(!copy.map[holdX+1][holdY].GetIdentity().equals("Mountain")) {
					copy.startMountain = copy.map[holdX + 1][holdY];
					copy = copy.Mountainize(holdX+1,holdY);
				}
			}	
		}
		//Mountainize SE
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY > 0 && holdX < copy.mapXDimension) {
				if(!copy.map[holdX+1][holdY - 1].GetIdentity().equals("Mountain")) {
					copy.startMountain = copy.map[holdX + 1][holdY + 1];
					copy = copy.Mountainize(holdX+1, holdY+1);
				}
			}	
		}
		//Mountainize S
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY > 0) {
				if(!copy.map[holdX][holdY - 1].GetIdentity().equals("Mountain")) {
					copy.startMountain = copy.map[holdX][holdY - 1];
					copy = copy.Mountainize(holdX, holdY-1);
				}
			}	
		}
		//Mountainize SW
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY > 0 && holdX > 0) {
				if(!copy.map[holdX - 1][holdY - 1].GetIdentity().equals("Mountain")) {
					copy.startMountain = copy.map[holdX - 1][holdY - 1];
					copy = copy.Mountainize(holdX-1, holdY-1);
				}
			}	
		}
		//Mountainize W
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdX > 0) {
				if(!copy.map[holdX - 1][holdY].GetIdentity().equals("Mountain")) {
					copy.startMountain = copy.map[holdX - 1][holdY];
					copy = copy.Mountainize(holdX-1, holdY);
				}
			}	
		}
		//Mountainize NW
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY > 0 && holdX < copy.mapXDimension) {
				if(!copy.map[holdX - 1][holdY - 1].GetIdentity().equals("Mountain")) {
					copy.startMountain = copy.map[holdX - 1][holdY + 1];
					copy = copy.Mountainize(holdX-1, holdY+1);
				}
			}	
		}
		
		return copy;
	}
}
