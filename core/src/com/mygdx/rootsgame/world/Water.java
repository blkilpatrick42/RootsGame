package com.mygdx.rootsgame.world;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.VisualAspect;

public class Water extends Tile {
	public static String identity = "Water";
	private int frame = 0;
	public Water() {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),2,0,16);
	}
	
	public void ExecuteRules() {
		if(age % 2 == 0) {
			frame = frame + 1;
			if(frame > 3)
				frame = 0;	
		
			aspect = new VisualAspect(Reader.GetSpriteSheet(),2+frame,2,16);
		}
	}
	
	public String GetIdentity() {
		return this.identity;
	}
}
