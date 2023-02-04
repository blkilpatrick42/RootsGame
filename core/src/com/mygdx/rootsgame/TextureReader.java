package com.mygdx.rootsgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

public class TextureReader {
	
	static String sheetName = "roots_devtiles.png";
	
	//Method for generating a texture from a given String
    public static Texture getTexture(String inName){
        Texture tempTex = new Texture(inName);
        return tempTex;
    }
    
    public static Texture GetSpriteSheet() {
    	return getTexture(sheetName);
    }
}
