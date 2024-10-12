package com.sinbelisk.ioutils;

import java.io.*;
import java.nio.charset.Charset;

public class CommonUtils {
    public static BufferedReader getBufferedReader(File file) throws IOException{
        return new BufferedReader(new FileReader(file));
    }

    public static BufferedReader getBufferedReaderStream(File file, Charset charset) throws IOException {
        if (charset == null) charset = Charset.defaultCharset();
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }
}
