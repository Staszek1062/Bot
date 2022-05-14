package org.example;

import java.io.*;
import java.util.*;

public class ReadWriteFiles {
    Scanner scan;


    char[] readIndexes;
    String[] itemPlacement;
    String[] quot;

    Grid grid;
    FindingAlgorithm algo;
    ResourceFileReader fileReader;



    /**
     * Takes files and check if results from file are same as results from program.
     * @param myFileGrid The file grid set up.
     * @param myFileBot The file job set up.
     * @param myFileResult The file result check.
     */
    public ReadWriteFiles(File myFileGrid, File myFileBot, File myFileResult)  {

        readGridSetUpFile(myFileGrid);
        readJobFile(myFileBot);
        readResultFile(myFileResult);
    }
    /**
     * Writes result file output.txt
     */
    private void writeResult() throws IOException {
        FileWriter outputResults = new FileWriter("output.txt");
        outputResults.write(algo.results.nodesTraveled()+"\n");
        outputResults.write(algo.results.time()+"\n");

        for(int i = algo.results.nodesIndexesTraveled().size()-1; i>=0; i--) {
            outputResults.write(algo.results.nodesIndexesTraveled().get(i)[0]+" "+ algo.results.nodesIndexesTraveled().get(i)[1]+"\n");
        }

        outputResults.close();
    }

    /**
     * Takes file and setup grid.
     * @param myFile The file grid set up.
     */
    private void readGridSetUpFile(File myFile)  {
        List<String> readLines;
        List<String[]> readItemPlacement= new ArrayList<>();
        List<Integer> readCoordinates= new ArrayList<>();

        StringJoiner readStrIndexes = new StringJoiner("");

        fileReader= new ResourceFileReader();
        itemPlacement = new String[4];
        readLines=fileReader.readLines("grid-1.txt");
        int counter=0;

        quot=readLines.get(counter).split(" ");
        System.out.println(readLines.get(0));
        for(String i:quot) {
            readCoordinates.add(Integer.parseInt(i));
        }
        counter++;
        while (counter <= readCoordinates.get(1) ) {
            System.out.println(readLines.get(counter));
            readStrIndexes.add(readLines.get(counter));
            counter++;
        }
        while (readLines.size()>counter) {
            System.out.println(readLines.get(counter));
            itemPlacement = readLines.get(counter).split(" ", 4);
            counter++;
            readItemPlacement.add(itemPlacement);
        }

        readIndexes= readStrIndexes.toString().toCharArray();

        grid = new Grid(readCoordinates);
        grid.createGrid(readIndexes);
        grid.fillGrid(readItemPlacement);

    }

    /**
     * Takes file and setup bot path.
     * @param myFile The file bot setup.
     */
    private void readJobFile(File myFile)  {

        List<String> readLines=fileReader.readLines("grid-2.txt");
        algo = new FindingAlgorithm(grid);
        String product;
        int[] bot= new int[2];
        int[] station = new int[2];


        quot=readLines.get(0).split(" ");
        bot[0] = Integer.parseInt(quot[0]);
        bot[1] = Integer.parseInt(quot[1]);

        quot=readLines.get(1).split(" ");
        station[0] = Integer.parseInt(quot[0]);
        station[1] = Integer.parseInt(quot[1]);

        product=readLines.get(2);
        algo.findEfficientPath(bot,station,product);

        for(int[] i: algo.results.nodesIndexesTraveled())
            System.out.print("  "+ Arrays.toString(i));


    }
    /**
     * Takes result file read it.
     * @param myFile The file results.
     */
    private void readResultFile(File myFile) {



    }


}



