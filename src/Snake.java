import java.util.LinkedList;;

public class Snake
{
	public Snake(Drawer d, Grid g)
	{
		
		this.d = d;
		this.g = g;
		current = new Position();
		current.x = g.getSizeX()/2;
		current.y = g.getSizeY()/2;
		
		posList = new LinkedList<Position>();
		tailLength = 0;
	}
	
	public void draw()
	{
		for( Position p : posList)
		{
			d.draw(p.x, p.y, java.awt.Color.GREEN);
		}
		
		d.draw(current.x, current.y, java.awt.Color.YELLOW);
	}
	
	public int getLength() { return tailLength; }
	
	public boolean move(MoveDir dir)
	{
		Position last = new Position();
		last.x = current.x; last.y = current.y;
		posList.push(last);
		
		switch( dir)
		{
		case UP:
			current.x--;
			break;
		case DOWN:
			current.x++;
			break;
		case LEFT:
			current.y--;
			break;
		case RIGHT:	
			current.y++;
			break;
		case NONE:
			return true;
		}
		
		Grid.Type tp = g.getTypeAt(current.x,current.y);
		if( tp == Grid.Type.WALL )
			return false;
		
		for( Position p : posList)
		{
			if(current.x == p.x && current.y == p.y)
				return false;
		}
		
		if( tp == Grid.Type.APPLE)
		{
			tailLength++;
			g.pickupApple(current.x, current.y);
		}
		
		while(posList.size() > tailLength)
			posList.removeLast();
		
		return true;
	}
	
	public class Position
	{
		public int x;
		public int y;
	}
	
	public enum MoveDir
	{
		UP, DOWN, LEFT, RIGHT, NONE
	}
	
	private Position current;
	private Grid g;
	private Drawer d;
	private LinkedList<Position> posList;
	private int tailLength;
}