package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class ReadFile {
    Scanner scan;
    int counter;
    private final Grid grid;
    List<Integer> readCoordinates;   // Matrix (Width,Height) and space in Stack
    char[] readIndexes;     //Indexes for every space in Matrix
    String readStrIndexes = "";   //Strings Of Indexes
    String[][] itemPlacement;    //Name of item and Coordinates (x,y)

    public ReadFile(File Data) throws FileNotFoundException {

        scan = new Scanner(new FileReader(Data));
        //                                                  Reading Matrix (Width,Height) and space in Stack
        while (scan.hasNextInt()) {
            readCoordinates.add(scan.nextInt());
        }
        counter = 0;
        StringJoiner temp = new StringJoiner("");
        //                                                  Reading Strings Of Indexes
        while (counter < readCoordinates.get(1) && scan.hasNextLine()) {
            temp.add(scan.nextLine());

            counter++;
        }
        readIndexes= temp.toString().toCharArray();
        //                                                  Reading Indexes for every space in Matrix

        counter = 0;
        //                                                  Reading name of item and Coordinates (x,y)
        while (scan.hasNextLine()) {
            itemPlacement[counter] = scan.nextLine().split(" ");
            counter++;
        }
        grid = new Grid(readCoordinates);
        grid.createGrid(readIndexes);
        grid.fillGrid(itemPlacement);
    }

}



