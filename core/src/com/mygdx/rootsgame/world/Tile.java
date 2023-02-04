package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.*;
import com.mygdx.rootsgame.entities.*;

public abstract class Tile {
	public VisualAspect aspect;
	public Entity surfaceEntity;
	
	public int gridX; //grid x location in the game world
	public int gridY; //grid y location in the game world
	
	public void Tick(GameWorld inputWorld){	
		surfaceEntity.tick(inputWorld);
		//TODO: futures n' shit
	}
	
	//Sets the entity located on the surface of this tile
	public void SetSurfaceEntity(Entity entity) {
		surfaceEntity = entity;
	}
	
	public void SetGridLocation(int x, int y) {
		gridX = x;
		gridY =y;
	}
}
