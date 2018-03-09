import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

public class Drawer extends JFrame
{
	private static final long serialVersionUID = 1;
	public Drawer(int sx,int sy)
	{
		super();
		setSize(sx*16,sy*16);
		setResizable(false);
		setTitle("Snake Q4BGD-Edition");
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layout = new GridLayout(sx,sy);
		setLayout(layout);
		
		int numComponents = sx * sy;
		for(int i = 0; i < numComponents; ++i)
			add(new JPanel());
		
		resolution = sx;
	}
	
	public void draw(int px, int py, Color clr)
	{
		this.getRootPane().getContentPane().getComponent(py + px*resolution).setBackground(clr);
	}
	
	private GridLayout layout;
	private int resolution;
}