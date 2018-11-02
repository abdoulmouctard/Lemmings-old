package Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

import Model.Constantes;

public class Start 
{
	private int choix;
	private JComponent panel;
	private JButton start_bnt;
	private JButton guide_bnt;
	private JButton live_bnt;
	
	public Start()
	{
		choix = -1;
		start_bnt = new JButton(" NOUVELLE PARTIE ");
		start_bnt.setSize(200, 40);
		start_bnt.setLocation(400, 280);
		start_bnt.setBorderPainted(true);
		start_bnt.setBackground(Color.WHITE);
		start_bnt.setFont(new Font(null, Font.BOLD, 10));
		start_bnt.setFocusable(false);

		guide_bnt = new JButton(" AIDE (?)");
		guide_bnt.setSize(200, 40);
		guide_bnt.setLocation(400, 320);
		guide_bnt.setBackground(Color.WHITE);
		guide_bnt.setFont(new Font("BOLD", Font.BOLD, 10));
		guide_bnt.setFocusable(false);
		
		live_bnt = new JButton(" QUITTER ");
		live_bnt.setSize(200, 40);
		live_bnt.setLocation(400, 360);
		live_bnt.setBackground(Color.WHITE);
		live_bnt.setFont(new Font(null, Font.BOLD, 10));
		live_bnt.setFocusable(false);

		start_bnt.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0){choix = 1;}
		});
		
		guide_bnt.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0){choix = 2;}
		});
		live_bnt.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0){choix = 3;}
		});
		
		panel = new JComponent()
			{
				private static final long serialVersionUID = 1L;
				@Override
				protected void paintComponent(Graphics g)
				{
					Graphics2D g2 = (Graphics2D)g;
					g2.drawImage(Constantes.start_background, 0, 0, 1000, 600,panel);
				}
				
			};
			
			panel.add(start_bnt);
			panel.add(guide_bnt);
			panel.add(live_bnt);
	}
	
	public int getChoix() {return choix;}
	
	public JComponent getPanel(){choix = -1; return panel;}
	
}
