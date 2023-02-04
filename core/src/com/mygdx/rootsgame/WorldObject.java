package com.mygdx.rootsgame;

public abstract class WorldObject {
	public int age = 0;
	public static String identity = "nullWorldObject";
	
	public int gridX; //grid x location in the game world
	public int gridY; //grid y location in the game world
	
	public String GetIdentity() {
		return identity;
	}
	
	//override this function to create rules on entities that inherit from this class
	public void ExecuteRules(GameWorld inputWorld) {
		return;
	}
		
	//sets the grid location of this object (note: does not change location of the thing, merely lets
	//the object know where it is)
	public void SetGridLocation(int x, int y) {
		gridX = x;
		gridY = y;
	}
}
