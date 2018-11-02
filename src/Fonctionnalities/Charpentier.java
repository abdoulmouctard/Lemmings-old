package Fonctionnalities;


import Model.Constantes;
import Model.Map;

public class Charpentier extends Job 
{
	public Charpentier() 
	{
		job_count = 5;
	}
	
	@Override
	public boolean execute() 
	{
		if(personnage.getDirection() == direction) 
		{
			job_count --;
			int index = -1;
			switch (direction) 
			{
				case Right:
					index = Map.findIndex(personnage.getX()+Constantes.GAME_SCALE, personnage.getY(), map.size());
					if (isFree(index)) {map.get(index).setDisplay(true);map.get(index).seType(1);}
					else {job_count = 0;}
					break;

				case Left:
					index = Map.findIndex(personnage.getX()-Constantes.GAME_SCALE, personnage.getY(), map.size());
					if (isFree(index)) {map.get(index).setDisplay(true);map.get(index).seType(1);}
					else {job_count = 0;}
					break;
	
				default:break;
			}
			
			if(job_count != 0) {return true;}
		}
		return false;
	}
	
	@Override
	public void reload() {job_count = 5;}
	

	
	

}
