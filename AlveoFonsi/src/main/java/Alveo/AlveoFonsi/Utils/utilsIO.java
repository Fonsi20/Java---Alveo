package Alveo.AlveoFonsi.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class utilsIO {

	static String matrix[][];
	static String[] ourdataInLines = new String[] {};

	public static String[][] readData(String dataFile) throws FileNotFoundException, IOException {
		try {
			int lenghtRow = lenghtColumn(dataFile);
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
			String line = br.readLine();
			int lenghtColumn = lenghtRow(line);
			
			matrix = new String[lenghtRow][lenghtColumn];
			
			int row = 0; 
			while(line != null) {
				String[] value = line.split("	");
				for (int i = 0; i < lenghtColumn; i++)
					matrix[row][i] = value[i];
 
				row++; 
				line = br.readLine();
			}
			br.close();
 
			Alveo.AlveoFonsi.Utils.utils.showMatrix(lenghtRow,lenghtColumn, matrix);
			
		} catch (FileNotFoundException e) {
			System.out.println(constants.errorFile);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(constants.errorCast);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(constants.errorWorkingWithFile);
			e.printStackTrace();
		}
		return matrix;
 
	}
	
	public static void writteData(String[][] matrix) {
		System.out.println("\n\nPrinting the result - Columns");
	    createFile();
	    try {
	        FileWriter myWriter = new FileWriter(constants.resultFile);
	        for (String[] strings : matrix) {
	        	for (String strings2 : strings) {
	        		myWriter.write(strings2+ "	");
				}
			}
	        myWriter.close();
	        System.out.println(constants.msgSuccessfully);
	      } catch (IOException e) {
	        System.out.println(constants.errorGeneric);
	        e.printStackTrace();
	      }
	  }

	private static void createFile() {
		try {
	      File myObj = new File("result.txt");
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	      } else {
	        System.out.println(constants.warningFileExist);
	      }
	    } catch (IOException e) {
	      System.out.println("");
	      e.printStackTrace();
	    }
	}


	private static int lenghtRow(String line) {
		ourdataInLines = line.split("	");
		int lenghtRow = ourdataInLines.length;
		return lenghtRow;
	}


	private static int lenghtColumn(String dataFile) throws FileNotFoundException, IOException {
		BufferedReader b = new BufferedReader(new FileReader(dataFile));
		int lenghtColumn = 0;
		while ((b.readLine())!=null) {
			lenghtColumn++;
		}
		b.close();
		return lenghtColumn;
	}	


	public static String[][] columnsThatWeWant(String[][] matrixData) throws FileNotFoundException, IOException {
		System.out.println("\n\nCongif document - Columns");
		String[][] matrixColumn = readData(Alveo.AlveoFonsi.Utils.constants.ruteConfig);
		return translateColumns(matrixColumn, matrixData);
	}


	private static String[][] translateColumns(String[][] matrixColumn, String[][] matrixData) {
	    List<String> result = new LinkedList<String>();
		
		for (String[] item : matrixColumn) {
			for(int row = 0; row < matrixData[0].length; row++) {
			    if(matrixData[0][row].equals(item[0])) {
			    	matrixData[0][row]=item[1];
			    	result.add(matrixData[0][row]);
			    }
			}
		}		
		return obtainNewMatrix(result,matrixData);
	}


	private static String[][] obtainNewMatrix(List<String> result, String[][] matrixData) {
		int lenghtRow =result.size();
		int lenghtColumn = matrixData[0].length;
		matrix = new String[lenghtRow][ matrixData.length];

		for(int row = 0; row < lenghtRow; row++) {
			for(int column = 0; column < lenghtColumn ; column++) {
				for (String results : result) {
					if(matrixData[0][column].equals(results)) {
						matrix[row][result.indexOf(results)]= matrixData[row][column];
					}					
				}
			}
		}
		return matrix;
	}


	public static String[][] rowsThatWeWant(String[][] matrixData) throws FileNotFoundException, IOException {
		System.out.println("\n\nCongif ID document - Rows");
		String[][] matrixRow = readData(Alveo.AlveoFonsi.Utils.constants.ruteConfigId);
		return translateRows(matrixRow, matrixData);		
	}


	private static String[][] translateRows(String[][] matrixRow, String[][] matrixData) {
		 List<String> result = new LinkedList<String>();
			
			for (String[] item : matrixRow) {
				for(int row = 0; row < matrixData[0].length; row++) {
				    if(matrixData[row][0].equals(item[0])) {
				    	matrixData[row][0]=item[1];
				    	result.add(matrixData[row][0]);
				    }
				}
			}		
			return obtainNewMatrixRows(result,matrixData);
	}
	
	
	private static String[][] obtainNewMatrixRows(List<String> result, String[][] matrixData) {
		int lenghtRow = matrixData.length;
		int lenghtColumn = matrixData[0].length;
		matrix = new String[result.size()][ matrixData.length];
		int x=0;
		
		for (String results : result) {
			for(int row = 0; row < lenghtRow; row++) {
				x = 0;
				for(int column = 0; column < lenghtColumn ; column++) {
					if(matrixData[row][0].equals(results)) {
						matrix[0][x]= matrixData[row][column];
						x++;
					}					
				}
			}	
		}
		return matrix;
	}
}
