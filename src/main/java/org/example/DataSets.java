package org.example;

import java.io.File;


public class DataSets {
    final String myFileGrid1= "grid-1.txt"; // Specify the filename
    final String myFileBot1 = "job-1.txt"; // Specify the filename
    final String myFileResult1 = "results-1.txt"; // Specify the filename
    final String myFileOutput1 = "output-1.txt";
    //final File myFileGrid2 = new File("grid-2.txt"); // Specify the filename
    //final File myFileBot2 = new File("job-2.txt"); // Specify the filename
    //final File myFileResult2 = new File("results-2.txt"); // Specify the filename
    ReadFiles First = new ReadFiles(myFileGrid1,myFileBot1,myFileResult1);

    //ReadWriteFiles Second= new ReadWriteFiles(myFileGrid2,myFileBot2,myFileResult2);

}