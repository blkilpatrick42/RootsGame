package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.TextureReader;
import com.mygdx.rootsgame.VisualAspect;

public class RedFlower extends Flower{
	public static String identity = "RedFlower";
	
	public RedFlower(int x, int y) {
		aspect = new VisualAspect(TextureReader.GetSpriteSheet(),4,0,16);
		SetGridLocation(x,y);
	}
}
