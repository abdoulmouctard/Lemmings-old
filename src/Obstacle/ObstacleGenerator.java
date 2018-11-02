package Obstacle;

import java.util.List;
import Model.Constantes;
import Model.Map;
import Model.Personage;

public class ObstacleGenerator extends Obstacle 
{
	List<Personage> pers_list;
	public ObstacleGenerator(List<Personage> pers_l) 
	{
		super(null);
		pers_list = pers_l;
		type = 8;
	}

	@Override
	public void execute(Personage personage, List<Map> map) 
	{
		int index = generateIndex(map, personage);
		map.get(index).setObstacle(new Mine(pers_list));
		map.get(index).seType(6);

		index = generateIndex(map, personage);
		map.get(index).setObstacle(new Teleportation(pers_list));
		map.get(index).seType(7);
		
		index = generateIndex(map, personage);
		map.get(generateIndex(map, personage)).setObstacle(new Mine(pers_list));
		map.get(index).seType(8);
		
	}
	
	private int generateIndex(List <Map> map, Personage personage) 
	{
		int x,y, index = -1;
		Map m;
		while(index == -1) 
		{
			x = Constantes.GAME_SCALE * (int)(Math.random()*(80));
			y = Constantes.GAME_SCALE * (int)(Math.random()*(80));
			
			index = Map.findIndex(x, y, map.size());
			if(index != -1 ) 
			{
				m = map.get(index);
				if((!m.isDisplay() || !m.isDestroyable())||(m.isDisplay() && m.getObstacle() != null)) {index = -1;}
				//
			}
			
		}
		return index;
	}
}
