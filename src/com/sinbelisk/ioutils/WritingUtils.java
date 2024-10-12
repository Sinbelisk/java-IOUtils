package com.sinbelisk.ioutils;

import java.io.*;

public class WritingUtils {
    public static void writeLineInTxtFile(File file, String line, boolean append) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append))) {
            bufferedWriter.write(line);
        }
    }
}
