package Fonctionnalities;
import java.util.List;

import Model.Constantes;
 
import Model.Personage;

public class Bloqueur extends Job {

	private List<Personage> listPers;
	private int myIndex;
	
	public Bloqueur() {
		super();
		estBloqueur = true;
	}
	
	
	@Override
	public boolean execute() 
	{
		Personage pers;
		personnage.setMoved(true);
		for (int i=0; i < listPers.size();i++) 
		{
			pers = listPers.get(i); 
			if(pers.isActive() && i != myIndex && pers.getY() == personnage.getY()) 
			{
				if(personnage.getX() ==   pers.getX()+Constantes.GAME_SCALE) {pers.switchDirection();}
				else if(personnage.getX() == pers.getX()-Constantes.GAME_SCALE){pers.switchDirection();}
			}
		}
		return true;
	}
	
	public void setPersArray(List<Personage> lp, int current) 
	{
		myIndex = current;
		listPers = lp;
	}
	@Override
	public void reload() 
	{
		
	}
}
