//Class for scaling/resizing the elements on the application

public class ScaledPoint 
{
	private static int realWidth = 0;	//int for width of frame
	private static int realHeight = 0;	//int for height of frame

	//default constructor 
	/*public static ScaledPoint() 
	{
		realWidth = 0;		//set width to 0	
		realHeight = 0;		//set height to 0
	}*/

	/*public static ScaledPoint(int width, int height)
	//PRE: width and height are positive values 
	{
		realWidth = width;		//set width to frame width
		realHeight = height;	//set height to frame height
	}*/

	public static void setSize(int width, int height) 
	//PRE: width and height are positive values
	{
		realWidth = width;		//set frame width 
		realHeight = height;	//set frame height
	}

	public static int[] getRealCoordinance(double x, double y) 
	//PRE: x and y are positive values 
	//POST: FCTVAL = integer array holding real x and y coordinates
	{
		double wOffset = (double) (realWidth / 2);
		double hOffset = (double) (realHeight / 2);
		double w = wOffset + (wOffset * x);
		double h = hOffset + (hOffset * y);
		return new int[] {(int) w, (int) h}; 	//return array of coordinates
	}

	public static double[] getScaledCoordinance(int x, int y) 
	//PRE: x and y are positive values
	//POST: FCTVAL = integer array holding scaled x and y coordinates
	{
		return new double[] {((double) x) / realWidth, ((double) y) / realHeight};	//return array of coordinates
	}
}