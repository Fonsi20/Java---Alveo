package Alveo.AlveoFonsi.Utils;

public class utils {

	public static void showMatrix(int lenghtRow, int lenghtColumn, String[][] matrix) {
		for (int i = 0; i < lenghtRow ; i++) {
			for (int j = 0; j < lenghtColumn; j++)
				System.out.print(matrix[i][j] + "	");
			System.out.println();
		}
	}

}
