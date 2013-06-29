package com.me.thesalmonfactory;

//import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;

public class LevelParser {
	
	public static final int matrixWidth = 13, matrixHeight = 8;
	public int[][][] mapMatrix; 
	
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
		
		XmlReader xml = new XmlReader();
		
		//In Project: Gdx.files.internal("levels/levels0.xml")
		FileHandle levelFile = Gdx.files.internal("levels/level" + levelID + ".xml");
		String fileContent = levelFile.readString();
		//File file = new File(fileContent);
		
		try {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fileContent);
		doc.getDocumentElement().normalize();


		NodeList layerNodes = doc.getElementsByTagName("layer");
	
		int nodesLength = layerNodes.getLength();
		//System.out.println("Layers found : " + nodesLength);
		
		for (int layerCount = 0; layerCount < nodesLength; layerCount++) {
			Node currentNode = layerNodes.item(layerCount);
			//System.out.println("Layer found : " + currentNode.getNodeName());

			Node tempNode = currentNode.getFirstChild();
			while(tempNode.getNodeName() != "data")
				tempNode = tempNode.getNextSibling();
			
			//System.out.println("Layer child : " + tempNode.getNodeName());
			
			NodeList tileNodes = tempNode.getChildNodes();
			

			int tileCountX = 0, tileCountY = 0;
			//System.out.println("Children : " + tileNodes.getLength());
			
			
			
			for (int i = 0; i < tileNodes.getLength(); i+=2) {
				Node currentTile = tileNodes.item(i);
				
				while(currentTile != null && currentTile.getNodeName() != "tile")
				{
					currentTile = currentTile.getNextSibling();
				}
					
				
				//System.out.println(tileCountX + ", " + tileCountY + " Tile found : " + currentTile.getNodeName() + " : " + ((Element) currentTile).getAttribute("gid"));

				Element element = (Element) currentTile;
				int tempInt = 0;
				tempInt = Integer.parseInt(element.getAttribute("gid")) - 1;
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
			//System.out.println("Layer finished !");
	    }

		//System.out.println("Finished parsing, displaying the matrix.");
		//displayMatrix();
		} catch (Exception ex) {
			ex.printStackTrace();
			}
	}
			

			
}