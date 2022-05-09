package org.example;

import java.io.*;
import java.util.*;

public class ReadWriteFiles {
    Scanner scan;

    int counter=0, typeOfFile =0;
// Matrix (Width,Height) and space in Stack
    char[] readIndexes;     //Indexes for every space in Matrix
    String[] itemPlacement ;    //Name of item and Coordinates (x,y)
    Grid grid;
    FindingAlgorithm algo;
    String[] quot;

    /**
     * Takes files and check if results from file are same as results from program.
     * @param myFileGrid The file grid set up.
     * @param myFileBot The file job set up.
     * @param myFileResult The file result check.
     */
    public ReadWriteFiles(File myFileGrid, File myFileBot, File myFileResult)  {

        ReadGridSetUpFile(myFileGrid);

        ReadJobFile(myFileBot);
        ReadResultFile(myFileResult);
        try {
            WriteResult();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Writes result file output.txt
     */
    private void WriteResult() throws IOException {
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
    private void ReadGridSetUpFile(File myFile)  {
        try{scan = new Scanner(new FileReader(myFile));}
        catch(FileNotFoundException e){
            System.out.println("File1 not found");
        }
        List<Integer> readCoordinates= new ArrayList<>();

        itemPlacement = new String[4];
        List<String[]> readItemPlacement= new ArrayList<>() ;

        quot=scan.nextLine().split(" ");
        for(String i:quot) {
            readCoordinates.add(Integer.parseInt(i));
        }
        StringJoiner readStrIndexes = new StringJoiner("");
        counter=0;
        while (counter < readCoordinates.get(1) && scan.hasNextLine()) {
            readStrIndexes.add(scan.nextLine());
            counter++;
        }
        readIndexes= readStrIndexes.toString().toCharArray();
        counter = 0;
       
        while (scan.hasNextLine()) {

            itemPlacement = scan.nextLine().split(" ", 4);
            counter++;


            readItemPlacement.add(itemPlacement);
        }
        grid = new Grid(readCoordinates);
        grid.createGrid(readIndexes);
        grid.fillGrid(readItemPlacement);
        scan.close();
    }
    /**
     * Takes file and setup bot path.
     * @param myFile The file bot setup.
     */
    private void ReadJobFile(File myFile)  {
        try{scan = new Scanner(new FileReader(myFile));}
        catch(FileNotFoundException e){
            System.out.println("File2 not found");
        }
        algo = new FindingAlgorithm(grid);
        String product;
        int[] bot= new int[2];
        int[] station = new int[2];


        quot=scan.nextLine().split(" ");
        bot[0] = Integer.parseInt(quot[0]);
        bot[1] = Integer.parseInt(quot[1]);

        quot=scan.nextLine().split(" ");
        station[0] = Integer.parseInt(quot[0]);
        station[1] = Integer.parseInt(quot[1]);

        product=scan.nextLine();
        algo.findEfficientPath(bot,station,product);

        System.out.println("Product at:  "+ Arrays.toString(algo.results.productCoords())+"  |   time: "+ algo.results.time()+" lenghtInNodes: "+ algo.results.nodesTraveled());
        for(int[] i: algo.results.nodesIndexesTraveled())
            System.out.print("  "+ Arrays.toString(i));
        scan.close();

    }
    /**
     * Takes result file read it.
     * @param myFile The file results.
     */
    private void ReadResultFile(File myFile) {
        try{scan = new Scanner(new FileReader(myFile));}
        catch(FileNotFoundException e){
            System.out.println("File3 not found");
        }

        scan.close();
    }


}



