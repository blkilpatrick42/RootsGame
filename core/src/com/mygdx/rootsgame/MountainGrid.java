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
	
	public MountainGrid Mountainize(int gridX, int gridY) {
		int holdX = gridX;
		int holdY = gridY;
		this.start = new Mountain();
		this.start.SetGridLocation(holdX, holdY);
		this.m++;
		MountainGrid copy = this;
		
		//Mountainize N
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(holdY < copy.y) {
				if(!copy.map[holdX][holdY + 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[holdX][holdY + 1];
					copy = copy.Mountainize(holdX, holdY+1);
				}
			}	
		}
		
		//Mountainize NE
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(holdY < copy.y && holdX < copy.x) {
				if(!copy.map[holdX+1][holdY + 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[holdX + 1][holdY + 1];
					copy = copy.Mountainize(holdX+1,holdY+1);
				}
			}	
		}
		//Mountainize E
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(holdX < copy.x) {
				if(!copy.map[holdX+1][holdY].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[holdX + 1][holdY];
					copy = copy.Mountainize(holdX+1,holdY);
				}
			}	
		}
		//Mountainize SE
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(holdY > 0 && holdX < copy.x) {
				if(!copy.map[holdX+1][holdY - 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[holdX + 1][holdY + 1];
					copy = copy.Mountainize(holdX+1, holdY+1);
				}
			}	
		}
		//Mountainize S
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(holdY > 0) {
				if(!copy.map[holdX][holdY - 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[holdX][holdY - 1];
					copy = copy.Mountainize(holdX, holdY-1);
				}
			}	
		}
		//Mountainize SW
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(holdY > 0 && holdX > 0) {
				if(!copy.map[holdX - 1][holdY - 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[holdX - 1][holdY - 1];
					copy = copy.Mountainize(holdX-1, holdY-1);
				}
			}	
		}
		//Mountainize W
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(holdX > 0) {
				if(!copy.map[holdX - 1][holdY].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[holdX - 1][holdY];
					copy = copy.Mountainize(holdX-1, holdY);
				}
			}	
		}
		//Mountainize NW
		if(DiceRollerPF.RollDice(100, (int)((1-Math.pow((this.n-this.m)/this.n, this.o))*100))){
			if(holdY > 0 && holdX < copy.x) {
				if(!copy.map[holdX - 1][holdY - 1].GetIdentity().equals("Mountain")) {
					copy.start = copy.map[holdX - 1][holdY + 1];
					copy = copy.Mountainize(holdX-1, holdY+1);
				}
			}	
		}
		
		return copy;
	}
}
