
public class Bishop extends AbstractPiece {

	public Bishop(boolean isWhite) {
		super(isWhite);
	}

	@Override
	public void draw() {
		if (isWhite) {
			System.out.print("bw");
		} else {
			System.out.print("bb");
		}
	}

	private static Boolean diagonalPath(int srcRow, int srcCol,
			int destRow, int destCol) {
		// возвращает true, если путь диагональный
		// Значения это начальные и конечные координаты хода
		// в массиве шахматной доски. 
		return ((Math.abs(srcRow - destRow) == Math.abs(srcCol - destCol)));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return diagonalPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		return 3;
	}

}
