package com.sinbelisk.ioutils;

import java.io.*;

import static com.sinbelisk.ioutils.CommonUtils.*;

public class FileUtils {
    private static final int BUFFER_SIZE_MEDIUM = 8192; //Buffer of 8KB
    public boolean areFilesEquals(File file1, File file2) throws IOException {
        try (BufferedInputStream fis1 = getReadStream(file1);
             BufferedInputStream fis2 = getReadStream(file2)) {

            return compareFiles(fis1, fis2, BUFFER_SIZE_MEDIUM);
        }
    }

    public int copyBinaryFile(File fileToCopy, String outputPath, int bufferSize) {
        if (!fileToCopy.exists()) return CommonUtils.FILE_NOT_FOUND;

        File copiedFile = new File(outputPath + "/Copy" + fileToCopy.getName());

        try(BufferedInputStream originalFile = getReadStream(fileToCopy);
            BufferedOutputStream copyFile = getWriteStream(copiedFile)){

            copy(originalFile, copyFile, bufferSize);
            return CommonUtils.SUCCESS;

        } catch (IOException e){
            System.err.println("Error when copying file: " + e.getMessage());
            return CommonUtils.ERROR;
        }
    }
    private void copy(InputStream inputStream, OutputStream outputStream, int bufferSize) throws IOException {
            byte[] buffer = new byte[bufferSize];
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

    // receives two blocks of bytes and compares them.
    private boolean areBuffersEquals(byte[] buffer1, byte[] buffer2) {
        for (int i = 0; i < buffer1.length; i++) {
            if (buffer1[i] != buffer2[i]) {
                return false;
            }
        }

        return true;
    }
}