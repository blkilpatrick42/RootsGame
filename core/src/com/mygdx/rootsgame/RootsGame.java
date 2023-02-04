package com.mygdx.rootsgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.rootsgame.world.*;
import com.mygdx.rootsgame.entities.*;
import com.mygdx.rootsgame.util.DiceRoller;

public class RootsGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	public static GameWorld Game;
	
	//time management variables
	public boolean timePaused = true;
	private long diff, start = System.currentTimeMillis(); //gets current system time in Millisecs
	int slowSpeed = 1;
	int mediumSpeed = 2;
	int fastSpeed = 3;
	public enum TimeSpeed{
		slow,
		medium,
		fast
	}
	
	public int GetTimeSpeed(TimeSpeed speed) {
		int retSpeed = 0;
		switch(speed) {
			 case slow: retSpeed = slowSpeed;
	         break;
			 case medium: retSpeed = mediumSpeed;
	         break;
			 case fast:  retSpeed = fastSpeed;
	         break;
		}
		return retSpeed;
	}
	
	public RootsGame(int xSize, int ySize) {
		Game = new GameWorld(xSize, ySize);
		
	}
	
	//Tick function, limits the program to the fps
    public void sleep(int fps) {
        if (fps > 0) {
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000 / fps;
            if (diff < targetDelay) {
                try {
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {
                }
            }
            start = System.currentTimeMillis();
        }
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
		Reader.saveToFile("WSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWGGWGWGSSSGGWGWGGSGWGGSSWSWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGWWSGWSGWGSGWGSWGSGWGSGWGSGWGSWGSGWSGWGSGWSGWGGSGWGSWGSGWGSGWGSGWGSWSGWGSWGWSGWGSWGSWGSGWSGWSGGWSGSWGW", "test.txt");

		Game.initialize();
	}

	@Override
	public void render () {
		batch.begin();
		DrawGameWorld(Game);
		batch.end();
		
		//if time is unpaused, advance it at given speed
		if(!timePaused) {
			Game.AdvanceClock();
			sleep(GetTimeSpeed(TimeSpeed.slow));
		}
		
		//enter toggles game pause
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
			ToggleTimePaused();
		
		//if the game is paused, space advances the clock by one
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)&& timePaused)
			Game.AdvanceClock();		
	}
	
	public void DrawGameWorld(GameWorld gameWorld) {
		for(Tile[] tiles: gameWorld.World) {
			for(Tile tile: tiles) {
				//draw ground tile
				tile.aspect.localSprite.draw(batch);
				tile.aspect.setPos(tile.gridX, tile.gridY, 16);
				
				//draw entity on top of the ground tile
				if(tile.surfaceEntity != null) {
					tile.surfaceEntity.aspect.localSprite.draw(batch);
					tile.surfaceEntity.aspect.setPos(tile.gridX, tile.gridY, 16);
				}
			}
		}
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
