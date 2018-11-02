package Fonctionnalities;
import Model.Constantes;
import Model.Map;
 

public class Foureur extends Job 
{	
	public Foureur() 
	{
		super();
		job_count = 5;
	}
	
	@Override
	public boolean execute() 
	{
		if(isOnSupport(personnage)) 
		{
			int index = Map.findIndex(personnage.getX(), personnage.getY()+Constantes.GAME_SCALE, map.size());
			if( index != -1) 
			{
				if(map.get(index).isDestroyable()) 
				{
					if(map.get(index).getObstacle() != null) 
					{
						int type = map.get(index).getObstacle().getType();
						if( type == 8 || type == 6) {map.get(index).getObstacle().execute(personnage, map);}
					}
					map.get(index).setDisplay(false);
					job_count --;
				}else {return stopWorking();}//********************
			}
		}else{return stopWorking();}
		
		return job_count > 0;
	}
	
	@Override
	public void reload() {job_count = 5;}

}
