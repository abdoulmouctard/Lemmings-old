package Graphics;

import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

import Model.JobState;
import Model.Position;

public class Click
{
	private Gui gui;
	private List<JButton> buttons = new ArrayList<>();
	private ActionListener buttonsListener = new ActionListener() 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			int choise = 2;
			if(e.getSource() == buttons.get(0))       {choise = -1;gui.setPlay(false);}
			else if(e.getSource() == buttons.get(1))  {choise = -2;gui.setPlay(false);}
			else if(e.getSource() == buttons.get(2))  {choise = 1;}
			else if(e.getSource() == buttons.get(3))  {gui.setJob(JobState.BOMBEUR);}
			else if(e.getSource() == buttons.get(4))  {gui.setJob(JobState.BLOQUEUR);choise = 3;}
			else if(e.getSource() == buttons.get(5))  {gui.setJob(JobState.CHARPENTIER);}
			else if(e.getSource() == buttons.get(6))  {gui.setJob(JobState.FOURREUR);}
			else if(e.getSource() == buttons.get(7))  {gui.setJob(JobState.GRIMPEUR);}
			else if(e.getSource() == buttons.get(8))  {gui.setJob(JobState.TUNNELIER);}
			else if(e.getSource() == buttons.get(9))  {gui.setJob(JobState.PARACHUTISTE);}
			else if(e.getSource() == buttons.get(10)) {gui.setJob(JobState.MARCHEUR);choise = 4;}
			else {choise = 0;}
			
			gui.setChoise(choise);
		}
	};

	private MouseAdapter mouseListener  = new MouseAdapter() 
	{
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			super.mouseClicked(e);
			gui.setMousePositon(new Position(e.getX(), e.getY()));
		}
		
	};
	
	public Click(Gui g) 
	{
		buttons = new ArrayList<>();
		creatMenu();
		gui = g;
		gui.getPanel().addMouseListener(mouseListener);
	}
	
	private void creatMenu()
	{
		buttons.add(new JButton());
		buttons.add(new JButton());
		
		buttons.add(new JButton());
		
		buttons.add(new JButton("Bombeur"));	
		buttons.add(new JButton("Bloqueur"));
		buttons.add(new JButton("Charpentier"));
		buttons.add(new JButton("Foureur"));
		buttons.add(new JButton("Grimpeur"));
		
		buttons.add(new JButton("Tunnelier"));
		buttons.add(new JButton("Parachutiste"));

		buttons.add(new JButton("Marcheur"));

		for (JButton b : buttons)
		{
			b.setFocusable(false);
			b.setBackground(Color.WHITE);
			b.setFocusable(false);
			b.addActionListener(buttonsListener);
		}
		
		buttons.add(new JButton("SCORE : "));
		buttons.add(new JButton("..."));
		
		buttons.add(new JButton ("..."));

	}
	
	public List<JButton> getMenu(){return buttons;}
	
}
