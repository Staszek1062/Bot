package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class ReadFile {
    Scanner scan;
    int counter=0,iteration=0;
    List<Integer> readCoordinates;   // Matrix (Width,Height) and space in Stack
    char[] readIndexes;     //Indexes for every space in Matrix
    String[][] itemPlacement;    //Name of item and Coordinates (x,y)
    Grid grid;
    String[] quot;
    List<Integer> temporary;


    public ReadFile(File Data) throws FileNotFoundException {

        scan = new Scanner(new FileReader(Data));
        //                                                  Reading Matrix (Width,Height) and space in Stack
        temporary.clear();
        quot=scan.nextLine().split(" ");
        for(String i:quot)
            temporary.add(Integer.parseInt(i));

        if(iteration==0) ReadGridSetUpFile();
        if(iteration==1) ReadJobFile();
        if(iteration==2)ReadResultFile();
        iteration++;

    }



    private void ReadGridSetUpFile() {
        readCoordinates=temporary;
        counter = 0;
        StringJoiner readStrIndexes = new StringJoiner("");
        //                                                  Reading Strings Of Indexes
        while (counter < readCoordinates.get(1) && scan.hasNextLine()) {
            readStrIndexes.add(scan.nextLine());

            counter++;
        }
        readIndexes= readStrIndexes.toString().toCharArray();
        //                                                  Reading Indexes for every space in Matrix

        counter = 0;
        //                                                  Reading name of item and Coordinates (x,y)
        while (scan.hasNextLine()) {
            itemPlacement[counter] = scan.nextLine().split(" ");
            counter++;
            grid = new Grid(readCoordinates);
            grid.createGrid(readIndexes);
            grid.fillGrid(itemPlacement);
        }
    }
    private void ReadJobFile() {

        String product;
        int[] bot= new int[2];
        int[] station = new int[2];
        int[] productPosition= new int[2];
        bot[0] = temporary.get(0).;
        bot[1] = temporary.get(1);
        quot=scan.nextLine().split(" ");

        station[0] = Integer.parseInt(quot[0]);
        station[1] = Integer.parseInt(quot[1]);
        product=scan.nextLine();
        productPosition=grid.findClosest();
        grid.getShortestDistance(bot,productPosition);
        grid.getShortestDistance(bot,station);

    }
    private void ReadResultFile() {
        List<Integer> positions = null;
        int sum,time;
        sum= Integer.parseInt(quot[0]);
        time=Integer.parseInt(scan.nextLine());

        while(scan.hasNextInt()){
            positions.add(scan.nextInt());
        }
    }


}



