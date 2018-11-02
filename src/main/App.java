package main;

import java.io.IOException;

import Model.Manager;
import Model.loadMap;

public class App
{
	public static void main(String[] args) throws IOException
	{
		Manager manager = new Manager(loadMap.getMatrice("src/Media/configuration"));
		while(manager.step());
		System.exit(0);
	}
}
