package com.sinbelisk.ioutils;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TEST {
    // Clase para probar el funcionamiento de la liberia
    public static final String PATH = "files/";
    public static void main(String[] args) throws IOException {
        File file = new File(PATH + "construct.png");
        File file2 = new File(PATH + "construct1.jpg");

        boolean areEquals = FileUtils.areFilesEquals(file, file2);

        if(areEquals) System.out.println("Files are equals");
        else System.out.println("Files aren't equals");

    }
}
