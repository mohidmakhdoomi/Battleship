// Mohid Makhdoomi
// makhdoom
import java.util.ArrayList;

public class GameStateT {
	public ShipT[] ships;
	public int[] hits;

	public GameStateT(ShipT[] shipList) throws InvalidShipListException {
		if (!(shipList.length == 5) || !(shipList[0].shipSize() == 2) || !(shipList[1].shipSize() == 3)
				|| !(shipList[2].shipSize() == 3) || !(shipList[3].shipSize() == 4) || !(shipList[4].shipSize() == 5))
			throw new InvalidShipListException();

		for (int i = 0; i < shipList.length; i++) {
			for (int j = 0; j < shipList.length; j++) {
				if (i != j) {
					if (collision(shipList[i], shipList[j]))
						throw new InvalidShipListException();
				}
			}
		}
		this.ships = shipList;
		hits = new int[5];
		for (int i = 0; i < hits.length; i++) {
			hits[i] = 0;
		}
	}

	public boolean is_hit(PointT p) {
		int i;
		for (i = 0; i < ships.length; i++) {
			if (pointInLine(p, ships[i].startPoint(), ships[i].endPoint())) {
				break;
			}
		}
		if (hitCheck(this.ships, p)) {
			this.hits[i]++;
		}
		return hitCheck(ships, p);
	}

	public boolean is_all_sunk() {
		for (int i = 0; i < ships.length; i++) {
			if (ships[i].shipSize() != hits[i])
				return false;
		}
		return true;
	}

	private boolean hitCheck(ShipT[] shipList, PointT p) {
		for (ShipT s : shipList) {
			if (pointInLine(p, s.startPoint(), s.endPoint())) {
				return true;
			}
		}
		return false;
	}

	private boolean collision(ShipT one, ShipT two) {
		ArrayList<PointT> all = new ArrayList<PointT>();

		for (int i = 0; i < Constants.MAX_GRID; i++) {
			for (int j = 0; j < Constants.MAX_GRID; j++) {
				all.add(new PointT(i, j));
			}
		}

		for (PointT i : all) {
			if (pointInLine(i, one.startPoint(), one.endPoint())) {
				if (pointInLine(i, two.startPoint(), two.endPoint())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean pointInLine(PointT p, PointT start, PointT end) {
		return (start.dist(p) + end.dist(p)) == start.dist(end);
	}
}
