package com.sinbelisk.ioutils;

import java.io.*;

public class FileUtils {


    public static void compararArchivosBinarios(String rutaArchivo1, String rutaArchivo2) throws IOException {
        try (FileInputStream fis1 = new FileInputStream(rutaArchivo1);
             FileInputStream fis2 = new FileInputStream(rutaArchivo2)) {
            int byteArchivo1, byteArchivo2;
            boolean sonIguales = true;

            // Comparar ambos archivos byte a byte
            while ((byteArchivo1 = fis1.read()) != -1 && (byteArchivo2 = fis2.read()) != -1) {
                if (byteArchivo1 != byteArchivo2) {
                    sonIguales = false;
                    break;
                }
            }
            // Verificar si ambos archivos tienen la misma longitud
            if (fis1.read() != -1 || fis2.read() != -1) {
                sonIguales = false;
            }

            if (sonIguales) {
                System.out.println("Los archivos son idénticos.");
            } else {
                System.out.println("Los archivos son diferentes.");
            }
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

}



