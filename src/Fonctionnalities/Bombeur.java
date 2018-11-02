package Fonctionnalities;


import Model.Constantes;
import Model.Map;
 

public class Bombeur extends Job 
{
	public Bombeur() 
	{
		super();
		job_count = 3;
	}

	@Override
	public boolean execute() 
	{
		job_count --;
		
		if(job_count == 0) 
		{
			int x_pers = personnage.getX();
			int y_pers = personnage.getY();
			
			//DROITE
			explose(x_pers+Constantes.GAME_SCALE, y_pers);
			explose(x_pers+2*Constantes.GAME_SCALE, y_pers);
			
			//GAUCHE
			explose(x_pers-Constantes.GAME_SCALE, y_pers);
			explose(x_pers-2*Constantes.GAME_SCALE, y_pers);
			
			//HAUT
			explose(x_pers, y_pers-Constantes.GAME_SCALE);
			explose(x_pers, y_pers-2*Constantes.GAME_SCALE);
			
			//BAS
			explose(x_pers, y_pers+Constantes.GAME_SCALE);
			explose(x_pers, y_pers+2*Constantes.GAME_SCALE);
			
			
			//DROITE-HAUT
			explose(x_pers+Constantes.GAME_SCALE, y_pers-Constantes.GAME_SCALE);
			explose(x_pers+2*Constantes.GAME_SCALE, y_pers-2*Constantes.GAME_SCALE);
			
			explose(x_pers+2*Constantes.GAME_SCALE, y_pers-Constantes.GAME_SCALE);
			explose(x_pers+Constantes.GAME_SCALE, y_pers-2*Constantes.GAME_SCALE);
			
			//DROITE-BAS
			explose(x_pers+Constantes.GAME_SCALE, y_pers+Constantes.GAME_SCALE);
			explose(x_pers+2*Constantes.GAME_SCALE, y_pers+2*Constantes.GAME_SCALE);
	
			explose(x_pers+2*Constantes.GAME_SCALE, y_pers+Constantes.GAME_SCALE);
			explose(x_pers+Constantes.GAME_SCALE, y_pers+2*Constantes.GAME_SCALE);
			
			//GAUCHE-HAUT
			explose(x_pers-Constantes.GAME_SCALE, y_pers-Constantes.GAME_SCALE);
			explose(x_pers-2*Constantes.GAME_SCALE, y_pers-2*Constantes.GAME_SCALE);
			

			explose(x_pers-2*Constantes.GAME_SCALE, y_pers-Constantes.GAME_SCALE);
			explose(x_pers-Constantes.GAME_SCALE, y_pers-2*Constantes.GAME_SCALE);
			
			//GAUCHE-BAS
			explose(x_pers-Constantes.GAME_SCALE, y_pers+Constantes.GAME_SCALE);
			explose(x_pers-2*Constantes.GAME_SCALE, y_pers+2*Constantes.GAME_SCALE);
			
			explose(x_pers-2*Constantes.GAME_SCALE, y_pers+Constantes.GAME_SCALE);
			explose(x_pers-Constantes.GAME_SCALE, y_pers+2*Constantes.GAME_SCALE);
			
			personnage.deactivate();
			stopWorking();
		}
		

		return job_count > 0;
	}
	
	private void explose(int x, int y) 
	{
		int index = Map.findIndex(x, y, map.size());
		if(index != -1 && map.get(index).isDestroyable()) 
		{
			if(map.get(index).getObstacle() != null) 
			{
				int type = map.get(index).getObstacle().getType();
				if( type == 8 || type == 6) {map.get(index).getObstacle().execute(personnage, map);}
			}
			map.get(index).setDisplay(false);
		}
	}

	@Override
	public void reload() 
	{
		job_count = 3;
	}

}
