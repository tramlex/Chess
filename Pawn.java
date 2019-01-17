
public class Pawn extends AbstractPiece {

	public Pawn(boolean isWhite) {
		super(isWhite);
	}

	@Override
	public void draw() {
		if (this.isWhite) {
			System.out.print("pw");
		}
		if (!(this.isWhite)) {
			System.out.print("pb");

		}

	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
			
			//если пешка движется вперед
			//или двинулась вперед на две позиции от стартовой
			//или съедает черную
			//стоп, или return false (недопустимое движение)
			if (this.isWhite) {
				return (((srcCol == destCol) && srcRow == (destRow + 1))
						|| ((srcRow == 6) && (srcCol == destCol) && (srcRow == (destRow + 2)))
						|| ((srcRow == (destRow + 1))
								&& (Math.abs(srcCol - destCol) == 1)));
			}
			else {
				return (((srcCol == destCol) && srcRow == (destRow - 1))
						|| ((srcRow == 1) && (srcCol == destCol) && (srcRow == (destRow - 2)))
						|| ((srcRow == (destRow - 1))
								&& (Math.abs(srcCol - destCol) == 1)));
			}
			


	}

	@Override
	public int relativeValue() {
		return 1;
	}

}
