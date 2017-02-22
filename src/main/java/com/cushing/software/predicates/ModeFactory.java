package com.cushing.software.predicates;

import com.cushing.software.settings.Settings;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @author p.zhoidz.
 */
public final class ModeFactory {

    private static final Settings SETTINGS = Settings.SettingsHolder.HOLDER_INSTANCE;

    /**
     * Possible predicate modes.
     */
    public enum Type {
        SIMPLE, CONTAINS, EDIT_DISTANCE
    }

    /**
     * Get predicate based on mode.
     *
     * @param type     of the predicate.
     * @param patterns Set of patterns to compare.
     * @return predicate instance.
     */
    public Predicate<String> getPredicate(Type type, Set<String> patterns) {
        switch (type) {
            case SIMPLE:
                return StringPredicates.equalsPredicate(patterns);

            case CONTAINS:
                return StringPredicates.containsPredicate(patterns);

            case EDIT_DISTANCE:
                return StringPredicates.distanceAwareLevenshteinPredicate(patterns, SETTINGS.getMaxEditDistance());

            default:
                throw new IllegalArgumentException();
        }
    }
}