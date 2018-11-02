package Graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import javax.swing.JComponent;

import Model.Constantes;
import Model.Direction;
import Model.Map;
import Model.Personage;

public class Panel extends JComponent
{
	private static final long serialVersionUID = 1L;

	public Panel(){super();}
	
	public List<Map> obs;
	public List<Personage> pers;
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(Constantes.game_background);
		g.fillRect(0, 0, 1000, 600);
		drawingMap(g2);
		drawingPers(g2);
	}

	private void drawingMap(Graphics2D g)
	{
		for (Map ob : obs)
		{
			if(ob.isDisplay()) 
			{
				g.drawImage(MapImage(ob), ob.getX(), ob.getY(), Constantes.GAME_SCALE,Constantes.GAME_SCALE, this);
			}
		}
	}
	
	
	private void drawingPers(Graphics2D g)
	{
		for (Personage p : pers) 
		{
			if(p.isActive() && p.isPushed()) 
			{
				g.setColor(PersColor(p));
				g.fillOval(p.getX()+1,  p.getY()+1,  p.getWidth(), p.getWidth());
				g.setColor(Color.RED);
				if(p.getDirection() == Direction.Right) 
				{
					g.drawLine(p.getX()+Constantes.GAME_SCALE/2, p.getY()+Constantes.GAME_SCALE/2, p.getX()+Constantes.GAME_SCALE-2, p.getY()+Constantes.GAME_SCALE/2);
				}else
				{
					g.drawLine(p.getX()+2, p.getY()+Constantes.GAME_SCALE/2, p.getX()+Constantes.GAME_SCALE/2, p.getY()+Constantes.GAME_SCALE/2);
				}
			}
		}		
	}
	
	private Color PersColor(Personage p) 
	{
		switch (p.getJobState()) 
		{
			case BLOQUEUR:return Color.DARK_GRAY;
			case BOMBEUR: return Color.RED;
			case FOURREUR:return Color.BLUE;
			case GRIMPEUR:return Color.LIGHT_GRAY;
			case PARACHUTISTE: return Color.PINK;
			case TUNNELIER:return Color.ORANGE;
			case CHARPENTIER:return Color.MAGENTA;
			default: return Color.CYAN;
		}
	}
	private Image MapImage(Map m) 
	{
		switch (m.geType()) 
		{
			case 1:return Constantes.mur_simple;
			case 2: return Constantes.mur_indes;
			case 3:return Constantes.feu;
			case 4:return Constantes.entree;
			case 5:return Constantes.sortie;
			case 6:return Constantes.mine;
			case 7:return Constantes.teleportation;
			case 8:return Constantes.generateur_obs;
			default: return null;
		}
	}
}
