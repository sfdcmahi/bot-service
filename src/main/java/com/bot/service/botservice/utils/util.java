package com.bot.service.botservice.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class util {
    private static final int BUFFER_SIZE = 1024;
    public static File convertToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    /**
     * Reads everything from reader and returns as string.
     * @param reader the object read from
     * @return a string containing all the information
     */
    public static String readAll(final Reader reader) throws IOException
    {
        if(reader == null)
        {
            throw new NullPointerException("Reader is null...");
        }

        final StringBuilder stringBuilder = new StringBuilder();
        copy(reader, stringBuilder);
        return stringBuilder.toString();

    }

    private static void copy(final Reader from, final Appendable to) throws IOException
    {
        final CharBuffer charBuffer = CharBuffer.allocate(BUFFER_SIZE);
        while (from.read(charBuffer) != -1) {
            charBuffer.flip();
            to.append(charBuffer);
            charBuffer.clear();
        }
    }


}
