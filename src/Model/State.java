package Model;


import java.util.ArrayList;
import java.util.List;

import Fonctionnalities.Bloqueur;
import Fonctionnalities.Bombeur;
import Fonctionnalities.Charpentier;
import Fonctionnalities.Foureur;
import Fonctionnalities.Grimpeur;
import Fonctionnalities.Job;
import Fonctionnalities.Parachutiste;
import Fonctionnalities.Tunnelier;

public class State 
{
	
	private JobState JS;
	private List<Job> jobs = new ArrayList<>();
	private int index = 0;
	private Personage pers;
	
	public State(List<Map> m, Personage p)
	{
		pers = p;
		JS = JobState.MARCHEUR;
		jobs.add(new Bloqueur());
		jobs.add(new Bombeur());
		jobs.add(new Foureur());
		jobs.add(new Grimpeur());
		jobs.add(new Parachutiste());
		jobs.add(new Tunnelier());
		jobs.add(new Charpentier());
		
		for (Job j : jobs)
		{
			j.setPersonnage(p);
			j.setMap(m);
		}
	}
	
	public void setJob(JobState js) 
	{
		JS = js;
		switch (JS) 
		{
			case BLOQUEUR: index = 0;break;
			case BOMBEUR: index = 1;break;
			case FOURREUR: index = 2;break;
			case GRIMPEUR: index = 3;break;
			case PARACHUTISTE: index = 4;break;
			case TUNNELIER: 
				index = 5;
				jobs.get(index).setDirection(pers.getDirection());
				break;
			case CHARPENTIER:
				index = 6;
				jobs.get(index).setDirection(pers.getDirection());
				break;
			default: break;
		}
		
		jobs.get(index).reload();		
	}
	
	public boolean execute_job() {return jobs.get(index).execute();}

	public JobState getJS() {return JS;}
	
	public void setPersArray(List<Personage> lp,int current) 
	{
		if (JS == JobState.BLOQUEUR) 
		{
			jobs.get(index).setPersArray(lp, current);
		}
	}
	
	
}
