package com.cushing.software.predicates;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

/**
 * @author p.zhoidz.
 */
public class StringPredicatesUnitTest {

    @Test
    public void testEqualsPredicate() throws Exception {
        List<String> expected = Stream.of("the end")
                .collect(toList());

        Set<String> patterns = Stream.of("the end",
                "matches",
                "line 3",
                "and this is anoother.").collect(toSet());

        Stream<String> input = Stream.of("Hello. This is line 1 of text.",
                "and this is another.",
                "line 3 here",
                "the end",
                "and this is anoother");

        Predicate<String> sut = StringPredicates.equalsPredicate(patterns);
        List<String> result = input.filter(sut).collect(toList());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testContainsPredicate() throws Exception {
        List<String> expected = Stream.of("the end", "line 3 here")
                .collect(toList());

        Set<String> patterns = Stream.of("the end",
                "matches",
                "line 3",
                "and this is anoother.").collect(toSet());

        Stream<String> input = Stream.of("Hello. This is line 1 of text.",
                "and this is another.",
                "line 3 here",
                "the end",
                "and this is anoother");

        Predicate<String> sut = StringPredicates.containsPredicate(patterns);
        List<String> result = input.filter(sut).collect(toList());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testDistanceAwarePredicate() throws Exception {
        List<String> expected = Stream.of("the end", "and this is another.")
                .collect(toList());

        Set<String> patterns = Stream.of("the end",
                "matches",
                "line 3",
                "and this is anoother.").collect(toSet());

        Stream<String> input = Stream.of("Hello. This is line 1 of text.",
                "and this is another.",
                "line 3 here",
                "the end",
                "and this is anoother");

        Predicate<String> sut = StringPredicates.distanceAwareDamerauPredicate(patterns, 2);
        List<String> result = input.filter(sut).collect(toList());

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(expected));
    }



    @Test
    public void testLevenshteinPredicate() throws Exception {
        List<String> expected = Stream.of("the end", "and this is another.")
                .collect(toList());

        Set<String> patterns = Stream.of("the end",
                "matches",
                "line 3",
                "and this is anoother.").collect(toSet());

        Stream<String> input = Stream.of("Hello. This is line 1 of text.",
                "and this is another.",
                "line 3 here",
                "the end",
                "and this is anoother");

        Predicate<String> sut = StringPredicates.distanceAwareLevenshteinPredicate(patterns, 2);
        List<String> result = input.filter(sut).collect(toList());

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(expected));
    }

}