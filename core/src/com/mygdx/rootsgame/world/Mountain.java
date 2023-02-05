package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.*;

public class Mountain extends Tile {
	public static String identity = "Mountain";
	
	public Mountain() {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),3,2,16);
	}
	
	public String GetIdentity() {
		return Mountain.identity;
	}
}
