package Fonctionnalities;


import Model.Constantes;
import Model.Direction;
import Model.Map;

public class Tunnelier extends Job 
{
	public Tunnelier() 
	{
		super();
	}

	
	@Override
	public boolean execute() 
	{
		if(personnage.getDirection() ==  direction)
		{
			int index = -1;
			
			if(direction == Direction.Right) 
			{
				index = Map.findIndex(personnage.getX()+Constantes.GAME_SCALE, personnage.getY(), map.size());
				if(!isFree(index) && index != -1 && map.get(index).isDestroyable()) {map.get(index).setDisplay(false);return true;}
				else {return stopWorking();}
			}else 
			{
				index = Map.findIndex(personnage.getX()-Constantes.GAME_SCALE, personnage.getY(), map.size());
				if(!isFree(index) && index != -1 && map.get(index).isDestroyable()) 
				{
					map.get(index).setDisplay(false);
					return true;
				}else {return stopWorking();}
				
			}
		}
		return stopWorking();
	}
	
	@Override
	public void reload() 
	{
		
	}

}
