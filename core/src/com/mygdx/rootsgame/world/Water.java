package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.VisualAspect;

public class Water extends Tile {
	public Water() {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),3,0,16);
	}
}
