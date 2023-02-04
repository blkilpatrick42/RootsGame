package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.*;

public class Soil extends Tile {
	public static String identity = "Soil";
	
	public Soil() {
		aspect = new VisualAspect(TextureReader.GetSpriteSheet(),2,0,16);
	}
}
