package com.me.thesalmonfactory;

//import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.me.thesalmonfactory.helpers.GameContext;

public class LevelParser {
	
	public static final int matrixWidth = 13, matrixHeight = 8;
	public int[][][] mapMatrix; 
	
	public static Vector2 ROBOT_ROT, ROBOT_SPAWN, 
		SALMON_ROT, SALMON_SPAWN;
	
	public static List<Vector2> WATER_LIST = null;
	
	public void displayMatrix(){
		for(int i = 0 ; i < matrixHeight ; i++)
		{
			String temp = new String("");
			for(int j = 0 ; j < matrixWidth ; j++)
			{
				temp += Integer.toString(mapMatrix[j][i][0]) + " ";
			}
			System.out.println(temp);
		}
		
		for(int i = 0 ; i < matrixHeight ; i++)
		{
			String temp = new String("");
			for(int j = 0 ; j < matrixWidth ; j++)
			{
				temp += Integer.toString(mapMatrix[j][i][1]) + " ";
			}
			System.out.println(temp);
		}
	}
	
	@SuppressWarnings("unused")
	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}

	public LevelParser(int levelID)
	{
		mapMatrix = new int[matrixWidth][matrixHeight][2]; 
		
		if(ROBOT_ROT == null) {
			ROBOT_ROT = new Vector2(0,0);
			ROBOT_SPAWN = new Vector2(0,0);
			SALMON_ROT = new Vector2(0,0);
			SALMON_SPAWN = new Vector2(0,0);
		}
		
		if(WATER_LIST == null) {
			WATER_LIST = new ArrayList<Vector2>();
		}

		FileHandle levelFile = Gdx.files.internal("levels/level" + levelID + ".xml");
		XmlReader xml = new XmlReader();
		XmlReader.Element xml_element = null;
		try {
			xml_element = xml.parse(levelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(xml_element != null) {
			Array<com.badlogic.gdx.utils.XmlReader.Element> elements = xml_element.getChildrenByName("layer");
			
			int nodesLength = elements.size;
			
			for (int layerCount = 0; layerCount < nodesLength; layerCount++) {
				com.badlogic.gdx.utils.XmlReader.Element currentNode = elements.get(layerCount);
				Array<com.badlogic.gdx.utils.XmlReader.Element> tiles = currentNode.getChild(0).getChildrenByName("tile");
				
				int tileCountX = 0, tileCountY = 0;
				
				int tileSize = tiles.size;
				for (int i = 0; i < tileSize; i++) {
					com.badlogic.gdx.utils.XmlReader.Element currentTile = tiles.get(i);
					
					int tempInt = 0;
					tempInt = Integer.parseInt(currentTile.getAttribute("gid")) - 1;
					AddSpecialTiles(tileCountX, tileCountY, tempInt);
					mapMatrix[tileCountX][tileCountY][layerCount] = tempInt;
					
					if (tileCountX < matrixWidth - 1)
						tileCountX++;
					else
					{
		   				tileCountX = 0;
		   				tileCountY++;
		   			}
					if(tileCountY == 8)
						break;
					      	
		  		}
		    }
		}
	}
	
	private void AddSpecialTiles(int x, int y, int id) {
		switch(id) {
			//Salmon Spawn
			case 5: 
				SALMON_SPAWN = GameContext.GetPosition(x, y);
				break;
			//Robot Rotation
			case 13: 
				ROBOT_ROT = GameContext.GetPosition(x, y);
				break;
			//Salmon Spawn
			case 37: 
				SALMON_ROT = GameContext.GetPosition(x, y);
				break;
			//Robot  Spawn
			case 45: 
				ROBOT_SPAWN = GameContext.GetPosition(x, y);
				break;
		}
		if(id / 32 < 28 && (id % 32 == 5 || id % 32 == 6 || id % 32 == 7)) {
			Vector2 pos = GameContext.GetPosition(x, y);
			pos.x += GameContext.m_OffsetX;
			pos.y = Gdx.app.getGraphics().getHeight() - pos.y - GameContext.TILE_WIDTH * 1.55f;
			WATER_LIST.add(pos);
		}
	}
	
	public void Dispose() {
		WATER_LIST.clear();
	}
}