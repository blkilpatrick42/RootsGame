package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.VisualAspect;

public class YellowFlower extends Flower{
	public static String identity = "YellowFlower";
	
	public YellowFlower(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),6,0,16);
		SetGridLocation(x,y);
	}
}
