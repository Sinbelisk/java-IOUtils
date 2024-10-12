package com.sinbelisk.ioutils;

import java.io.*;
import java.nio.charset.*;

public class WritingUtils {
    // To Write Some String In Not Binary File (Overwrite or not)
    public static void writeTextLine(File file, String line, boolean append) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append))) {
            bufferedWriter.write(line);
        }
    }

    // To Write Some Character In Not Binary File (Overwrite or not)
    public static void writeCharacter(File file, char character, boolean append) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append))) {
            bufferedWriter.write(character);
        }
    }

    // To Write Several Strings In Not Binary File (Overwrite or not, Line Break or not)
    public static void writeSeveralStrings(File file, String[] lines, boolean append, boolean inNewLine) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append))) {
            for (String line : lines) {
                bufferedWriter.write(line);

                if (inNewLine) {
                    bufferedWriter.newLine();
                }

                bufferedWriter.flush();
            }
        }
    }
    // To Write Several Characters In Not Binary File (Overwrite or not, Line Break or not)
    public static void writeSeveralCharacters(File file, char[] characters, boolean append, boolean inNewLine) throws IOException {
       try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append))) {
           for (char character : characters) {
               bufferedWriter.write(character);

               if (inNewLine) {
                   bufferedWriter.newLine();
               }

               bufferedWriter.flush();
           }
       }
    }

    // To Write Coding Line In Not Binary File (Overwrite or not)
    public static void writeCodingTextLine(File file, String line, boolean append, Charset coding) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), coding))) {
            bufferedWriter.write(line);
        }
    }

    // To Write Coding Line In Somewhere Position
    public static void writeTextLineInMemoryPointer(File file, String line, long pointer, Charset coding) throws IOException {
        if (coding == null) {
            coding = Charset.defaultCharset();
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.seek(pointer);
            randomAccessFile.write(line.getBytes(coding));
        }
    }
}
