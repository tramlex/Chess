
public class Rook extends AbstractPiece {

	public Rook(boolean isWhite) {
		super(isWhite);
	}

	@Override
	public void draw() {
		if (isWhite){
			System.out.print("rw");
		}
		else{
			System.out.print("rb");
		}		
	}
	
	private static Boolean straightPath(int srcRow, int srcCol,
			int destRow, int destCol) {
		// возвращает true, если путь прямой
		// Значения - начальная и конечная координаты хода на шахматной доске.

		return !((srcRow != destRow) && (srcCol != destCol));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return straightPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		return 5;
	}

}
