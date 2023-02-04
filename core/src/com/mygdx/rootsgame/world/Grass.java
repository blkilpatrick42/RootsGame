package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.*;

public class Grass extends Tile {
	public Grass() {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),1,0,16);
	}
}
