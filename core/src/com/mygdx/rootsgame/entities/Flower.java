package com.mygdx.rootsgame.entities;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.RootsGame;
import com.mygdx.rootsgame.VisualAspect;
import com.mygdx.rootsgame.util.DiceRoller;
import com.mygdx.rootsgame.world.Grass;
import com.mygdx.rootsgame.world.Soil;
import com.mygdx.rootsgame.world.Tile;
import com.mygdx.rootsgame.world.Water;

public class Flower extends Entity{
	public static String identity = "Flower";
	
	//State where 0 = sprout, 1-3 = blooming, 4 = decay
	public int FlowerState;
	
	//Color where R = red, B = blue, Y = Yellow, etc
	public char FlowerColor;
	
	public Flower(int x, int y, char color, int state) {
		SetGridLocation(x,y);
		setColor(color);
		setState(state);
		
		//Determine sprite, sus by default
		int spriteLoc = 13;
		if(state == 0) {
			spriteLoc = 10;
		}
		else if(state == 4) {
			spriteLoc = 11;
		}
		else {
			switch(color){
				case 'R':
					spriteLoc = 3;
					break;
				case 'Y':
					spriteLoc = 5;
					break;
				case 'B':
					spriteLoc = 4;
					break;
				case 'G':
					spriteLoc = 7;
					break;
				case 'P':
					spriteLoc = 9;
					break;
				case 'O':
					spriteLoc = 8;
					break;
				case 'W':
					spriteLoc = 6;
					break;
				}
		}
		
		aspect = new VisualAspect(Reader.GetSpriteSheet(),spriteLoc,0,16);
	}
	
	
	public String GetIdentity() {
		return this.identity;
	}	
	
	public char GetColor() {
		return this.FlowerColor;
	}	
	
	public String GetColorString() {
		String colorString = "";
		switch(FlowerColor) {
			case 'R':
				colorString = "Red";
				break;
			case 'Y':
				colorString = "Yellow";
				break;
			case 'B':
				colorString = "Blue";
				break;
			case 'G':
				colorString = "Green";
				break;
			case 'P':
				colorString = "Purple";
				break;
			case 'O':
				colorString = "Orange";
				break;
			case 'W':
				colorString = "White";
				break;
		}
		return colorString;
	}	
	
	public int GetState() {
		return this.FlowerState;
	}		
	
	public String GetStateString() {
		String stateString = "";
		switch(FlowerState) {
			case 0:
				stateString = "Sprout";
				break;
			case 1:
				stateString = "Bloom1";
				break;
			case 2:
				stateString = "Bloom2";
				break;
			case 3:
				stateString = "Bloom3";
				break;
			case 4:
				stateString = "Decay";
				break;
		}
		return stateString;
	}
	
	public void setColor(char newColor) {
		this.FlowerColor = newColor;
	}
	
	public void setState(int newState) {
		this.FlowerState = newState;
	}
	
	public void ExecuteRules() {

		switch(FlowerState) {
			//If sprout, grow to bloom
			case 0:
				RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new Flower(gridX, gridY, this.FlowerColor, 1), gridX, gridY);
				break;
			//If bloom1-3, 33% chance to grow to next state
			case 1:
			case 2:
			case 3:
				if(DiceRoller.RollDice(3)) {
					RootsGame.Game.NextWorldState.SubmitFutureEntity(1, new Flower(gridX, gridY, this.FlowerColor, this.FlowerState + 1), gridX, gridY);
				}
				break;
			//If decaying, disappear
			case 4:
				RootsGame.Game.NextWorldState.SubmitFutureEntity(1, null, gridX, gridY);
				break;
		}
	}
}
