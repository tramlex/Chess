
public class Knight extends AbstractPiece {

	public Knight(boolean isWhite) {
		super(isWhite);
			}

	@Override
	public void draw() {
		if (isWhite){
			System.out.print("kw");
		}
		else{
			System.out.print("kb");
		}		
	}
	
	private static Boolean lShapedPath(int srcRow, int srcCol,
			int destRow, int destCol) {
		// возвращает true, если путь имеет L-образную форму
		// Значения - начальная и конечная координаты хода на шахматной доске.

		return ((Math.abs(srcRow - destRow) == 2 && Math.abs(srcCol
				- destCol) == 1)
				|| (Math.abs(srcRow - destRow) == 1 && Math.abs(srcCol
						- destCol) == 2));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return lShapedPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		return 0;
	}

}
