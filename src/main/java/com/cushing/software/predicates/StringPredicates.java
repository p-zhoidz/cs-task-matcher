package com.cushing.software.predicates;

import com.cushing.software.algo.DamerauDelegate;
import com.cushing.software.algo.DamerauImproved;
import com.cushing.software.algo.DistanceAlg;
import com.cushing.software.algo.LevenshteinBounded;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Predicates factory.
 *
 * @author p.zhoidz.
 */
public final class StringPredicates {

    /**
     * Private constructor.
     */
    private StringPredicates() {

    }

    /**
     * Simple string equals predicate.
     *
     * @param patterns strings to compare.
     * @return true if string matches at least one pattern.
     */
    public static Predicate<String> equalsPredicate(Set<String> patterns) {
        return s -> patterns
                .stream()
                .parallel()
                .filter(s::equals)
                .findAny()
                .isPresent();
    }

    /**
     * Simple string 'contains' predicate.
     *
     * @param patterns strings to compare.
     * @return true if string matches at least one pattern.
     */
    public static Predicate<String> containsPredicate(Set<String> patterns) {
        return s -> patterns
                .stream()
                .parallel()
                .filter(s::contains)
                .findAny()
                .isPresent();
    }

    /**
     * Distance aware predicate. Please, refer {@code DamerauDelegate} for details.
     *
     * @param patterns    strings to compare.
     * @param maxDistance maximum edit distance.
     * @return true if string matches at least one pattern.
     */
    public static Predicate<String> distanceAwareDamerauPredicate(Set<String> patterns, int maxDistance) {
        DistanceAlg alg = new DamerauDelegate();
        return getDistAwarePredicate(alg, patterns, maxDistance);
    }

    /**
     * Improved distance aware predicate. Please, refer {@code DamerauImproved} for details.
     *
     * @param patterns    strings to compare.
     * @param maxDistance maximum edit distance.
     * @return true if string matches at least one pattern.
     */
    public static Predicate<String> distanceAwareDamerauImprovedPredicate(Set<String> patterns, int maxDistance) {
        DistanceAlg alg = new DamerauImproved();
        return getDistAwarePredicate(alg, patterns, maxDistance);
    }

    /**
     * Improved distance aware predicate.
     * Please, refer {@code StringUtils.getLevenshteinDistance(CharSequence s, CharSequence t, final int threshold)}
     * for details.
     *
     * @param patterns    strings to compare.
     * @param maxDistance maximum edit distance.
     * @return true if string matches at least one pattern.
     */
    public static Predicate<String> distanceAwareLevenshteinPredicate(Set<String> patterns, int maxDistance) {
        DistanceAlg alg = new LevenshteinBounded();
        return getDistAwarePredicate(alg, patterns, maxDistance);
    }

    /**
     * Distance aware predicate factory method.
     *
     * @param alg         algorithm to utilize.
     * @param patterns    strings to compare.
     * @param maxDistance maximum 'edit distance'.
     * @return true if string matches at least one pattern.
     */
    private static Predicate<String> getDistAwarePredicate(DistanceAlg alg, Set<String> patterns, int maxDistance) {
        return s -> patterns
                .stream()
                .parallel()
                .filter(p -> alg.matches(s, p, maxDistance))
                .findAny()
                .isPresent();
    }

}
