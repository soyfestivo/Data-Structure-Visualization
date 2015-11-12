//
//Stephen Selke, Denis Radinski, Oliver Panasewicz, Jonathan Galvan
//Project 3, Circular Linked List Visualization
//CS 342
//

//Class for scaling/resizing the elements on the application
public class ScaledPoint 
{
	private static int realWidth = 0;	//int for width of frame
	private static int realHeight = 0;	//int for height of frame

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