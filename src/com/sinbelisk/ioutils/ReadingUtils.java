package com.sinbelisk.ioutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadingUtils {

    private String readAndGetTextFromBuffer(BufferedReader reader) throws IOException {
        StringBuilder fileTextBuilder = new StringBuilder();
        String bufferLine;

        while ((bufferLine = reader.readLine()) != null) {
            fileTextBuilder.append(bufferLine);
            fileTextBuilder.append("\n");
        }

        return fileTextBuilder.toString();
    }

    // Los 2 métodos reciben un archivo y devuelven un String con el contenido del archivo.
    // El segundo permite codificación.
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

    //Devuelve un valor entero con la cantidad de lineas que tiene el archivo
    public int getFileLines(File file) throws IOException {
        int lines = 0;
        try (BufferedReader reader = CommonUtils.getBufferedReaderStream(file, null)) {
            while (reader.readLine() != null) {
                lines++;
            }
        }
        return lines;
    }

    // Devuelve como String la linea de texto especificada de un archivo
    public String searchAndGetFileLine(File file, int line) throws IOException {
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

    // Devuelve un valor entero con la cantidad de caracteres de una linea de texto de un archivo
    public int getFileLineCharCount(File file, int line) throws IOException {
        return searchAndGetFileLine(file, line).length();
    }

    // Busca cualquier String dentro de un arhivo de cualquier codificacion. Devuelve la posicion del primer caracter de la cadena.
    public int getStringPositionFromFile(File file, String inputLine, Charset charset) throws IOException {
        StringBuilder fileTextBuilder = new StringBuilder();
        String line;

        try (BufferedReader bufferedReader = CommonUtils.getBufferedReaderStream(file, charset)) {
            while ((line = bufferedReader.readLine()) != null) {
                fileTextBuilder.append(line).append("\n");
            }
        }

        String fileText = fileTextBuilder.toString();
        return fileText.indexOf(inputLine);
    }
}
