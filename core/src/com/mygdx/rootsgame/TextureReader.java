package com.mygdx.rootsgame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

public class TextureReader {
	
	static String sheetName = "roots_devtiles.png";
    private static File locFile;
	
	//Method for generating a texture from a given String
    public static Texture getTexture(String inName){
        Texture tempTex = new Texture(inName);
        return tempTex;
    }
    
    public static Texture GetSpriteSheet() {
    	return getTexture(sheetName);
    }
    
   	public static String readStringFromFile(String fileName){
           try {
               locFile = new File(fileName);
               StringBuilder sb = new StringBuilder();
               Scanner Prelim = new Scanner(locFile);
               Prelim.useDelimiter("\n");
               while (Prelim.hasNext()) {
                   sb.append(Prelim.next());
               }
               Prelim.close();
               return sb.toString();
    
           } catch (FileNotFoundException e) {
               System.out.print(e);
           }
           return null;
       }
       
       //writes a String to a file
       public static void saveToFile(String s, String name) {
           try {
               PrintWriter writer = new PrintWriter(name);
               Scanner scan = new Scanner(s);
               scan.useDelimiter("\n");
               while (scan.hasNext()) {
                   writer.println(scan.next());
               }
               writer.close();
               scan.close();

           } catch (FileNotFoundException e) {
               System.out.print(e);
           }
           
       }
}
