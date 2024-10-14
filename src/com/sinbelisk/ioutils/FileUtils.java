package com.sinbelisk.ioutils;

import java.io.*;

public class FileUtils {
    private static final int BUFFER_SIZE_MEDIUM = 8192; //Buffer size of 8KB
    private static final int BUFFER_SIZE_SMALL = 1024; // Buffer size of 1KB

    public static boolean areFilesEquals(File file1, File file2) throws IOException {
        try (BufferedInputStream fis1 = getReadStream(file1);
             BufferedInputStream fis2 = getReadStream(file2)) {

            return compareFiles(fis1, fis2, BUFFER_SIZE_MEDIUM);
        }
    }
    public static void copiarArchivoBinario(String rutaOriginal, String rutaCopia) throws IOException {
        File archivoOriginal = new File(rutaOriginal);
        File archivoCopia = new File(rutaCopia);

        long inicio = System.currentTimeMillis(); // Inicio del tiempo

        try (FileInputStream fis = new FileInputStream(archivoOriginal);
             FileOutputStream fos = new FileOutputStream(archivoCopia)) {

            // Creamos un buffer para leer los datos en bloques
            byte[] buffer = new byte[1024];  // 1 KB de buffer
            int bytesLeidos;

            // Leer y escribir el archivo en bloques
            while ((bytesLeidos = fis.read(buffer)) != -1) {
                // Imprime cada byte en formato binario (opcional, puede comentarse)
                for (int i = 0; i < bytesLeidos; i++) {
                    System.out.print(String.format("%8s", Integer.toBinaryString(buffer[i] & 0xFF)).replace(' ', '0') + " ");
                }
                fos.write(buffer, 0, bytesLeidos);
            }

            System.out.println("\nImagen copiada correctamente en formato binario: " + archivoCopia.getAbsolutePath());
            long fin = System.currentTimeMillis(); // Fin del tiempo
            System.out.println("Tiempo tomado con buffer: " + (fin - inicio) + " ms");

        }
    }

    // Compares two streams in blocks with a specified buffer size.
    private static boolean compareFiles(InputStream fileStream1, InputStream fileStream2, int bufferSize) throws IOException {
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
            if (!compareBuffers(bufferFile1, bufferFile2)) return false;
        }

        return fileStream2.read() == -1;
    }

    // returns a InputStream with a buffer.
    private static BufferedInputStream getReadStream(File file) throws IOException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    // receives two blocks of bytes and compares them.
    private static boolean compareBuffers(byte[] buffer1, byte[] buffer2) throws IOException {
        for (int i = 0; i < buffer1.length; i++) {
            if (buffer1[i] != buffer2[i]) {
                return false;
            }
        }

        return true;
    }
}