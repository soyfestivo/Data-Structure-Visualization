public class ScaledPoint {
	private int realWidth;
	private int realHeight;

	public ScaledPoint() {
		realWidth = 0;
		realHeight = 0;
	}

	public ScaledPoint(int width, int height) {
		realWidth = width;
		realHeight = height;
	}

	public void setSize(int width, int height) {
		realWidth = width;
		realHeight = height;
	}

	public int[] getRealCoordinance(int x, int y) {
		return new int[] {realWidth * x, realHeight * y};
	}

	public int[] getScaledCoordinance(int x, int y) {
		return new int[] {x / realWidth, y / realHeight};
	}
}