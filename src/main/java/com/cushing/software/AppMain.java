package com.cushing.software;

import com.cushing.software.predicates.PredicateFactory;
import com.cushing.software.utils.FileReaderUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 * @author p.zhoidz.
 */
public class AppMain {
    private static final String PATTERNS_FILE_NAME = "patterns.txt";
    private static final String INPUT_FILE_NAME = "input.txt";

    public static void main(String... args) throws IOException {

        Set<String> patternsStream = FileReaderUtils.getStream(PATTERNS_FILE_NAME).collect(toSet());
        Stream<String> inputLineStream = FileReaderUtils.getStream(INPUT_FILE_NAME);

        Predicate<String> predicate = PredicateFactory.getPredicate(PredicateFactory.Type.CONTAINS, patternsStream);

        Tester<String> tester = new Tester<>();
        List<String> test = tester.test(predicate, inputLineStream);

        System.out.println(test);

    }
}
