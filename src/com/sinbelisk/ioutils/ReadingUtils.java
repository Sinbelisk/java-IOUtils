package com.sinbelisk.ioutils;

import java.io.*;

public class ReadingUtils {

    public String readFileLines(File file) throws IOException {
        StringBuilder fileTextBuilder = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){

            String bufferLine;

            while((bufferLine = reader.readLine()) != null){
                fileTextBuilder.append(bufferLine);
                fileTextBuilder.append("\n");
            }
        }

        return fileTextBuilder.toString();
    }
}
