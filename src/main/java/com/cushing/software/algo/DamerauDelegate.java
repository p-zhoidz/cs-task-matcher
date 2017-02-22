package com.cushing.software.algo;

import info.debatty.java.stringsimilarity.Damerau;

/**
 * Implementation of the {@link DistanceAlg}. Simply delegates calculation
 * to {@link Damerau} algorithm and checks whether returned value is less or equal to the maximum.
 *
 * @author p.zhoidz.
 */
public class DamerauDelegate implements DistanceAlg {
    private static Damerau target = new Damerau();

    @Override
    public boolean matches(String s1, String s2, int max) {
        return target.distance(s1, s2) <= max;
    }
}
