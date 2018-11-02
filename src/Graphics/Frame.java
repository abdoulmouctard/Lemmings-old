package Graphics;

import javax.swing.JFrame;


public class Frame extends JFrame  
{
	private static final long serialVersionUID = 1L;
	
	public Frame() 
	{
		super("LEMMINGS GAME");
		this.setSize(1000, 600);
		this.setLocation(50, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		setEnabled(true);
	}

	
}
