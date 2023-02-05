package com.mygdx.rootsgame.entities;

import com.mygdx.rootsgame.Reader;
import com.mygdx.rootsgame.VisualAspect;

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
			case 'Y':
				colorString = "Yellow";
			case 'B':
				colorString = "Blue";
			case 'G':
				colorString = "Green";
			case 'P':
				colorString = "Purple";
			case 'O':
				colorString = "Orange";
			case 'W':
				colorString = "White";
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
			case 1:
				stateString = "Bloom1";
			case 2:
				stateString = "Bloom2";
			case 3:
				stateString = "Bloom3";
			case 4:
				stateString = "Decay";
		}
		return stateString;
	}
	
	public void setColor(char newColor) {
		this.FlowerColor = newColor;
	}
	
	public void setState(int newState) {
		this.FlowerState = newState;
	}
}
