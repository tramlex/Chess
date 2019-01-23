
public abstract class AbstractPiece {

	boolean isWhite;

	/**
	 * Если фигура белого цвета то true, иначе false
	 */
	public AbstractPiece(boolean isWhite) {
		this.isWhite = isWhite;
	}

	/**
	 * Возвращает true, если цвет белый, false если нет
	 */
	public boolean isWhite() {
		return isWhite;
	}

	/**
	 * Рисует данный кусок в консоль
	 */
	public abstract void draw();

	/**
	 * Проверяет, является ли данный ход допустимым. Возвращает true, если допустимо, 
	 * false если нет
	 */
	public abstract boolean isMoveValid(int srcRow, int srcCol, int destRow,
			int destCol);

	/**
	 * Возвращает относительное значение шахматной фигуры этого шахматиста.
	 */
	public abstract int relativeValue();

}
