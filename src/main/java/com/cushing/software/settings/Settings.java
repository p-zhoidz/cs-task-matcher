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
    private static final String MAX_EDIT_DISTANCE_DEFAULT = "1";

    private final Integer maxEditDistance;

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
    }

    /**
     * @return Returns the value of maxEditDistance.
     */
    public Integer getMaxEditDistance() {
        return maxEditDistance;
    }

    /**
     * {@link Settings} singleton instance holder.
     */
    public static class SettingsHolder {
        public static final Settings HOLDER_INSTANCE = new Settings();
    }
}
