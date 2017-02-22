package com.cushing.software.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * File reader utils.
 *
 * @author p.zhoidz.
 */
public class FileReaderUtils {

    /**
     * Read file as stream.
     *
     * @param fileName name of teh file to read.
     * @return {@link Stream<String>} which contains list of file lines.
     * @throws NullPointerException if file does not exist.
     * @throws IOException          standard I/O exception cases.
     */
    public static Stream<String> getStream(String fileName) throws IOException {
        String input = FileReaderUtils.class.getClass()
                .getClassLoader()
                .getResource(fileName)
                .getFile();

        Path path = Paths.get(input);
        return Files.lines(path);
    }
}
