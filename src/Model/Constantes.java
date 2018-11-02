package Model;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;


public class Constantes
{	public final static int           HIGHT = 30;
	public final static int           WIDTH = 43;
	public final static int   NBRE_LEMMINGS = 5;
	public final static int    GAME_TIMEOUT = 5;
	public final static int  CLICKS_TIMEOUT = 10;
	public final static int      GAME_SCALE = 20;
	public final static int         REFRECH = 200;
	final static String DIR = "src/Media/";

	public final static Color game_background  = new Color(19,24,56);

	public final static Image start_background = Toolkit.getDefaultToolkit().getImage(DIR+"start.png");
	public final static Image entree           = Toolkit.getDefaultToolkit().getImage(DIR+"entree.png");
	public final static Image sortie           = Toolkit.getDefaultToolkit().getImage(DIR+"sortie.png");
	public final static Image mur_indes        = Toolkit.getDefaultToolkit().getImage(DIR+"mur_indes.png");
	public final static Image mur_simple  	   = Toolkit.getDefaultToolkit().getImage(DIR+"mur_simple.png");
	public final static Image feu              = Toolkit.getDefaultToolkit().getImage(DIR+"feu.png");
	public final static Image teleportation    = Toolkit.getDefaultToolkit().getImage(DIR+"teleportation.png");
	public final static Image generateur_obs   = Toolkit.getDefaultToolkit().getImage(DIR+"generateur_obs.png");
	public final static Image mine             = Toolkit.getDefaultToolkit().getImage(DIR+"mine.png");
	public final static Image help             = Toolkit.getDefaultToolkit().getImage(DIR+"help_btn.png");
	public final static Image home             = Toolkit.getDefaultToolkit().getImage(DIR+"home_btn.png");

	public final static ImageIcon play         = new ImageIcon(Toolkit.getDefaultToolkit().getImage(DIR+"play_btn.png"));
	public final static ImageIcon pause        = new ImageIcon(Toolkit.getDefaultToolkit().getImage(DIR+"pause_btn.png"));
}
