package Obstacle;

import java.util.List;

import Model.Constantes;
import Model.Map;
import Model.Personage;
import Model.Position;

public class Teleportation extends Obstacle 
{
	

	public Teleportation(List<Personage> pers_list) 
	{
		super(pers_list);
		type = 7;
	}
	
	@Override
	public void execute(Personage personage, List<Map> map) 
	{
		personage.setPosition(generatePosition(map));
	}
	
	private Position generatePosition(List<Map> m) 
	{
		int x=-1,y=-1;
		int index = -1,index2;
		while(index == -1) 
		{
			x= Constantes.GAME_SCALE * (int)(Math.random()*(40));
			y= Constantes.GAME_SCALE * (int)(Math.random()*(20));
			index =Map.findIndex(x, y, m.size());
			if(index != -1)
			{
				index2 = Map.findIndex(x, y+Constantes.GAME_SCALE, m.size());
				if(! m.get(index).isDisplay() && index2 != -1 && m.get(index2).isDisplay()) {return new Position(x,y);}
				else{index = -1;}
			}
		}
		return null;
	}
	
}
