package Model;

import java.util.List;


public class Personage
{
	
	private State state;
	private Position pos;
	private final int WIDTH = Constantes.GAME_SCALE-2;
	private boolean is_active;
	private List<Map> walls;
	private Direction direction;
	private int stepDown;
	private boolean isMoved;
	private boolean isPushed;
	
	public boolean isPushed() {return isPushed;}
	
	public void setPushed(){this.isPushed = true;}

	public Personage(int x, int y, List<Map> w) 
	{
		is_active = true;
		isPushed = false;
		walls = w;
		pos = new Position(x, y);
		isMoved = false;
		if(System.nanoTime()%2 == 0) {direction = Direction.Right;}
		else {direction = Direction.Left;}
		state = new State(w,this);
	}
	
	public Direction getDirection() {return direction;}
	public int getX(){return pos.getX();}
	public int getY(){return pos.getY();}
	public void setStepDown(int s){stepDown=s;}
	public void incStepDown() {stepDown++;}
	public void switchDirection(){direction = (direction == Direction.Left)?Direction.Right:Direction.Left;}
	public int getWidth() {return WIDTH;}
	public JobState getJobState() {return state.getJS();}
	public void setJobState(JobState j){state.setJob(j);}
	public boolean isMoved() {return isMoved;}
	public void setMoved(boolean isMoved) {this.isMoved = isMoved;}
	public State getState() { return state;}

	public void work() 
	{
		if(state.getJS() != JobState.MARCHEUR) 
		{
			if(!state.execute_job()) {state.setJob(JobState.MARCHEUR);}
		}
	}
	
	public boolean isActive()
	{
		if(stepDown >= 5) {is_active = false;}
		return is_active;
	}
	
	public void deactivate() {is_active = false;}
	
	private boolean isFree(int index) 
	{
		if(index == -1) {return false;}
		else 
		{
			if(walls.get(index).isDisplay()) return false;
			else return true;
		}
	}
	
	public void moveRight() {pos.setPosition(getX()+Constantes.GAME_SCALE, getY());}
	public void moveLeft() {pos.setPosition(getX()-Constantes.GAME_SCALE, getY());}
	public void moveDown(boolean escalier)
	{
		if(escalier)
		{
			if(direction == Direction.Right)
			{
				if(isFree(Map.findIndex(getX()+Constantes.GAME_SCALE, getY()+Constantes.GAME_SCALE, walls.size())))
				{
					pos.setPosition(getX()+Constantes.GAME_SCALE, getY()+Constantes.GAME_SCALE);
				}
			}else 
			{
				if(isFree(Map.findIndex(getX()-Constantes.GAME_SCALE, getY()+Constantes.GAME_SCALE, walls.size())))
				{
					pos.setPosition(getX()-Constantes.GAME_SCALE, getY()+Constantes.GAME_SCALE);
				}
			}
		}else
		{
			if(isFree(Map.findIndex(getX(), getY()+Constantes.GAME_SCALE, walls.size())))
			{
				pos.setPosition(getX(), getY()+Constantes.GAME_SCALE);
			}
		}	
	}
	public void moveUp( boolean escalier) 
	{
		if(escalier) 
		{
			if(direction == Direction.Right)
			{
				if(isFree(Map.findIndex(getX()+Constantes.GAME_SCALE, getY()-Constantes.GAME_SCALE, walls.size())))
				{
					pos.setPosition(getX()+Constantes.GAME_SCALE, getY()-Constantes.GAME_SCALE);
				}
			}else 
			{
				if(isFree(Map.findIndex(getX()-Constantes.GAME_SCALE, getY()-Constantes.GAME_SCALE, walls.size())))
				{
					pos.setPosition(getX()-Constantes.GAME_SCALE, getY()-Constantes.GAME_SCALE);
				}
			}
		}else 
		{
			if(isFree(Map.findIndex(getX(), getY()-Constantes.GAME_SCALE, walls.size())))
			{
				pos.setPosition(getX(), getY()-Constantes.GAME_SCALE);
			}
		}
	}
	public boolean isOnFire() {
		int index = Map.findIndex(getX(), getY()+Constantes.GAME_SCALE,walls.size());
		return (index != -1 && walls.get(index).getObstacle()!=null && walls.get(index).getObstacle().getType() == 3 );
	}
	
	public boolean isOnTeleportation() 
	{
		int index = Map.findIndex(getX(), getY()+Constantes.GAME_SCALE,walls.size());
		return (index != -1 && walls.get(index).getObstacle()!=null && walls.get(index).getObstacle().getType() == 7 );
	}
	public boolean isOnSortie()
	{
		int index = Map.findIndex(getX(), getY()+Constantes.GAME_SCALE,walls.size());
		return (index != -1 && walls.get(index).geType() == 5 );
	}
	public void setPosition(Position p) {pos = p;}

	public void motion()
	{
		if( isPushed() &&  isActive()) 
		{
			 work();
			if( isMoved()) {return;}
			
			if(!isOnSupport()){ moveDown(false);  incStepDown();}
			else {
				switch (getDirection()) 
				{
					case Right:
						 setStepDown(0);
						if(isFree(Map.findIndex( getX()+Constantes.GAME_SCALE,  getY(), walls.size())))
						{
							if(isFree(Map.findIndex( getX()+Constantes.GAME_SCALE,  getY()+Constantes.GAME_SCALE, walls.size()))) 
							{
								 moveDown(true);
							}else { moveRight();}
						}else 
						{
							if(isFree(Map.findIndex( getX()+Constantes.GAME_SCALE,  getY()-Constantes.GAME_SCALE, walls.size()))
									&& isFree(Map.findIndex( getX(),  getY()-Constantes.GAME_SCALE, walls.size()))) 
							{
								 moveUp(true);
							}else { switchDirection();}
						}
						break;
					case Left:
						 setStepDown(0);
						if(isFree(Map.findIndex( getX()-Constantes.GAME_SCALE,  getY(), walls.size())))
						{ 
							if(isFree(Map.findIndex( getX()-Constantes.GAME_SCALE,  getY()+Constantes.GAME_SCALE, walls.size()))) 
							{
								 moveDown(true);
							}else { moveLeft();}
						}else 
						{
							if(isFree(Map.findIndex( getX()-Constantes.GAME_SCALE,  getY()-Constantes.GAME_SCALE, walls.size()))&&
									isFree(Map.findIndex( getX(),  getY()-Constantes.GAME_SCALE, walls.size()))) 
							{
								 moveUp(true);
							}else { switchDirection();}
						}
		
					default: break;
				}
				 setMoved(true);
			}
		}	
	}
	
	private boolean isOnSupport()
	{
		int ret =Map.findIndex(getX(),getY()+Constantes.GAME_SCALE, walls.size());
		if( ret != -1 && !walls.get(ret).isDisplay()) 
		{
			return false;
		}else return true;
	}
}
