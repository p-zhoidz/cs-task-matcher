package com.cushing.software.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Application configuration settings.
 *
 * @author p.zhoidz.
 */
public final class Settings {
    private static final String CONFIGURATION_FILE = "configuration.properties";

    private static final String MAX_EDIT_DISTANCE_PROPERTY_NAME = "max.edit.distance";
    private static final String INPUT_FILE_PROPERTY_NAME = "input.file.path";
    private static final String PATTERNS_FILE_PROPERTY_NAME = "patterns.file.path";

    private static final String MAX_EDIT_DISTANCE_DEFAULT = "1";


    private final Integer maxEditDistance;
    private final String inputFilePath;
    private final String patternsFilePath;

    /**
     * Private constructor.
     * When object is created, all the properties are being initialized.
     */
    private Settings() {
        InputStream input = Settings.class
                .getClassLoader()
                .getResourceAsStream(CONFIGURATION_FILE);

        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            throw new IllegalStateException(String.format(
                    "Properties file can not be initialized. "
                            + "Please make sure %s exists.", CONFIGURATION_FILE));
        }

        this.maxEditDistance = Integer.valueOf(
                prop.getProperty(MAX_EDIT_DISTANCE_PROPERTY_NAME, MAX_EDIT_DISTANCE_DEFAULT));

        this.inputFilePath = prop.getProperty(INPUT_FILE_PROPERTY_NAME);
        this.patternsFilePath = prop.getProperty(PATTERNS_FILE_PROPERTY_NAME);

        if (inputFilePath == null || inputFilePath.isEmpty()) {
            throw new IllegalArgumentException(String.format("Input file should be specified. "
                    + "Please check %s", CONFIGURATION_FILE));
        }

        if (patternsFilePath == null || patternsFilePath.isEmpty()) {
            throw new IllegalArgumentException(String.format("Patterns file should be specified. "
                    + "Please check %s", CONFIGURATION_FILE));
        }
    }

    /**
     * @return Returns the value of maxEditDistance.
     */
    public Integer getMaxEditDistance() {
        return maxEditDistance;
    }

    /**
     * @return Returns the value of patternsFilePath.
     */
    public String getPatternsFilePath() {
        return patternsFilePath;
    }

    /**
     * @return Returns the value of inputFilePath.
     */
    public String getInputFilePath() {
        return inputFilePath;
    }

    /**
     * {@link Settings} singleton instance holder.
     */
    public static class SettingsHolder {
        public static final Settings HOLDER_INSTANCE = new Settings();
    }
}
