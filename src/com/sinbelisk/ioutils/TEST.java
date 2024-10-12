package com.sinbelisk.ioutils;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TEST {
    // Clase para probar el funcionamiento de la liberia
    public static final String PATH = "files/";
    public static void main(String[] args) throws IOException {
        File file = new File(PATH + "file.txt");
        WritingUtils.writeTextLineInMemoryPointer(file, "hola", 2, StandardCharsets.UTF_8);

    }
}
