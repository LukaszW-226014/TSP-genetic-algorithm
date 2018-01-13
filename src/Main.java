import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Dane do algorytmu:
        int dlugosc_tabu;
        int ilosc_porazek;

        int numOfCities = 0;
        JFileChooser chooser = new JFileChooser("instancje\\");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TXT", "txt");
        chooser.setFileFilter(filter);
        int retval = JFileChooser.APPROVE_OPTION;
        chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES);
        retval = chooser.showDialog(null, "Select");
        File file = chooser.getSelectedFile();

        try {
            LineNumberReader lineCounter = new LineNumberReader(
                    new InputStreamReader(new FileInputStream(file)));
            String nextLine = null;

            try {
                while ((nextLine = lineCounter.readLine()) != null) {
                    if (nextLine == null)
                        break;
                    System.out.println(nextLine);
                }
                numOfCities = lineCounter.getLineNumber();
                //System.out.println("Total number of line in this file " + numOfCities);
            } catch (Exception done) {
                done.printStackTrace();
            }
        }
        catch (FileNotFoundException e){ }

        int [][] n = new int [numOfCities][numOfCities];
        try {
            System.out.println("Wybrales plik: " + file.toString());
            Scanner scan = new Scanner(file);

            for (int i = 0; i < numOfCities; i++){
                for (int j = 0; j < numOfCities; j++){
                    n[i][j] = scan.nextInt();
                }
            }
            Matrix matrix = new Matrix(n);
//            TabuSearch tabuSearch = new TabuSearch(matrix);
            Timer timer = new Timer();
            timer.start();
//            tabuSearch.search();
            System.out.println("Czas = " + timer.getElapsedTime() + "sec");
        }
        catch (FileNotFoundException e){
            System.out.println("Nie wybrales pliku");
        }
        catch (NullPointerException f){
            System.out.println("Nie wybrales pliku");
        }
    }
}
