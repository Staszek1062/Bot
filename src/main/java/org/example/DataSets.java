package org.example;

import java.io.File;


public class DataSets {
    final File myFileGrid1 = new File("src/bin/grid-1.txt"); // Specify the filename
    final File myFileBot1 = new File("src/bin/job-1.txt"); // Specify the filename
    final File myFileResult1 = new File("src/bin/results-1.txt"); // Specify the filename
    //final File myFileGrid2 = new File("src/bin/grid-2.txt"); // Specify the filename
    //final File myFileBot2 = new File("src/bin/job-2.txt"); // Specify the filename
    //final File myFileResult2 = new File("src/bin/results-2.txt"); // Specify the filename
    ReadWriteFiles First = new ReadWriteFiles(myFileGrid1,myFileBot1,myFileResult1);
    //ReadWriteFiles Second= new ReadWriteFiles(myFileGrid2,myFileBot2,myFileResult2);

}