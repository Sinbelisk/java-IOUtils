package com.sinbelisk.ioutils;

import java.io.*;
import java.nio.charset.Charset;

public class ReadingUtils {
    private StringBuilder fileTextBuilder;
    public String readAndGetFileLines(File file) throws IOException {
        fileTextBuilder = getStringBuilder();

        try(BufferedReader reader = getBufferedReader(file)){

            String bufferLine;

            while((bufferLine = reader.readLine()) != null){
                fileTextBuilder.append(bufferLine);
                fileTextBuilder.append("\n");
            }
        }
        return fileTextBuilder.toString();
    }

    public String readAndGetFileLine(File file, Charset encoding) throws IOException {
        fileTextBuilder = getStringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(file, encoding))){
            String bufferLine;

            while((bufferLine = reader.readLine()) != null){
                fileTextBuilder.append(bufferLine);
                fileTextBuilder.append("\n");
            }
        }

        return fileTextBuilder.toString();
    }
    public int getFileLines(File file) throws IOException {
        int lines = 0;


        return lines;
    }
    public String getFileLine(File file, int line) throws IOException {
        String fileTextLine = "";

        try(BufferedReader reader = getBufferedReaderStream(file, null)){
            int currentLine = 0;
            while ((fileTextLine = reader.readLine()) != null){
                if(currentLine == line) return fileTextLine;
                currentLine++;
            }
        }

        return null;
    }
    public int getFileLineCharCount(File file, int line) throws IOException{
        return getFileLine(file, line).length();
    }

    private StringBuilder getStringBuilder(){
        return new StringBuilder();
    }
    private BufferedReader getBufferedReader(File file) throws IOException{
        return new BufferedReader(new FileReader(file));
    }

    private BufferedReader getBufferedReaderStream(File file, Charset charset) throws IOException{
        if (charset == null) charset = Charset.defaultCharset();
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }

}
