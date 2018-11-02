package Model;

import Obstacle.Obstacle;

public class Map 
{
	private boolean destroyable;
	private int height = Constantes.GAME_SCALE;
	private int width = Constantes.GAME_SCALE;
	private Position pos;
	private Obstacle obstacle;
	private int wall_type;
	
	public static Map Entree(int x, int y)
	{
		return new Map(x, y, 4);
	}
	
	public static Map Sortie(int x, int y)
	{
		return new Map(x, y, 5);
	}
	
	public static Map Wall(int x, int y,int type)
	{
		return new Map(x, y, type);
	}
	
	
	private Map(int x, int y,int type) 
	{
		pos = new Position(x,y);
		
		wall_type = (type >=0 && type <= 8)?type:0;
		
		switch (type) 
		{
			case 1://MUR SIMPLE
				destroyable = true;
				break;
			case 2://MUR INDESTRUCTIBLE
				destroyable = false;
				break;
			case 3://MUR SIMPLE + FEU
				destroyable = false;
				break;
			case 4://ENTREE
				destroyable = false;
				break;
			case 5://SORTIE
				destroyable = false;
				break;
			case 6: //MUR SIMPLE + MINE
				destroyable = true;
				break;
			case 7://MUR SIMPLE + TELEPORTATION
				destroyable = false;
				break;
			case 8://MUR SIMPLE + GENERATEUR OBS
				destroyable = true;
				break;
				
			default:
				destroyable = true;
				break;
		}
	
	}

	public int getX(){return this.pos.getX();}
	public int getY(){return this.pos.getY();}
	public int getWidth(){return this.width;}
	public int getHeight(){return this.height;}
	public boolean isDisplay() {return wall_type != 0;}
	
	public void setDisplay(boolean dis)
	{
		if(!dis) {this.obstacle = null;}
		wall_type = (dis?1:0);
	}
	public boolean isDestroyable() {return destroyable;}
	
	public static int findIndex(int x, int y,int sizeObs)
	{
		if(x% Constantes.GAME_SCALE != 0 || y% Constantes.GAME_SCALE != 0)return -1;
		
		int index = (x*Constantes.HIGHT)/Constantes.GAME_SCALE + y/Constantes.GAME_SCALE;		
		if(index <0 || index >= sizeObs) return -1;
		
		else return index;
	}

	public Obstacle getObstacle() {return obstacle;}
	public void setObstacle(Obstacle obstacle) {this.obstacle = obstacle;}
	public int geType() {return wall_type;}
	
	public void seType(int t) {this.wall_type=t;}


}

