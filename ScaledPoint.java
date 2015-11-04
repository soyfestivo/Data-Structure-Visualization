//Class for scaling/resizing the elements on the application

public class ScaledPoint 
{
	private int realWidth;	//int for width of frame
	private int realHeight;	//int for height of frame

	//default constructor 
	public ScaledPoint() 
	{
		realWidth = 0;		//set width to 0	
		realHeight = 0;		//set height to 0
	}

	public ScaledPoint(int width, int height)
	//PRE: width and height are positive values 
	{
		realWidth = width;		//set width to frame width
		realHeight = height;	//set height to frame height
	}

	public void setSize(int width, int height) 
	//PRE: width and height are positive values
	{
		realWidth = width;		//set frame width 
		realHeight = height;	//set frame height
	}

	public int[] getRealCoordinance(int x, int y) 
	//PRE: x and y are positive values 
	//POST: FCTVAL = integer array holding real x and y coordinates
	{
		return new int[] {realWidth * x, realHeight * y}; 	//return array of coordinates
	}

	public int[] getScaledCoordinance(int x, int y) 
	//PRE: x and y are positive values
	//POST: FCTVAL = integer array holding scaled x and y coordinates
	{
		return new int[] {x / realWidth, y / realHeight};	//return array of coordinates
	}
}