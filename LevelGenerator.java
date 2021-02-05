package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class LevelGenerator 
{
	public int map[][];			// 2D array for creating bricks.
	
	public int row;
	public int col;
	public int brickWidth;	
	public int brickHeight;
	public int level;
	public int totalBricks = 0;
	
	public LevelGenerator (int row, int col)
	{
		map = new int[row][col];
		this.row = row;
		this.col = col;
	}
	public void createMap(int level) {
		this.level = level;
		this.destroy();
		for(int i = 0; i<row; i++)
		{
			for(int j =0; j<col; j++)
			{
				switch(level)
				{
				case 1:
					
					map[i][j] = 1;
					break;
					
				case 2:
					
					if(j%2==0)
					{
						map[i][j] = 1;
					}
					else
						map[i][j] = 0;
					break;
				case 3:
					
					if(((i+j >= 3) && (i+j <= 5)) || ((i+j >=9) && (i+j <= 11)))
					{
						map[i][j] = 1;
					}
					else
						map[i][j] = 0;
					break;
				case 4:
					
					if(i%2 == 0 && j%2 != 0)
					{
						map[i][j] = 1;
					}
					if(i%2 !=0 && j%2 == 0)
					{
						map[i][j] = 1;
					}
					else
						map[i][j] = 0;
					break;
				case 5:
					
					if((i%2 == 0 && j%2==0) || (i%2 !=0 && j%2 !=0))
					{
						map[i][j] = 1;
					}
					else 
					{
						map[i][j] = 0;
					}
					break;
					

				}
				if(map[i][j] == 1)
				{
					totalBricks ++; 
				}
					
			}	
		}
		
		brickWidth = 540/col;		//adjusting width and height as per the row column.
		brickHeight = 100/row;
	}
	
	public void draw(Graphics2D g)		//draw method to draw the bricks.
	{
		for(int i = 0; i<row; i++)
		{
			for(int j =0; j<col; j++)
			{
				if(map[i][j] > 0)			//checking bricks values to be 1;
				{
					g.setColor(Color.cyan);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					// adding borders to differentiate bricks.
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);				
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col)
	{
		map[row][col] = value;		// change value to 0 after collision to disappear the brick.
	}
	
	public void destroy() {
		for(int i = 0; i<row; i++)
		{
			for(int j =0; j<col; j++)
			{
				this.map[i][j] = 0;
			}	
		}
		totalBricks = 0;
		
	}
	
}
