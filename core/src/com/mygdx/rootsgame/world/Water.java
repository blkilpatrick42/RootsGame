package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.TextureReader;
import com.mygdx.rootsgame.VisualAspect;

public class Water extends Tile {
	public static String identity = "Water";
	
	public Water() {
		aspect = new VisualAspect(TextureReader.GetSpriteSheet(),3,0,16);
	}
}
