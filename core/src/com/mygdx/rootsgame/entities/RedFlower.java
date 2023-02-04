package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.VisualAspect;

public class RedFlower extends Flower{
	public static String identity = "RedFlower";
	
	public RedFlower(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),4,0,16);
		SetGridLocation(x,y);
	}
}
