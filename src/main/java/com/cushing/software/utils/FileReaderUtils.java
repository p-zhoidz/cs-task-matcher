package com.cushing.software.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * File reader utils.
 *
 * @author p.zhoidz.
 */
public final class FileReaderUtils {

    /**
     * Utility class private constructor.
     */
    private FileReaderUtils() {

    }

    /**
     * Read file as stream.
     *
     * @param fileName name of teh file to read.
     * @return {@link Stream<String>} which contains list of file lines.
     * @throws NullPointerException if file does not exist.
     * @throws IOException          standard I/O exception cases.
     */
    public static List<String> getContent(String fileName) throws IOException {

        Path path = Paths.get(fileName);
        try (Stream<String> stream = Files.lines(path)) {
            return stream.collect(toList());
        }


    }

}
