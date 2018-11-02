package Obstacle;

import java.util.List;

import Model.Constantes;
import Model.Map;
import Model.Personage;

public class Mine extends Obstacle {

	public Mine(List<Personage> pers_list) 
	{
		super(pers_list);
		type = 6;
	}

	@Override
	public void execute(Personage personage, List<Map> map) 
	{
		int x = personage.getX();
		int y = personage.getY();
		personage.deactivate();
		
		int tmpx,tmpy;
		
		for (Personage p : list_personnage) 
		{
			tmpx = p.getX();
			tmpy = p.getY();
			
			if((y == tmpy && tmpx == x- Constantes.GAME_SCALE)||(y == tmpy && tmpx == x + Constantes.GAME_SCALE)) {p.deactivate();}
		}
		
	}

}
