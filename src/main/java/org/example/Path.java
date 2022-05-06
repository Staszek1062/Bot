package org.example;

import java.io.File;
import java.io.FileNotFoundException;


public class Path {
    File myFileGrid = new File("src/bin/grid-2.txt"); // Specify the filename
    File myFileBot = new File("src/bin/job-1.txt"); // Specify the filename
    ReadFile First= new ReadFile(myFileGrid);
    ReadFile Second= new ReadFile(myFileBot);



    public Path() throws FileNotFoundException {
        System.out.println("File not found");

    }
    // ReadFile Second= new ReadFile(myFileBot);

}