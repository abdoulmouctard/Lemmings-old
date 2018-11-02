package Fonctionnalities;

import Model.Constantes;

import Model.Direction;
import Model.Map;

public class Parachutiste extends Job{

	private int nbre_pas = 0;
	
	public Parachutiste()
	{
		super();
		job_count = 0;
	}

	@Override
	public boolean execute() 
	{
		nbre_pas ++;
		if(isOnSupport(personnage) && job_count != 0) {return stopWorking();}

		if(job_count == 0 && isOnSupport(personnage)) 
		{
			if (personnage.getDirection() == Direction.Right) 
			{
				if(isFree(Map.findIndex(personnage.getX()+Constantes.GAME_SCALE, personnage.getY(), map.size()))) 
				{
					return true;
				}
			}else
			{
				if(isFree(Map.findIndex(personnage.getX()-Constantes.GAME_SCALE, personnage.getY(), map.size()))) 
				{
					return true;
				}
			}
			return stopWorking();
		}
		
		if(job_count % 2 == 0) 
		{
			personnage.moveDown(false);
			personnage.setStepDown(0);
			personnage.setMoved(true);
		}
		
		job_count ++;
		
		int index = Map.findIndex(personnage.getX(), personnage.getY()+Constantes.GAME_SCALE, map.size());
		
		if(nbre_pas >= 3 || (index != -1 && map.get(index).isDisplay())) {stopWorking();}
		
		return true;
	}
	
	@Override
	public void reload() 
	{
		nbre_pas = 0;
		job_count = 0;
	}
}
