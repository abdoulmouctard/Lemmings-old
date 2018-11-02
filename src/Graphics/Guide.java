package Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;

import Model.Constantes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Guide
{
	private JButton start;
	private JButton home;
	private JComponent panel;
	private int choix;

	public Guide()
	{
		choix = -1;
		String [] desc_t = new String[] {"ENTREE","SORTIE","MUR SIMPLE","MUR INDESTRUCTIBLE","MUR OBS:(TELEPORTATION)",
											"MUR OBS:(GENERATEUR OBS)","MUR OBS:(MINE)","MUR OBS:(FEU)"};

		Image [] icons = new Image[] {Constantes.entree,Constantes.sortie,Constantes.mur_simple,Constantes.mur_indes,
									Constantes.teleportation,Constantes.generateur_obs,Constantes.mine,Constantes.feu};

		String [] desc_l = new String[]{"LEMMING (MARCHEUR)","LEMMING (BOMBEUR)","LEMMING (BLOQUEUR)","LEMMING (CHARPENTIER)",
										"LEMMING (FOUREUR)","LEMMING (GRIMPEUR)","LEMMING (TUNNELIER)","LEMMING (PARACHUTISTE)"};

		Color [] couleurs = new Color[] {Color.CYAN,Color.RED,Color.DARK_GRAY,Color.MAGENTA,Color.BLUE,Color.LIGHT_GRAY,Color.ORANGE,Color.PINK};

		panel = new JComponent()
		{
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g)
			{
				Graphics2D g2 = (Graphics2D)g;

				g.setColor(Constantes.game_background);
				g.fillRect(0, 0, 1000, 600);

				g.setColor(new Color(138, 19, 95));
				g.setFont(new Font(null, Font.CENTER_BASELINE, 30));
				g.drawString(" LES LEMMINGS PARTENT EN AVENTURE ! ",200 , 50);

				int x = 260, y = 300;

				g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN|Font.BOLD, 13));
				g.setColor(Color.GRAY);
				g.drawString("Vous avez pour objectif principale dans ce jeu, de sauver le maximum de Lemmings durant leur parcours.", x-70, y-200);
				g.drawString("Pour ce faire,  nous metons à votre disposition des outils qui vous permetront de les associer à des taches", x-70, y-180);
				g.drawString("afin d'eviter les pieges, ainsi que les sauts d'une hauteur sup à 5 pas. Ces pieges peuvent etre de plusieurs ", x-70, y-165);
				g.drawString("natures, voir la \"DESCRIPTION DU TERRAIN\" ci dessous. Ainsi, a chaque fois que vous associer un Lemmings", x-70, y-150);
				g.drawString("a une tache, ce dernier va changer de comportement mais aussi de couleur, selon la fonction démandée voir", x-70, y-135);
				g.drawString("aussi la \"DESCRIPTION DES LEMMINGS\" ci-dessous. ", x-70, y-120);

				g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN|Font.BOLD, 15));
				g.setColor(new Color(35, 108, 126));
				g.drawLine(500, y-20, 500, y+140);
				g.drawString(" DESCRIPTION DES LEMMINGS ", 550, y-30);
				g.drawString(" DESCRIPTION DU TERRAIN ", 250, y-30);

				g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));

				for (int i = 0; i < icons.length; i++)
				{
					g2.drawImage(icons[i], x, y+(i)*20-20, 18, 18,this);
					g.setColor(Color.GRAY);
					g.drawString(desc_t[i],x+20+2, y+(i)*20-8);
				}

				x = 560;
				for (int i = 0; i < couleurs.length; i++)
				{
					g.setColor(couleurs[i]);
					g.fillOval(x, y+(i)*20-20, 20, 20);
					g.setColor(Color.GRAY);
					g.drawString(desc_l[i],x+20+2, y+(i)*20-10);
				}

				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
				g.setColor(Color.red);
				g.drawString(" /!\\ LA DUREE MAXIMALE DU JEU EST DE "+Constantes.GAME_TIMEOUT+"min", 350, 495);
			}
		};

		start = new JButton(" JOUER > ");
		start.setLocation(510, 540);
		start.setSize(110, 30);
		start.setBackground(Color.WHITE);
		start.setFont(new Font(null, Font.BOLD, 10));
		start.setFocusable(false);

		home = new JButton(" < ACCUEIL ");
		home.setFont(new Font(null, Font.BOLD, 10));
		home.setLocation(400, 540);
		home.setSize(110, 30);
		home.setBackground(Color.WHITE);
		home.setFocusable(false);

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				choix = 1;
			}
		});


		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				choix = 2;
			}
		});

		panel.add(start);
		panel.add(home);
	}

	public void renameBtn(boolean b){
		if(b){
			start.setText("CONTINUER >");
		}else {
			start.setText(" JOUER > ");

		}
	}
	public int getChoix(){return choix;}
	public JComponent getPanel(){choix = -1;return panel;}

}
