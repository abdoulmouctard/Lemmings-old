package Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.Constantes;
import Model.JobState;
import Model.Map;
import Model.Personage;
import Model.Position;

public class Gui
{
	private JobState js = null;
	private Panel panel =new Panel();
	private Click menu;
	private boolean play_btn = true;
	private Position mouse_pos = null;
	private int choise = 0;
	
	public Gui() 
	{
		panel.setBackground(Color.WHITE);
		menu = new Click(this);
		addButtons();
	}
	public void repaint() {panel.repaint();}
	
	public void setList(List<Personage> pers, List<Map> obs) 
	{
		this.panel.obs= obs;
		this.panel.pers = pers;
	}
	
	private void addButtons()
	{
		Color c1 = new Color(215, 215, 255);
		Color c2 = Color.WHITE;
		
		//GUIDE
		menu.getMenu().get(0).setLocation(860, 0);	
		menu.getMenu().get(0).setIcon(new ImageIcon(Constantes.help));
		menu.getMenu().get(0).setSize(67, 40);
		panel.add(menu.getMenu().get(0));
		
		
		//HOME
		menu.getMenu().get(1).setLocation(927, 0);
		menu.getMenu().get(1).setSize(72, 40);
		menu.getMenu().get(1).setIcon(new ImageIcon(Constantes.home));
		panel.add(menu.getMenu().get(1));
		
		Font fnc_font = new Font(Font.SANS_SERIF, Font.PLAIN|Font.BOLD, 11);
		
		int y = 90;
		for (int j=3;j<menu.getMenu().size();j++) 
		{
			y = 90 + 40*(j-3);
			menu.getMenu().get(j).setLocation(860, y);
			menu.getMenu().get(j).setSize(140, 40);
			menu.getMenu().get(j).setFont(fnc_font);
			menu.getMenu().get(j).setBackground((j%2==1)?c1:c2);
			panel.add(menu.getMenu().get(j));
			
		}
		//PLAY - PAUSE 
		menu.getMenu().get(2).setLocation(860, 40);
		menu.getMenu().get(2).setSize(140, 50);
		menu.getMenu().get(2).setFont(new Font(Font.DIALOG, Font.PLAIN|Font.BOLD, 10));
		panel.add(menu.getMenu().get(2));
		menu.getMenu().get(2).setIcon(Constantes.pause);
		
		//POUR LENOMBRE DE PERS ACTIFS
		menu.getMenu().get(menu.getMenu().size()-1).setEnabled(false);
		menu.getMenu().get(menu.getMenu().size()-1).setSize(140, 80);
		menu.getMenu().get(menu.getMenu().size()-1).setLocation(860, 410);
		menu.getMenu().get(menu.getMenu().size()-1).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
		menu.getMenu().get(menu.getMenu().size()-1).setBackground(Color.DARK_GRAY);
		
		//POUR LE TIME
		menu.getMenu().get(menu.getMenu().size()-2).setEnabled(false);
		menu.getMenu().get(menu.getMenu().size()-2).setSize(140, 40);
		menu.getMenu().get(menu.getMenu().size()-2).setLocation(860, 490);
		menu.getMenu().get(menu.getMenu().size()-2).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		menu.getMenu().get(menu.getMenu().size()-2).setBackground(Color.DARK_GRAY);
		//SCORE
		menu.getMenu().get(menu.getMenu().size()-3).setEnabled(false);
		menu.getMenu().get(menu.getMenu().size()-3).setLocation(860, 530);
		menu.getMenu().get(menu.getMenu().size()-3).setSize(140, 50);
		menu.getMenu().get(menu.getMenu().size()-3).setFont(fnc_font);
		menu.getMenu().get(menu.getMenu().size()-3).setBackground(Color.DARK_GRAY);

	}
	
	public void setJob(JobState j){js = j;mouse_pos = null;}
	public JobState getJob() {return js;}
	public void setMousePositon(Position pos) {mouse_pos = pos;}
	public Position getMousePositon() {return mouse_pos;}
	public Panel getPanel() {return panel;}
	
	public boolean getPlay() 
	{
		choise = (choise ==1)?0:choise;
		return play_btn;
	}
	
	public void setPlay(boolean play) {this.play_btn = play;}
	public int getChoise(){return choise;}
	
	public void switchPausePlay() {this.play_btn = !play_btn;}//
	
	public void setChoise(int ch)
	{
		if(ch == 1)
		{
			play_btn = !play_btn;
			disableButtons();
		}
		choise = ch;
	}
	
	public void UpdateButtons(int pers_active,int score, int time) 
	{
		time = time/1000;
		menu.getMenu().get(menu.getMenu().size()-1).setText(" "+pers_active+" ");
		if(time >= 60)
		{
			int min = time/60;
			int sec = time%60;
			menu.getMenu().get(menu.getMenu().size()-2).setText(min+"min"+sec+"s");
		}else {menu.getMenu().get(menu.getMenu().size()-2).setText(time+"sec");}
		
		menu.getMenu().get(menu.getMenu().size()-3).setText("SCORE : "+score+" ");
		disableButtons();
	}
	
	public void gameFinished(int nbre_total, int score, int timeout) 
	{
		String temps;
		
		if(timeout >= 60) 
		{
			int min = timeout/60;
			int sec = timeout%60;
			temps = " "+min+"min"+sec+"s";
		}else{temps = timeout+"sec";}
		
		JOptionPane.showMessageDialog(null, " VOUS AVEZ SAUVE "+ score+" LEMMING(S) \n PARMIS "+ nbre_total+" \n EN "+temps,"FIN DE LA PARTIE",JOptionPane.INFORMATION_MESSAGE);		
		choise = -2;
	}
	
	
	private void disableButtons() 
	{
		if(play_btn){menu.getMenu().get(2).setIcon(Constantes.pause);}
		else {menu.getMenu().get(2).setIcon(Constantes.play);}
	}	
	
}
