package com.mygdx.rootsgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.rootsgame.world.*;
import com.mygdx.rootsgame.entities.*;

public class RootsGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	public GameWorld Game;
	
	public RootsGame(int xSize, int ySize) {
		Game = new GameWorld(xSize, ySize);
		
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Game.initialize();
		//img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		//ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		DrawGameWorld(Game);
		batch.end();
	}
	
	public void DrawGameWorld(GameWorld gameWorld) {
		for(Tile[] tiles: gameWorld.World) {
			for(Tile tile: tiles) {
				tile.aspect.localSprite.draw(batch);
				tile.aspect.setPos(tile.gridX, tile.gridY, 16);
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
