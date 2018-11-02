package Fonctionnalities;

import java.util.List;

import Model.Constantes;
import Model.Direction;
import Model.Map;
import Model.Personage;

public abstract class Job 
{

	protected int job_count;
	protected Personage personnage;
	protected List<Map> map;
	protected boolean estBloqueur;
	protected Direction direction;
	
	public boolean EstBloqueur() {return estBloqueur;}
	
	public Job() 
	{
		estBloqueur = false;
	}
	public abstract boolean execute();
	
	public void setPersonnage(Personage pers) 
	{
		this.personnage = pers;
	}
	public void setDirection(Direction d)
	{
		direction = d;
	}
	public void setMap(List<Map> obs) {this.map = obs;}
	
	protected boolean isOnSupport(Personage p)
	{
		int ret = Map.findIndex(p.getX(), p.getY()+Constantes.GAME_SCALE, map.size());
		if( ret != -1 && !map.get(ret).isDisplay()) 
		{
			return false;
		}else return true;
	}
	
	
	protected boolean isFree(int index) 
	{
		return (index != -1 )&&!map.get(index).isDisplay();
	}
	
	protected boolean stopWorking() {reload();return false;}
	

	public abstract void reload();

	public void setPersArray(List<Personage> lp, int current){}
	
	
}
