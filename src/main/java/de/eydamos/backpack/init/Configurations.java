package de.eydamos.backpack.init;

import de.eydamos.backpack.Features;
import de.eydamos.backpack.misc.Constants;
import net.minecraftforge.common.config.Configuration;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class Configurations {
    public static Configuration config;

    public static boolean configExists;

    public static boolean OPEN_ONLY_PERSONAL_BACKPACK;
    public static boolean RENDER_BACKPACK_MODEL;
    public static int MAX_BACKPACK_AMOUNT;

    public static boolean refreshConfig() {
        config.getCategory(Configuration.CATEGORY_GENERAL).setComment("General Backpack configuration");
        config.getCategory(Constants.CONFIG_CATEGORY_FEATURES).setComment("Configuration to disable certain features");

        OPEN_ONLY_PERSONAL_BACKPACK = config.get(Configuration.CATEGORY_GENERAL, "openOnlyWornBackpacks", false, getOpenOnlyPersonalBackpacksComment()).getBoolean(false);
        RENDER_BACKPACK_MODEL = config.get(Configuration.CATEGORY_GENERAL, "renderBackpackModel", true, getRenderBackpackModelComment()).getBoolean(true);
        MAX_BACKPACK_AMOUNT = config.get(Configuration.CATEGORY_GENERAL, "maxBackpackAmount", 0, getMaxBackpackAmountComment(), 0, 36).getInt();

        if (config.hasChanged()) {
            config.save();
        }

        return true;
    }

    public static boolean featureEnabled(Features feature) {
        return config.get(Constants.CONFIG_CATEGORY_FEATURES, featureName(feature), true, feature.getConfigComment()).getBoolean(true) && refreshConfig();
    }

    public static String featureName(Features feature) {
        String[] words = feature.name().toLowerCase(Locale.ENGLISH).split("_");
        if (words.length == 1) {
            return words[0];
        }

        StringBuilder featureName = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            featureName.append(StringUtils.capitalize(words[i]));
        }
        return featureName.toString();
    }

    private static String getOpenOnlyPersonalBackpacksComment() {
        return "If true you can only open a backpack that you wear in the extra slot";
    }

    private static String getRenderBackpackModelComment() {
        return "If true the backpack 3D model will be rendered";
    }

    private static String getMaxBackpackAmountComment() {
        return "Number of backpacks a player can have in his inventory\n" + "valid: integers 0-36\n" + "0 = unlimited\n";
    }
}
