package com.cushing.software;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @param <T> type of the tested objects.
 * @author p.zhoidz.
 */
public class Tester<T> {

    /**
     * Test input stream against predicate.
     *
     * @param predicate predicate to filter stream out.
     * @param stream    stream of lines.
     * @return List of string which match appropriate predicate.
     */
    List<T> test(Predicate<T> predicate, Stream<T> stream) {
        return stream
                .filter(predicate)
                .collect(toList());
    }
}
