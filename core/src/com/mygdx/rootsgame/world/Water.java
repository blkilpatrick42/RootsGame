package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.VisualAspect;

public class Water extends Tile {
	public static String identity = "Water";
	
	public Water() {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),2,0,16);
	}
	
	public String GetIdentity() {
		return this.identity;
	}
}
