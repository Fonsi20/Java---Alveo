package Alveo.AlveoFonsi;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App 
{
	static String[][] data;
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        System.out.println( Alveo.AlveoFonsi.Utils.constants.welcomeMsg);
        data = Alveo.AlveoFonsi.Utils.utilsIO.readData(Alveo.AlveoFonsi.Utils.constants.ruteData);
        data = Alveo.AlveoFonsi.Utils.utilsIO.columnsThatWeWant(data);
        data = Alveo.AlveoFonsi.Utils.utilsIO.rowsThatWeWant(data);
        Alveo.AlveoFonsi.Utils.utilsIO.writteData(data);
    }
}
