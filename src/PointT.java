// Mohid Makhdoomi
// makhdoom
public class PointT {
	private int xc;
	private int yc;

	public PointT(int x, int y) throws InvalidPointException {
		if (!(0 <= x) || !(x <= Constants.MAX_GRID) || !(0 <= y) || !(y <= Constants.MAX_GRID))
			throw new InvalidPointException();
		this.xc = x;
		this.yc = y;
	}

	public int xcrd() {
		return this.xc;
	}

	public int ycrd() {
		return this.yc;
	}

	public double dist(PointT p) {
		return Math.sqrt(Math.pow(this.xc - p.xcrd(), 2) + Math.pow(this.yc - p.ycrd(), 2));
	}
}
