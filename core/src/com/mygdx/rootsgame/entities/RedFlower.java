package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.VisualAspect;

public class RedFlower extends Flower{
	public static String identity = "Red"+Flower.identity;
	
	public RedFlower(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),3,0,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
}
