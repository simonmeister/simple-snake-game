import java.util.Random;

public class Grid
{
	public Grid(int sx, int sy, Drawer d)
	{
		fieldArray = new Type[sx][sy];
		this.d = d;
		x = sx;
		y = sy;
		for(int i = 0; i < sx; ++i)
		{
			for(int j = 0; j < sy; ++j)
			{
				if(i == 0 || j == 0 || i == sx-1 || j == sy-1)
					fieldArray[i][j] = Type.WALL;
				else	
					fieldArray[i][j] = Type.FREE;
			}
		}
		appleCount = 0;
		generator = new Random();
	}
	
	public void spawnApple()
	{
		while( true )
		{
			int i = generator.nextInt(x); //Zahl von 0 bis size-1
			int j = generator.nextInt(y);
			
			if(fieldArray[i][j] == Type.FREE)
			{
				fieldArray[i][j] = Type.APPLE;
				++appleCount;
				break;			
			}
				
		}
	}
	
	public boolean pickupApple(int px,int py)
	{
		if( fieldArray[px][py] == Type.APPLE)
		{
			--appleCount;
			fieldArray[px][py] = Type.FREE;
			return true;
		}
		return false;
	}
	
	public boolean hasApple() { return appleCount > 0;}
	
	public Type getTypeAt(int px, int py)
	{
		return fieldArray[px][py];
	}
	
	public int getSizeX() { return x; }
	public int getSizeY() { return y; }
	
	public void draw()
	{
		for(int i = 0; i < x; ++i)
		{
			for(int j = 0; j < y; ++j)
			{
				if( fieldArray[i][j] == Type.FREE)
					d.draw(i,j,java.awt.Color.BLACK);
				else if( fieldArray[i][j] == Type.WALL)
					d.draw(i,j,java.awt.Color.GRAY);
				else
					d.draw(i,j,java.awt.Color.RED);
				
			}
		}
	}
	
	public enum Type
	{
		WALL,APPLE,FREE
	}
	
	private int x,y;
	private int appleCount;
	private Type fieldArray[][];
	private Drawer d;
	private Random generator;
}