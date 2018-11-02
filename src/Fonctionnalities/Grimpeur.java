package Fonctionnalities;

import Model.Constantes;
import Model.Direction;
import Model.Map;

public class Grimpeur extends Job {

	public Grimpeur() 
	{
		super();
	}
	@Override
	public boolean execute() 
	{
		int x = personnage.getX();
		int y = personnage.getY();
		int index = -1;
		
		personnage.setMoved(true);
		
		if (personnage.getDirection() == Direction.Right) 
		{
			index = Map.findIndex(x+Constantes.GAME_SCALE, y, map.size());
			if(isFree(index)) 
			{
				personnage.moveRight();
				personnage.setMoved(true);
				return stopWorking();
			} 
				
			if(isFree(Map.findIndex(x, y-Constantes.GAME_SCALE, map.size())) && 
					!isFree(Map.findIndex(x+Constantes.GAME_SCALE, y-Constantes.GAME_SCALE, map.size()))) 
			{
				personnage.moveUp(false);
				personnage.setMoved(true);
				return true;
			}
			
			if (isFree(Map.findIndex(x+Constantes.GAME_SCALE, y-Constantes.GAME_SCALE, map.size()))) 
			{
				personnage.moveUp(true);
				return stopWorking();
			}
		}else 
		{
			index = Map.findIndex(x-Constantes.GAME_SCALE, y, map.size());
			if(isFree(index)) 
			{
				personnage.moveLeft();
				personnage.setMoved(true);
				return stopWorking();
			} 
			
			if(isFree(Map.findIndex(x, y-Constantes.GAME_SCALE, map.size())) && 
					!isFree(Map.findIndex(x-Constantes.GAME_SCALE, y-Constantes.GAME_SCALE, map.size()))) 
			{
				personnage.moveUp(false);
				personnage.setMoved(true);
				return true;
			}
			
			if (isFree(Map.findIndex(x-Constantes.GAME_SCALE, y-Constantes.GAME_SCALE, map.size()))) 
			{
				personnage.moveUp(true);
				return stopWorking();
			}
		}
		
		return stopWorking();
	}
	
	@Override
	public void reload() 
	{
		
	}

}

	