package com.sinbelisk.ioutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CommonUtils {
    public int getFileLines(File file) throws IOException {
        int lines = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while (reader.readLine() != null) lines++;
        }

        return lines;
    }
}
