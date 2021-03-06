
public class Queen extends AbstractPiece {

	public Queen(boolean isWhite) {
		super(isWhite);
		
		}

	@Override
	public void draw() {
		if (isWhite){
			System.out.print("qw");
		}
		else{
			System.out.print("qb");
		}
	}

	private static Boolean diagonalPath(int srcRow, int srcCol, int destRow,
			int destCol) {
		// возвращает true, если путь диагональный
		// Значения - начальная и конечная координаты хода на шахматной доске.

		return ((Math.abs(srcRow - destRow) == Math.abs(srcCol - destCol)));
	}

	private static Boolean straightPath(int srcRow, int srcCol, int destRow,
			int destCol) {
		// возвращает true, если путь прямой
		// Значения - начальная и конечная координаты хода на шахматной доске.

		return !((srcRow != destRow) && (srcCol != destCol));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return (diagonalPath(srcRow, srcCol, destRow, destCol))
				|| straightPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		return 9;
	}

}
