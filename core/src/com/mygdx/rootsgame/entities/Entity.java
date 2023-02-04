package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.*;

public abstract class Entity extends WorldObject{
	public VisualAspect aspect;
	
	public static String identity = "nullEntity";
	
	public void AdvanceClock(){
		ExecuteRules();
		age++;
	}
}
