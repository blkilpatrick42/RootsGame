package com.mygdx.rootsgame;
import com.mygdx.rootsgame.util.DiceRollerPF;
import com.mygdx.rootsgame.world.Mountain;
import com.mygdx.rootsgame.world.Tile;

public class MountainGrid {
	Tile[][] map;
	Tile start;
	int n;
	int m;
	int o;
	int x;
	int y;
	
	public MountainGrid(Tile[][] provMap, Tile provStart, int provN, int provM, int provO, int provX, int provY) {
		map = provMap;
		start = provStart;
		n = provN;
		m = provM;
		o = provO;
		x = provX;
		y = provY;
	}
	
	public MountainGrid Mountainize() {
		int holdX = start.gridX;
		int holdY = start.gridY;
		this.start = new Mountain();
		this.start.SetGridLocation(holdX, holdY);
		this.m++;
		MountainGrid copy = this;
		
		//Mountainize N
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(copy.start.gridY < copy.y) {
				if(!copy.map[copy.start.gridX][copy.start.gridY + 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[copy.start.gridX][copy.start.gridY + 1];
					copy = copy.Mountainize();
				}
			}	
		}
		
		//Mountainize NE
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(copy.start.gridY < copy.y && copy.start.gridX < copy.x) {
				if(!copy.map[copy.start.gridX+1][copy.start.gridY + 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[copy.start.gridX + 1][copy.start.gridY + 1];
					copy = copy.Mountainize();
				}
			}	
		}
		//Mountainize E
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(copy.start.gridX < copy.x) {
				if(!copy.map[copy.start.gridX+1][copy.start.gridY].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[copy.start.gridX + 1][copy.start.gridY];
					copy = copy.Mountainize();
				}
			}	
		}
		//Mountainize SE
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(copy.start.gridY > 0 && copy.start.gridX < copy.x) {
				if(!copy.map[copy.start.gridX+1][copy.start.gridY - 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[copy.start.gridX + 1][copy.start.gridY + 1];
					copy = copy.Mountainize();
				}
			}	
		}
		//Mountainize S
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(copy.start.gridY > 0) {
				if(!copy.map[copy.start.gridX][copy.start.gridY - 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[copy.start.gridX][copy.start.gridY - 1];
					copy = copy.Mountainize();
				}
			}	
		}
		//Mountainize SW
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(copy.start.gridY > 0 && copy.start.gridX > 0) {
				if(!copy.map[copy.start.gridX - 1][copy.start.gridY - 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[copy.start.gridX - 1][copy.start.gridY - 1];
					copy = copy.Mountainize();
				}
			}	
		}
		//Mountainize W
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(copy.start.gridX > 0) {
				if(!copy.map[copy.start.gridX - 1][copy.start.gridY].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[copy.start.gridX - 1][copy.start.gridY];
					copy = copy.Mountainize();
				}
			}	
		}
		//Mountainize NW
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(copy.start.gridY > 0 && copy.start.gridX < copy.x) {
				if(!copy.map[copy.start.gridX - 1][copy.start.gridY - 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[copy.start.gridX - 1][copy.start.gridY + 1];
					copy = copy.Mountainize();
				}
			}	
		}
		
		return copy;
	}
}
