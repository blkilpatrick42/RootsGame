package com.mygdx.rootsgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.rootsgame.world.*;
import com.mygdx.rootsgame.entities.*;
import com.mygdx.rootsgame.util.DiceRoller;

public class RootsGame extends ApplicationAdapter {
	SpriteBatch batch;
    OrthographicCamera camera;
	
	public static GameWorld Game;
	public static Cursor cursor;
	
	//time management variables
	public static boolean timePaused = true;
	
	//VIEWPORT AND CAMERA HANDLING
		public static int VIRTUAL_WIDTH;
		public static int VIRTUAL_HEIGHT;		
		public static float ASPECT_RATIO;
		public Rectangle viewport;
	
	public RootsGame(int xSize, int ySize) {
		Game = new GameWorld(xSize, ySize);	
		VIRTUAL_WIDTH = xSize * 16;
		VIRTUAL_HEIGHT = ySize * 16;
		ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
	}
    
    private void ToggleTimePaused() {
    	if(timePaused)
			timePaused = false;
		else
			timePaused = true;
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		
		Game.initialize();
		cursor = new Cursor(20, 15);
	}

	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		// set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 
		batch.begin();		
		DrawGameWorld(Game);
		DrawCursor();
		batch.end();
		
		//if time is unpaused, advance it at given speed
		if(!timePaused) {
			Game.AdvanceClock();		
		}
		
		//enter toggles game pause
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
			ToggleTimePaused();
		
		//if the game is paused, space advances the clock by one
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)&& timePaused)
			Game.AdvanceClock();	
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && cursor.locY+1 < Game.worldSizeY) {
			cursor.locY = cursor.locY + 1;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && cursor.locY-1 >= 0) {
			cursor.locY = cursor.locY - 1;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && cursor.locX-1 >= 0) {
			cursor.locX = cursor.locX - 1;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && cursor.locX+1 < Game.worldSizeX) {
			cursor.locX = cursor.locX + 1;
		}
	}
	
	public void DrawGameWorld(GameWorld gameWorld) {
		for(Tile[] tiles: gameWorld.World) {
			for(Tile tile: tiles) {			
				//draw ground tile
				tile.aspect.setPos(tile.gridX, tile.gridY, 16);
				tile.aspect.localSprite.draw(batch);
							
				//draw entity on top of the ground tile
				if(tile.surfaceEntity != null) {
					tile.surfaceEntity.aspect.setPos(tile.gridX, tile.gridY, 16);
					tile.surfaceEntity.aspect.localSprite.draw(batch);				
				}
			}
		}		
	}
	
	public void DrawCursor() {
		cursor.aspect.setPos(cursor.locX, cursor.locY, 16);
		cursor.aspect.localSprite.draw(batch);	
	}
	
	@Override
    public void resize(int width, int height)
    {
        // calculate new viewport
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f); 
        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
        }

        float w = (float)VIRTUAL_WIDTH*scale;
        float h = (float)VIRTUAL_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }

	
	@Override
	public void dispose () {
		batch.dispose();
		Reader.SpriteSheet.dispose();
	}
}
