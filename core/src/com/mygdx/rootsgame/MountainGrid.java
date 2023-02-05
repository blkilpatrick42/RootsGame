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
		this.map[holdX][holdY] = startMountain;
		this.mountainCount++;
		
		//Mountainize N
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY < this.mapYDimension) {
				if(!this.map[holdX][holdY + 1].GetIdentity().equals("Mountain")) {
					this.startMountain = this.map[holdX][holdY + 1];
					this.Mountainize(holdX, holdY+1);
				}
			}	
		}
		
		//Mountainize NE
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY < this.mapYDimension && holdX < this.mapXDimension) {
				if(!this.map[holdX+1][holdY + 1].GetIdentity().equals("Mountain")) {
					this.startMountain = this.map[holdX + 1][holdY + 1];
					this.Mountainize(holdX+1,holdY+1);
				}
			}	
		}
		//Mountainize E
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdX < this.mapXDimension) {
				if(!this.map[holdX+1][holdY].GetIdentity().equals("Mountain")) {
					this.startMountain = this.map[holdX + 1][holdY];
					this.Mountainize(holdX+1,holdY);
				}
			}	
		}
		//Mountainize SE
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY > 0 && holdX < this.mapXDimension) {
				if(!this.map[holdX+1][holdY - 1].GetIdentity().equals("Mountain")) {
					this.startMountain = this.map[holdX + 1][holdY + 1];
					this.Mountainize(holdX+1, holdY+1);
				}
			}	
		}
		//Mountainize S
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY > 0) {
				if(!this.map[holdX][holdY - 1].GetIdentity().equals("Mountain")) {
					this.startMountain = this.map[holdX][holdY - 1];
					this.Mountainize(holdX, holdY-1);
				}
			}	
		}
		//Mountainize SW
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY > 0 && holdX > 0) {
				if(!this.map[holdX - 1][holdY - 1].GetIdentity().equals("Mountain")) {
					this.startMountain = this.map[holdX - 1][holdY - 1];
					this.Mountainize(holdX-1, holdY-1);
				}
			}	
		}
		//Mountainize W
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdX > 0) {
				if(!this.map[holdX - 1][holdY].GetIdentity().equals("Mountain")) {
					this.startMountain = this.map[holdX - 1][holdY];
					this.Mountainize(holdX-1, holdY);
				}
			}	
		}
		//Mountainize NW
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.mapArea-this.mountainCount)/this.mapArea, this.mountainFrequencyCoeff))*100))){
			if(holdY > 0 && holdX < this.mapXDimension) {
				if(!this.map[holdX - 1][holdY - 1].GetIdentity().equals("Mountain")) {
					this.startMountain = this.map[holdX - 1][holdY + 1];
					this.Mountainize(holdX-1, holdY+1);
				}
			}	
		}
		return this;
	}
}
