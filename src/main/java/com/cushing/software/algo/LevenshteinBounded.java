package com.cushing.software.algo;

import org.apache.commons.lang3.StringUtils;

/**
 * Implementation of the Levenshtein which allows skip calculation if distance
 * will be more than specified one. Please see {@code StringUtils.getLevenshteinDistance} for details.
 *
 * @author p.zhoidz.
 */
public class LevenshteinBounded implements DistanceAlg {

    @Override
    public boolean matches(String s1, String s2, int max) {
        return StringUtils.getLevenshteinDistance(s1, s2, max) != -1;
    }

}
