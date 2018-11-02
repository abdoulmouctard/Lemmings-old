package Obstacle;

import java.util.List;

import Model.Map;
import Model.Personage;

public class Fire extends Obstacle
{
    public Fire(List<Personage> pers_list)
    {
    	super(pers_list);
        type = 3;
    }
    
	@Override
	public void execute(Personage personage, List<Map> map) 
	{
		personage.deactivate();
	}
}
