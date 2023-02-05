package com.mygdx.rootsgame;

import com.mygdx.rootsgame.world.*;
import java.util.ArrayList;

public abstract class WorldObject {
	public int age = 0;
	public static String identity = "nullWorldObject";
	
	public int gridX; //grid x location in the game world
	public int gridY; //grid y location in the game world
	
	public enum TileDir{
		north,
		northEast,
		east,
		southEast,
		south,
		southWest,
		west,
		northWest
	}
	
	public String GetIdentity() {
		return WorldObject.identity;
	}
	
	//override this function to create rules on entities that inherit from this class
	public void ExecuteRules() {
		return;
	}
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
		
	//sets the grid location of this object (note: does not change location of the thing, merely lets
	//the object know where it is)
	public void SetGridLocation(int x, int y) {
		gridX = x;
		gridY = y;
	}
	
	//See below
	public Tile GetAdjacentTile(TileDir dir) {
		return GetAdjacentTile(dir, 1);
	}
	
	//Determines if the adjacent tile at the provided distance in provided direction is valid, returns the tile if so and null if not.
	public Tile GetAdjacentTile(TileDir dir, int distance) {
		if(dir == TileDir.north) {
			int newGridY = gridY + distance;
			if(newGridY < RootsGame.Game.worldSizeY) {
				return RootsGame.Game.World[gridX][newGridY];
			}
		}
		else if(dir == TileDir.northEast) {
			int newGridX = gridX + distance;
			int newGridY = gridY + distance;
			if(newGridY < RootsGame.Game.worldSizeY && newGridX < RootsGame.Game.worldSizeX) {
				return RootsGame.Game.World[newGridX][newGridY];
			}
		}
		else if(dir == TileDir.east) {
			int newGridX = gridX + distance;
			if(newGridX < RootsGame.Game.worldSizeX) {
				return RootsGame.Game.World[newGridX][gridY];
			}
		}
		else if(dir == TileDir.southEast) {
			int newGridX = gridX + distance;
			int newGridY = gridY - distance;
			if(newGridY >= 0 && newGridX < RootsGame.Game.worldSizeX) {
				return RootsGame.Game.World[newGridX][newGridY];
			}
		}
		else if(dir == TileDir.south) {
			int newGridY = gridY - distance;
			if(newGridY >= 0) {
				return RootsGame.Game.World[gridX][newGridY];
			}
		}
		else if(dir == TileDir.southWest) {
			int newGridX = gridX - distance;
			int newGridY = gridY - distance;
			if(newGridY >= 0 && newGridX >= 0) {
				return RootsGame.Game.World[newGridX][newGridY];
			}
		}
		else if(dir == TileDir.west) {
			int newGridX = gridX - distance;
			if(newGridX >= 0) {
				return RootsGame.Game.World[newGridX][gridY];
			}
		}
		else if(dir == TileDir.northWest) {
			int newGridX = gridX - distance;
			int newGridY = gridY + distance;
			if(newGridY < RootsGame.Game.worldSizeY && newGridX >= 0) {
				return RootsGame.Game.World[newGridX][newGridY];
			}
		}

		return null;
	}
	
	//Creates ArrayList of adjacent tiles going clockwise from north.
	public ArrayList<Tile> GetAdjacentTiles(int distance) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		tiles.add(GetAdjacentTile(TileDir.north,distance));
		tiles.add(GetAdjacentTile(TileDir.northEast,distance));
		tiles.add(GetAdjacentTile(TileDir.east,distance));
		tiles.add(GetAdjacentTile(TileDir.southEast,distance));
		tiles.add(GetAdjacentTile(TileDir.south,distance));
		tiles.add(GetAdjacentTile(TileDir.southWest,distance));
		tiles.add(GetAdjacentTile(TileDir.west,distance));
		tiles.add(GetAdjacentTile(TileDir.northWest,distance));
		return tiles;
	}
	
	//returns number of adjacent tiles with the given entity identity as surfaceEntity
	public int AdjacentTilesHaveEntity(String identity) {
		int num = 0;
		ArrayList<Tile> tiles = GetAdjacentTiles(1);
		for(Tile tile: tiles) {
			if(tile != null && tile.surfaceEntity != null &&
			   tile.surfaceEntity.GetIdentity().contains(identity))
				num++;
		}
		return num;
	}
	
	//returns number of adjacent tiles with the given identity
		public int AdjacentTilesHaveIdentity(String identity) {
			int num = 0;
			ArrayList<Tile> tiles = GetAdjacentTiles(1);
			for(Tile tile: tiles) {
				if(tile != null && tile.GetIdentity().contains(identity))
					num++;
			}
			return num;
		}
}
