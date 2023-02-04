package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.*;
import com.mygdx.rootsgame.entities.*;

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
}
