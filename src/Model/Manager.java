package Model;

import javax.swing.JComponent;

import Graphics.Frame;
import Graphics.Guide;
import Graphics.Start;

public class Manager
{
	private Frame frame;
	private Game game;   //1
	private Guide guide; //2
	private Start start; //3

	private int panel_type;
	private int choix;
	private JComponent panel;
	private boolean continuer;
	
	private int[][] matrice;
	
	public Manager(int [][]m)
	{
		matrice = m;
		game = new Game(matrice);
		start = new Start();
		guide = new Guide();
		frame = new Frame();
		switchPanel(3);
		continuer = true;
	}
	
	
	public boolean step()
	{
		sleep(Constantes.REFRECH);
		controller();
		panel.repaint();
		if(continuer && panel_type == 1){game.step();}
		return continuer;
	}
	
	private void controller()
	{
		if(panel_type == 1) // LE PLATEAU
		{
			choix = game.getChoix();
			
			if(choix == 1)
			{
				switchPanel(2);
				game.setPlay(false);
				guide.renameBtn(true);
			}else if(choix == 2)
			{
				switchPanel(3);
				game.setPlay(false);
				refresh_game();
			}
		}else if(panel_type == 2) // LE GUIDE D'UTILISATION
		{
			choix = guide.getChoix();
			if(choix == 1)
			{
				switchPanel(1);
				game.setPlay(true);
			}else if(choix == 2)
			{
				switchPanel(3);
				refresh_game();
			}
			
		}else if(panel_type == 3) //ACCUEIL
		{
			choix = start.getChoix();
			if(choix == 1)
			{
				switchPanel(1);
				game.setPlay(true);
			}else if(choix == 2)
			{
				switchPanel(2);
				guide.renameBtn(false);
				panel.repaint();
			}else if(choix == 3){continuer = false;}
		}
	}

	private void switchPanel(int type)
	{
		type = (type>0 && type <4)?type:1;
		panel_type = type;
		switch(panel_type) 
		{
			case 1:
				frame.setTitle("LEMMINGS GAME - PLATEAU ");
				panel = game.getPanel();
				break;
			case 2:
				frame.setTitle("LEMMINGS GAME - GUIDE D'UTILISATION ");
				panel = guide.getPanel();
				break;
			case 3:
				frame.setTitle("LEMMINGS GAME");
				panel = start.getPanel();
				break;
			default:break;
		}
		frame.setContentPane(panel);
	}
	
	private void sleep(int micro_time)
	{
		if(micro_time > 0) 
		{
			try {Thread.sleep(micro_time);}
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	private void refresh_game(){game = new Game(matrice);}
	
}
