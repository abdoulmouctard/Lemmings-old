package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class loadMap
{
	private static BufferedReader Buf_Reader;

	public static int[][] getMatrice(String file_name) throws IOException
	{
		int largeur;
		int hauteur;
		String tmp;

		try {
			Buf_Reader = new BufferedReader(new FileReader(file_name));
			// tmp = Buf_Reader.readLine();

		} catch (FileNotFoundException e) {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            System.out.println("Current relative path is: " + s);
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, " FICHIER DE CONFIGURATION NON COMPATIBLE ",null, JOptionPane.WARNING_MESSAGE);
			System.exit(-1);
		}

		largeur = Constantes.WIDTH;
		hauteur = Constantes.HIGHT;

		if(largeur != Constantes.WIDTH || hauteur != Constantes.HIGHT)
		{
			JOptionPane.showMessageDialog(null, " FICHIER DE CONFIGURATION NON COMPATIBLE ",null, JOptionPane.WARNING_MESSAGE);
			System.exit(-1);
		}

		int [][]matrice = new int[hauteur][largeur];
		int indice_debut = 0;
		int indice_fin;
		int x;
			for (int i = 0; i < hauteur; i++) {
				indice_debut=0;
				tmp = Buf_Reader.readLine();
				for (int j = 0; j < largeur;j++)
				{
					if (indice_debut == tmp.length()-1) {
						indice_fin=  tmp.length();
					}
					else indice_fin=  first_char(tmp, ' ', indice_debut);
					x=Integer.parseInt(tmp.substring(indice_debut, indice_fin));
					matrice[i][j] =  x;

					indice_debut = indice_fin+1;
				}
			}
			return matrice;
	}

	private static int  first_char(String s, char caract, int debut)
	{
		for(int i = ((debut >= 0)?debut:0); i<s.length();i++)
		{
			if(s.charAt(i) == caract) return i;
		}
		return -1;
	}




}
