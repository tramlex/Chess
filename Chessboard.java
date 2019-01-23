
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;


public class Chessboard {

	private Boolean gameRunning;
	private AbstractPiece[][] chessboard = new AbstractPiece[numOfRowsAndCols][numOfRowsAndCols];// [row][column]
	Scanner userInput = new Scanner(System.in);
	private static final int numOfRowsAndCols = 8;
	private int srcRow, srcCol, destRow, destCol;
	private int whiteScore = 0, blackScore = 0;
	private Boolean whitesTurnToMove;
	// Установите в значение true, если перемещение недопустимо. Запрашивает ввод пользователя снова в move ()
	// метод.
	private Boolean invalidMove = false;
	// Содержит строку с пользовательским вводом для инструкций перемещения
	String move;



	public Chessboard() { //Создает шахматную доску и наполняет ее фигурами.

		initialiseBoard(chessboard);
		gameRunning = true;

	}

	/*
	 *Вызывает Boolean gameRunning , если он не работает , то
	 * перестает вызывать move() и printBoard() и закрывает Chessboard()
	 * 
	 * @return  Boolean который false если юзер хочет выйти из 
	 *         gameRunning
	 */
	public Boolean isGameRunning() {
		return this.gameRunning;
	}

	/**
	 * Заполняет шахматную доску AbstractPiece правильными фигурами и
	 * случайным образом определяет, движутся ли белые или черные первыми
	 */
	private void initialiseBoard(AbstractPiece[][] chessboard) {
		// шахматная доска с матрицей 8х8
		// строки [0] и [1] черные
		// строки [6] и [7] белые

		for (int row = 0; row < chessboard.length; row++) {
			for (int col = 0; col < chessboard[row].length; col++) {
				if (row == 0) {
					switch (col) {
					case 0:
						chessboard[row][col] = new Rook(false);
						break;
					case 1:
						chessboard[row][col] = new Knight(false);
						break;
					case 2:
						chessboard[row][col] = new Bishop(false);
						break;
					case 3:
						chessboard[row][col] = new Queen(false);
						break;
					case 4:
						chessboard[row][col] = new King(false);
						break;
					case 5:
						chessboard[row][col] = new Bishop(false);
						break;
					case 6:
						chessboard[row][col] = new Knight(false);
						break;
					case 7:
						chessboard[row][col] = new Rook(false);
						break;
					}
				} else if (row == 1) {
					chessboard[row][col] = new Pawn(false);
				} else if (row == 6) {
					chessboard[row][col] = new Pawn(true);
				} else if (row == 7) {
					switch (col) {
					case 0:
						chessboard[row][col] = new Rook(true);
						break;
					case 1:
						chessboard[row][col] = new Knight(true);
						break;
					case 2:
						chessboard[row][col] = new Bishop(true);
						break;
					case 3:
						chessboard[row][col] = new Queen(true);
						break;
					case 4:
						chessboard[row][col] = new King(true);
						break;
					case 5:
						chessboard[row][col] = new Bishop(true);
						break;
					case 6:
						chessboard[row][col] = new Knight(true);
						break;
					case 7:
						chessboard[row][col] = new Rook(true);
						break;
					}
				} else {
					chessboard[row][col] = null;
				}
			}
		}

		// Случайно выбирается , кто начинает первым (черные или белые)
		Random rand = new Random();
		whitesTurnToMove = rand.nextBoolean();

	}

	/**
	 * Пишет каждый символ в консоль , используя draw ()
	 * метод из класса соответствующего фигуре с последующими вкладками
	 * Печатает числа 1-8 рядом со строками и буквы a-h рядом со столбцами
	 */
	public void printBoard() {

		// Берется массив 8х8 (шахматные фигуры),
		// то есть шахматная доска, как аргумент

		System.out.println("\ta\tb\tc\td\te\tf\tg\th");
		for (int row = 0; row < chessboard.length; row++) {
			System.out.print(8 - row + ".\t");
			for (int col = 0; col < chessboard[row].length; col++) {
				if (chessboard[row][col] != null) {
					chessboard[row][col].draw();
					System.out.print("\t");

				} else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Проверка в 2 шага. Шаг 1:  общая проверки правил,
	 * которому подчиняется любой кусок. 
	 * Шаг 2: метод isMoveValid ()
	 * , который проверяет правила, специфичные для этой фигуры, например, что
	 * ладья движется по прямой линии.
	 */

	public boolean moveValid() {

		// недействителен, если фигура выходит за край

		if (srcRow < 0 || srcRow > 7 || srcCol < 0 || srcCol > 7 || destRow < 0
				|| destRow > 7 || destCol < 0 || destCol > 7) {
			//System.out.println("Move is outside the board");
			return false;
		}

		// Неверно, если клетка пустая
		if (chessboard[srcRow][srcCol] == null) {
			//System.err.println("Origin is empty");
			return false;
		}

		// Неверно , если игрок двигается, когда не его ход
		if ((chessboard[srcRow][srcCol].isWhite && !whitesTurnToMove)
				|| (!chessboard[srcRow][srcCol].isWhite && whitesTurnToMove)) {
			//System.err.println("It's not your turn");
			return false;
		}

		// вернуть false, если не соблюдаются определенные правила
		if (!chessboard[srcRow][srcCol].isMoveValid(srcRow, srcCol, destRow,
				destCol)) {
			//System.err.println("This piece doesn't move like that");
			return false;
		}

		// это утверждение останавливает утверждение для проверки, если белые едят
		// белых isWhite ()
		if (chessboard[destRow][destCol] == null) {
			return true;
		}

		// Неверно, если белые едят белых
		if (chessboard[srcRow][srcCol].isWhite
				&& chessboard[destRow][destCol].isWhite) {
			//System.err.println("White cannot land on white");
			return false;
		}

		// Неверно , если черные едят черных
		if (!chessboard[srcRow][srcCol].isWhite
				&& !chessboard[destRow][destCol].isWhite) {
			//System.err.println("Black cannot land on black");
			return false;
		}

		return true;

	}

	/**
	 * Обновление счета
	 */
	private void updateScore() {
		if (chessboard[destRow][destCol] == null) {
			return;
		}
		if (whitesTurnToMove) {
			whiteScore += chessboard[destRow][destCol].relativeValue();
		} else {
			blackScore += chessboard[destRow][destCol].relativeValue();

		}
	}

	/**
	 * Считывает ввод с клавиатуры в виде 
	 * ("начальные координаты" to "онечные координаты"), например, "d2 в d3" и преобразует
	 * строку в координаты для массива шахматной доски. Проверяет, если движение
	 * допустимо с помощью moveValid (). Если допустимо, перемещает фигуру к месту назначения.
	 * Оценка шахматной доски и обновление updateScore (). Если неверно печатается ошибка
	 * и рекурсивно вызывается.
	 */
	public void move() {

		System.out
				.println("___________________________________________________\n"
						+ "Score: White "
						+ whiteScore
						+ " | "
						+ blackScore
						+ " Black");

		if (invalidMove) {
			System.err.println("Move is invalid. Please try again:");
			invalidMove = false;
		}

		else if (whitesTurnToMove) {
			System.out
					.println("___________________________________________________\n"
							+ "White's turn to move\n"
							+ "___________________________________________________\n");
		} else {
			System.out
					.println("___________________________________________________\n"
							+ "Black's turn to move\n"
							+ "___________________________________________________\n");
		}

		move = userInput.nextLine();

		if (move.equalsIgnoreCase("exit")) {
			gameRunning = false;
			System.out.println("Thanks for playing.");
			return;
		}

		// преобразование в нижний регистр
		String lowerCase = move.toLowerCase();
		// расщепление строки на компоненты
		String[] components = lowerCase.split(" ");

		// если вдвижение будет "e1 to e5" тогда
		// components[0].charAt(0) = 'e'
		// components[0].charAt (1) = '1'

		// используйте символы в components, чтобы установить координаты массива
		// переместить начало и переместить конец назначения

		/*srcRow = 7 - (components[0].charAt(1) - '1');
		srcCol = components[0].charAt(0) - 'a';
		destRow = 7 - (components[2].charAt(1) - '1');
		destCol = components[2].charAt(0) - 'a';
		*/	
		
		while(moveValid()!=true){
			Random rand = new Random();
			srcCol = rand.nextInt(8);
			srcRow = rand.nextInt(8);
			destRow = rand.nextInt(8);
			destCol = rand.nextInt(8);
		}
		
		updateScore();
		chessboard[destRow][destCol] = chessboard[srcRow][srcCol];
		chessboard[srcRow][srcCol] = null;
		whitesTurnToMove = !whitesTurnToMove;
		
		/*if (moveValid()) {
			updateScore();
			// положить фигуру в пункт назначения
			chessboard[destRow][destCol] = chessboard[srcRow][srcCol];
			// перезапуск move()
			chessboard[srcRow][srcCol] = null;
			whitesTurnToMove = !whitesTurnToMove;
		} else {
			invalidMove = true;
			move();

		}*/

	}

}
