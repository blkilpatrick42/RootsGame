package com.mygdx.rootsgame;

public class Cursor{
	VisualAspect aspect;
	
	int locX;
	int locY;
	
	public Cursor(int x, int y) {
		aspect = new VisualAspect(Reader.GetSpriteSheet(),0,1,16);
		locX = x;
		locY = y;
	}
}
