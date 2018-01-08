// Mohid Makhdoomi
// makhdoom
public class Battleship {
	private static GameStateT player1;
	private static GameStateT player2;
	private static PointT[] shotList;
	private static boolean player1turn;

	public static void init(ShipT[] player1ships, ShipT[] player2ships) {
		player1 = new GameStateT(player1ships);
		player2 = new GameStateT(player2ships);
		shotList = new PointT[0];
		player1turn = true;
	}

	public static PointT[] get_shots() {
		return shotList;
	}

	public static boolean add_shot(PointT p) throws InvalidMoveException {
		if (player1turn) {
			for (int i = 0; i < shotList.length; i = i + 2) {
				if (samePoint(p, shotList[i]))
					throw new InvalidMoveException();
			}
		} else if (!player1turn) {
			for (int i = 1; i < shotList.length; i = i + 2) {
				if (samePoint(p, shotList[i]))
					throw new InvalidMoveException();
			}
		}

		if (player1turn) {
			return player2.is_hit(p);
		} else if (!player1turn) {
			return player1.is_hit(p);
		}

		PointT[] temp = new PointT[shotList.length + 1];
		for (int i = 0; i < shotList.length; i++) {
			temp[i] = shotList[i];
		}
		temp[temp.length - 1] = p;
		shotList = temp.clone();
		player1turn = !player1turn;

		return false;
	}

	public static boolean has_won() {
		if (player1turn) {
			return player2.is_all_sunk();
		} else if (!player1turn) {
			return player1.is_all_sunk();
		}
		return false;
	}

	private static boolean samePoint(PointT p1, PointT p2) {
		return ((p1.xcrd() == p2.xcrd()) && (p1.ycrd() == p2.ycrd()));
	}
}
