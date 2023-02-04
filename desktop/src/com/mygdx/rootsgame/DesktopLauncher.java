package com.mygdx.rootsgame;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.rootsgame.RootsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("RootsGame");
		
	    int numTilesX = 40;
		int numTilesY = 30;
		int tilePixelSize = 16;	
		
		config.setWindowedMode(numTilesX * tilePixelSize, 
							   numTilesY * tilePixelSize);
		
		new Lwjgl3Application(new RootsGame(numTilesX, numTilesY), config);
	}
}
