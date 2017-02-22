package com.cushing.software.predicates;

import com.cushing.software.settings.Settings;

import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author p.zhoidz.
 */
public class PredicateFactory {

    private static final Settings settings = Settings.SettingsHolder.HOLDER_INSTANCE;

    public enum Type {
        SIMPLE, CONTAINS, EDIT_DISTANCE
    }

    public static Predicate<String> getPredicate(Type type, Set<String> patterns) throws IOException {
        switch (type) {
            case SIMPLE:
                return StringPredicates.equalsPredicate(patterns);

            case CONTAINS:
                return StringPredicates.containsPredicate(patterns);

            case EDIT_DISTANCE:
                return StringPredicates.distanceAwareDamerauPredicate(patterns, settings.getMaxEditDistance());

            default:
                //FIXME
                throw new IllegalArgumentException();
        }
    }
}