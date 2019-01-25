import java.util.Scanner;
import org.apache.commons.math3.analysis.function.*;

public class Controller {
	
	static Scanner userInput = new Scanner(System.in);

	
	public static void main(String[] args) {
		Chessboard myChessboard = new Chessboard();

		Cos cos = new Cos();		
		System.out.println(cos.value(0));
		
		while (myChessboard.isGameRunning()) {

			myChessboard.printBoard();
			myChessboard.move();

		}
	}

}
