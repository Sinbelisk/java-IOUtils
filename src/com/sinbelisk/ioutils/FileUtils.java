package com.sinbelisk.ioutils;

import java.io.*;

public class FileUtils {
    private static final int BUFFER_SIZE_MEDIUM = 8192; //Buffeze of 8KB

    public boolean areFilesEquals(File file1, File file2) throws IOException {
        try (BufferedInputStream fis1 = getReadStream(file1);
             BufferedInputStream fis2 = getReadStream(file2)) {

            return compareFiles(fis1, fis2, BUFFER_SIZE_MEDIUM);
        }
    }

    public void copyBinaryFile(File fileToCopy, String outputPath, int bufferSize) throws IOException {
        if (!fileToCopy.exists()) throw new IOException("File " + fileToCopy.getName() + " doesn't exists");

        File copiedFile = new File(outputPath + "/Copia" + fileToCopy.getName());

        try(BufferedInputStream originalFile = getReadStream(fileToCopy);
            BufferedOutputStream copyFile = getWriteStream(copiedFile)){

            copy(originalFile, copyFile, bufferSize);
        }
    }
    private void copy(InputStream inputStream, OutputStream outputStream, int bufferSize) throws IOException {
            byte[] buffer = new byte[bufferSize];  // 1 KB de buffer
            int readBytes;

            while ((readBytes = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readBytes);
            }
    }

    // Compares two streams in blocks with a specified buffer size.
    private boolean compareFiles(InputStream fileStream1, InputStream fileStream2, int bufferSize) throws IOException {
        // Buffers for both files
        byte[] bufferFile1 = new byte[bufferSize];
        byte[] bufferFile2 = new byte[bufferSize];

        // quantity of read bytes from each file
        int bytesReadFile1;
        int bytesReadFile2;

        // Reads and compares files in blocks of bufferSize
        while ((bytesReadFile1 = fileStream1.read(bufferFile1)) != -1) {
            bytesReadFile2 = fileStream2.read(bufferFile2);

            if (bytesReadFile1 != bytesReadFile2) return false;
            if (!areBuffersEquals(bufferFile1, bufferFile2)) return false;
        }

        return true;
    }

    // returns a InputStream with a buffer.
    private BufferedInputStream getReadStream(File file) throws IOException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    private BufferedOutputStream getWriteStream(File file) throws  IOException{
        return new BufferedOutputStream(new FileOutputStream(file));
    }

    // receives two blocks of bytes and compares them.
    private boolean areBuffersEquals(byte[] buffer1, byte[] buffer2) throws IOException {
        for (int i = 0; i < buffer1.length; i++) {
            if (buffer1[i] != buffer2[i]) {
                return false;
            }
        }

        return true;
    }
}