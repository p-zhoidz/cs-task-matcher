package com.cushing.software;

import com.cushing.software.predicates.ModeFactory;
import com.cushing.software.utils.FileReaderUtils;

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

    /**
     * Application main entry point.
     *
     * @param args entry point arguments.
     *             Skipped in terms of current implementations
     */
    public static void main(String... args) {
        try {
            Set<String> patternsStream = FileReaderUtils.getStream(PATTERNS_FILE_NAME).collect(toSet());
            Stream<String> inputLineStream = FileReaderUtils.getStream(INPUT_FILE_NAME);

            ModeFactory factory = new ModeFactory();
            Predicate<String> predicate = factory.getPredicate(ModeFactory.Type.CONTAINS, patternsStream);

            Tester<String> tester = new Tester<>();
            List<String> test = tester.test(predicate, inputLineStream);

            System.out.println(test);
        } catch (Throwable exception) {
            System.out.println("An exception has occurred. Message:");
            System.out.println(exception.getMessage());
        }
    }
}
