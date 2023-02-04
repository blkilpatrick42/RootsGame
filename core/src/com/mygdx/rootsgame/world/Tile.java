package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.*;
import com.mygdx.rootsgame.entities.*;

public abstract class Tile extends WorldObject{
	public VisualAspect aspect;
	public Entity surfaceEntity;
	public static String identity = "nullTile";	
	
	public void AdvanceClock(GameWorld inputWorld){	
		surfaceEntity.AdvanceClock(inputWorld);
		age++;
	}
	
	//Sets the entity located on the surface of this tile
	public void SetSurfaceEntity(Entity entity) {
		surfaceEntity = entity;
	}
}
