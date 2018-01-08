// Mohid Makhdoomi
// makhdoom
public class ShipT {
	private PointT start;
	private PointT end;
	private int length;

	public ShipT(PointT one, PointT two, int length) throws InvalidShipException {
		if ((length < Constants.MIN_SIZE) || (length > Constants.MAX_SIZE))
			throw new InvalidShipException();
		if (one.dist(two) != length)
			throw new InvalidShipException();
		if ((one.xcrd() != two.xcrd()) && (one.ycrd() != two.ycrd()))
			throw new InvalidShipException();

		this.length = length;
		this.start = one;
		this.end = two;
	}

	public PointT startPoint() {
		return this.start;
	}

	public PointT endPoint() {
		return this.end;
	}

	public int shipSize() {
		return this.length;
	}
}
