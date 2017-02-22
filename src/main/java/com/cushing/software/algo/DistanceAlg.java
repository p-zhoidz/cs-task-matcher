package com.cushing.software.algo;

/**
 * Base contract for distance frames determination.
 *
 * @author p.zhoidz.
 */
public interface DistanceAlg {
    /**
     * @param s1  The first string to compare.
     * @param s2  The second string to compare.
     * @param max max number of (insertion, deletion, substitution of a single character,
     *            or a transposition of two adjacent characters)
     * @return true if distance is less or equal to {@code max} value, false otherwise.
     * @throws NullPointerException if s1 or s2 is null.
     */
    boolean matches(final String s1, final String s2, int max);
}
