package za.co.infernos.goety.utils;

import za.co.infernos.goety.config.AttributesConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Helper utility for safely accessing config values with default fallbacks.
 * This prevents IllegalStateException when config is accessed before it's loaded.
 */
public class ConfigHelper {
    
    /**
     * Safely gets a Double config value with a default fallback.
     */
    public static double getDouble(ModConfigSpec.ConfigValue<Double> configValue, double defaultValue) {
        try {
            return configValue.get();
        } catch (IllegalStateException e) {
            return defaultValue;
        }
    }
    
    /**
     * Safely gets an Integer config value with a default fallback.
     */
    public static int getInt(ModConfigSpec.ConfigValue<Integer> configValue, int defaultValue) {
        try {
            return configValue.get();
        } catch (IllegalStateException e) {
            return defaultValue;
        }
    }
    
    /**
     * Safely gets a Boolean config value with a default fallback.
     */
    public static boolean getBoolean(ModConfigSpec.ConfigValue<Boolean> configValue, boolean defaultValue) {
        try {
            return configValue.get();
        } catch (IllegalStateException e) {
            return defaultValue;
        }
    }
    
    /**
     * Safely gets a Float config value with a default fallback.
     */
    public static float getFloat(ModConfigSpec.ConfigValue<Double> configValue, float defaultValue) {
        try {
            return configValue.get().floatValue();
        } catch (IllegalStateException e) {
            return defaultValue;
        }
    }
    
    /**
     * Safely gets a Float from a Double config value (convenience method).
     */
    public static float getFloatFromDouble(double value) {
        return (float) value;
    }
}
