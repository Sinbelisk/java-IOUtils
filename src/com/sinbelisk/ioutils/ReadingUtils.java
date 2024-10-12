package com.sinbelisk.ioutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadingUtils {
    private StringBuilder fileTextBuilder;

    private String readAndGetTextFromBuffer(BufferedReader reader) throws IOException {
        fileTextBuilder = getStringBuilder();
        String bufferLine;

        while ((bufferLine = reader.readLine()) != null) {
            fileTextBuilder.append(bufferLine);
            fileTextBuilder.append("\n");
        }

        return fileTextBuilder.toString();
    }

    public String readAndGetFileText(File file) throws IOException {
        try (BufferedReader reader = CommonUtils.getBufferedReader((file))) {
            return readAndGetTextFromBuffer(reader);
        }
    }

    public String readAndGetFileText(File file, Charset encoding) throws IOException {
        try (BufferedReader reader = CommonUtils.getBufferedReaderStream(file, encoding)) {
            return readAndGetTextFromBuffer(reader);
        }
    }

    public int getFileLines(File file) throws IOException {
        int lines = 0;
        try (BufferedReader reader = CommonUtils.getBufferedReaderStream(file, null)) {
            while (reader.readLine() != null) {
                lines++;
            }
        }
        return lines;
    }

    public String getFileLine(File file, int line) throws IOException {
        String fileTextLine = "";

        try (BufferedReader reader = CommonUtils.getBufferedReaderStream(file, null)) {
            int currentLine = 0;
            while ((fileTextLine = reader.readLine()) != null) {
                if (currentLine == line) return fileTextLine;
                currentLine++;
            }
        }
        return null;
    }

    public int getFileLineCharCount(File file, int line) throws IOException {
        return getFileLine(file, line).length();
    }

    private StringBuilder getStringBuilder() {
        return new StringBuilder();
    }

}
