package com.cushing.software.utils;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertTrue;

/**
 * @author p.zhoidz.
 */
public class FileReaderUtilsUnitTest {

    @Test
    public void testFileRead() throws Exception {
        List<String> expected = Stream.of("sample line 1", "sample line 2").collect(toList());

        String path = this.getClass()
                .getClassLoader()
                .getResource("input.txt")
                .getPath();

        List<String> result = FileReaderUtils.getContent(path);

        assertTrue(result.containsAll(expected));
    }

}