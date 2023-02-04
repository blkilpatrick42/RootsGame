package com.mygdx.rootsgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class VisualAspect {
	public Texture localTexture;
    public Sprite localSprite;

    
    public VisualAspect(){
        localTexture = new Texture("NullTex.png");
        localSprite = new Sprite(localTexture);
        setPos(0,0,1);
    }
    
    //constructor for loading a sprite from a sprite sheet
    //where x and y is the TOP LEFT corner of the sprite, and size
    //is the length of one side of a square with x and y as a corner
     public VisualAspect(Texture inTex, int x, int y, int size){
        localTexture = inTex;
        localSprite = new Sprite(localTexture,x*size,y*size,size,size);
		setPos(0,0,1);
    }
    
    //Returns the local sprite
    public Sprite getSprite(){
        return localSprite;
    }
    
    //Moves the sprite along the X axis
    //xTrans pixels from it's current
    //position, where xTrans is multiplied
    //by align
    public void transformX(int xTrans, int align){
        localSprite.setX(localSprite.getX()+(xTrans*align));
    }
    
    //Moves the sprite along the X axis
    //xTrans pixels from it's current
    //position, where yTrans is multiplied by align
    public void transformY(int yTrans, int align){
        localSprite.setY(localSprite.getY()+(yTrans*align));
    }
    
    //Moves the sprite to the given
    //x/y position on the screen, where
    //x and y are both multiplied by
    //align
    public void setPos(float x, float y, int align){
        localSprite.setX(x*align);
        localSprite.setY(y*align);
    }
    
    //Moves the sprite to the given
    //x/y position on the screen, where
    //x and y are both multiplied by
    //align
    public void setPos(int x, int y, int align){
        localSprite.setX(x*align);
        localSprite.setY(y*align);
    }
    
    //function for updating the aspect
    public void update(){
        return;
    }
    
    public void setSpriteAlpha(float alpha){
    	getSprite().setColor(getSprite().getColor().r, getSprite().getColor().g, getSprite().getColor().b, alpha);
    }
    
}
