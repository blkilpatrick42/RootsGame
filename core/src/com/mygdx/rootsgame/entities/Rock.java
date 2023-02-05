package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.VisualAspect;

public class Rock extends Flower{
	public static String identity = "Rock";
	
	public Rock(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),12,0,16);
		SetGridLocation(x,y);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
}
