package Obstacle;

import java.util.List;

import Model.Map;
import Model.Personage;

public abstract class Obstacle 
{
	Obstacle(List<Personage> pers_list)
	{
		list_personnage = pers_list;
	}
	protected boolean generator;
	protected int type;
	protected List<Personage> list_personnage;
	
	public int getType(){return type;}
	
	public boolean isGenerator() {return generator;}
	public void setGenerator(boolean generator) {this.generator = generator;}
	public abstract void execute(Personage personage,List<Map> map);
	
}
