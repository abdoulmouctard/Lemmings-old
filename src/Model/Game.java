package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

import Graphics.Gui;
import Obstacle.Fire;
import Obstacle.Mine;
import Obstacle.Obstacle;
import Obstacle.ObstacleGenerator;
import Obstacle.Teleportation;


public class Game
{
	private List <Map> map;
	private List <Personage> personages;
	private Gui graphic;

	private boolean play;
	private int nbre_pers_active;
	private int execution_time;
	private int nbre_pers_push;
	private int compteur;
	private int score;
	private int total_lemming;
	private int clicks_timeout;

	public Game(int [][] t)
	{
		score = 0;
		total_lemming = 0;
		execution_time = 0;
		play = false;
		nbre_pers_active = 0;
		compteur = 0;

		map = new ArrayList<>();
		personages = new ArrayList<>();
		loading_map(t);
		loading_pers(Constantes.NBRE_LEMMINGS);
		graphic = new Gui();
		graphic.setList(personages, map);
	}

	public void setPlay(boolean b) {this.play = b;}

	public void step()
	{
		if(play) {execution_time += Constantes.REFRECH;}

		pushPersonnage();
		int choix = graphic.getChoise();

		play = graphic.getPlay();
		graphic.UpdateButtons(nbre_pers_active,score, execution_time);
		if(play)
		{
			if(choix == 0) {motionController();}
			else if(choix == 2||choix == 3|| choix == 4)
			{
				if(clicks_timeout < Constantes.CLICKS_TIMEOUT)
				{
					clicks_timeout++;
					int index_pers = findPersonnage(graphic.getMousePositon());
					if(index_pers!=-1)
					{
						if(choix != 4)
						{
							personages.get(index_pers).setJobState(graphic.getJob());

							if(choix == 3) {personages.get(index_pers).getState().setPersArray(personages, index_pers);}
						}else
						{
							personages.get(index_pers).setJobState(JobState.MARCHEUR);
						}
					}
				}else
				{
					clicks_timeout = 0;
					graphic.setChoise(0);
					graphic.setPlay(true);
					motionController();
				}
			}
			manager();
			resetMotion();
		}
		if(!gameStatus()){graphic.gameFinished(total_lemming, score,execution_time/1000);}

	}

	private void motionController()
	{
		Personage p;
		for(int i =0;i<personages.size();i++)
		{
			p = personages.get(i);

			if(!p.isActive() || p.isMoved()) {continue;}

			p.motion();

			for(int j=0;j<personages.size();j++)
			{
				if(i != j)
				{
					if(p.getX() == personages.get(j).getX() && p.getY() == personages.get(j).getY()){p.setMoved(true);}
				}
			}
		}

	}



	private int findPersonnage(Position p)
	{
		if( p==null || p.getX() >= 860) {return -1;}
		Personage pers;
		for (int i =0;i<personages.size();i++)
		{
			pers = personages.get(i);
			if( (p.getX() >= pers.getX() && p.getX() <=  pers.getX()+ Constantes.GAME_SCALE)
				  && ( p.getY() >= pers.getY() && p.getY() <= pers.getY() + Constantes.GAME_SCALE)
			)
			{return i;}
		}
		return -1;
	}



	public boolean gameStatus()
	{
		nbre_pers_active = 0;
		for (Personage pers: personages)
		{
			if(pers.isActive() && pers.isPushed()) {nbre_pers_active++;}
		}
		return (nbre_pers_active != 0) && (execution_time/1000 <= 60*Constantes.GAME_TIMEOUT);
	}

	private void loading_pers(int nb_pers)
	{
		if(nb_pers<=3) nb_pers = 3;
		nbre_pers_push = nb_pers;
		total_lemming = nb_pers;

		for (Map m : map)
		{
			if(m.geType() == 4)
			{
				for (int i = 0; i < nb_pers; i++) {this.personages.add(new Personage(m.getX(),m.getY(),this.map));}
				break;
			}
		}
	}

	private void pushPersonnageAux()
	{
		int cpt = 0;
		for (Personage pers : personages)
		{
			if (!pers.isPushed())
			{
				pers.setPushed();
				break;
			}
			cpt ++;
		}
		if(cpt == nbre_pers_push) {nbre_pers_push = 0;}
	}

	private void pushPersonnage()
	{
		if(!play)return;
		if (nbre_pers_push != 0 && (compteur%5 == 0)) {pushPersonnageAux();compteur = 0;}
		compteur++;
	}

	private void loading_map(int [][] t)
	{
		int height = t.length;
		int  width = t[0].length;
		int compteur = 0;
		int x,y;

		int nbre_entree = 0, nbre_sortie=0;

		for (int i = 0; i < width; i++)
		{
			int type;
			for (int j = 0; j < height; j++)
			{
				x = i*Constantes.GAME_SCALE;
				y = j*Constantes.GAME_SCALE;

				type = (t[j][i]>=0 && t[j][i] <= 8)?t[j][i]:0;

				if(type != 4 && type != 5)
				{
					map.add(Map.Wall(x,y,type));
					if(type ==3) {map.get(compteur).setObstacle(new Fire(personages));}
					else if(type == 6) {map.get(compteur).setObstacle(new Mine(personages));}
					else if(type == 7) {map.get(compteur).setObstacle(new Teleportation(personages));}
					else if(type == 8) {map.get(compteur).setObstacle(new ObstacleGenerator(personages));}

				}else
				{
					if(type == 4 && nbre_entree == 0) {map.add(Map.Entree(x, y));nbre_entree++;}
					else if(type ==5 && nbre_sortie == 0) {map.add(Map.Sortie(x, y));nbre_sortie++;}
					else {map.add(Map.Wall(x,y,1));}
				}
				compteur ++;
			}
		}
	}

	private void resetMotion()
	{
		for (Personage pers: personages)
		{
			if(pers.isActive()) {pers.setMoved(false);}
		}
	}

	private void manager()
	{
		int index;
		Obstacle tmp_obs;
		for (Personage pers : personages)
		{
			if(!pers.isActive()) {continue;}

			if(pers.isOnFire() || pers.isOnTeleportation())
			{
				index = Map.findIndex(pers.getX(), pers.getY()+Constantes.GAME_SCALE, map.size());
				tmp_obs = map.get(index).getObstacle();
				tmp_obs.execute(pers, map);
			}else if(pers.isOnSortie())
			{
				score ++;
				pers.deactivate();
			}
		}
	}

	public int getChoix()
	{
		int ret = graphic.getChoise();

		if(ret == -2 || ret == -1)
		{
			ret = -ret;
			graphic.setChoise(0);
			return ret;
		}
		return -1;
	}

	public JComponent getPanel(){return graphic.getPanel();}

}
