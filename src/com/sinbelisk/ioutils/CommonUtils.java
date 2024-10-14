package com.sinbelisk.ioutils;

import java.io.*;
import java.nio.charset.Charset;

public class CommonUtils {
    public  static final int ERROR = -1;
    public static final int SUCCESS = 0;
    public static final int FILE_NOT_FOUND = 1;
    public static final int INVALID_BUFFER = 2;

    public static BufferedReader getBufferedReader(File file) throws IOException{
        return new BufferedReader(new FileReader(file));
    }

    public static BufferedReader getBufferedReaderStream(File file, Charset charset) throws IOException {
        if (charset == null) charset = Charset.defaultCharset();
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }
    public static BufferedInputStream getReadStream(File file) throws IOException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    public static BufferedOutputStream getWriteStream(File file) throws  IOException{
        return new BufferedOutputStream(new FileOutputStream(file));
    }
}
