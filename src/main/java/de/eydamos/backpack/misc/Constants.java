package de.eydamos.backpack.misc;

import de.eydamos.backpack.item.TabBackpacks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;

import java.io.File;

public class Constants {
    public static final String MOD_ID = "backpack";
    public static final String MOD_NAME = "Backpack";
    public static final String MOD_VERSION = "3.0.1";
    public static final String FINGERPRINT = "@FINGERPRINT@";

    public static final String DOMAIN = "backpack";

    public static final String CLASS_PROXY_CLIENT = "de.eydamos.backpack.proxy.ClientProxy";
    public static final String CLASS_PROXY_SERVER = "de.eydamos.backpack.proxy.ServerProxy";
    public static final String CLASS_GUI_FACTORY = "de.eydamos.backpack.factory.FactoryConfigurationGui";

    public static final String UPDATE_FILE = "http://www.eydamos.de/minecraft/backpack/version.json";

    public static final String INVENTORIES_PATH = DOMAIN + File.separator + "inventory" + File.separator;
    public static final String PLAYERS_PATH = DOMAIN + File.separator + "player" + File.separator;

    public static final ResourceLocation guiCombined = new ResourceLocation(DOMAIN, "textures/gui/gui_combined.png");
    public static final ResourceLocation modelTexture = new ResourceLocation(DOMAIN, "textures/model/backpack.png");

    public static final CreativeTabs tabBackpacks = new TabBackpacks();

    public class Guis {
        public static final int RENAME_BACKPACK = 0;
        public static final int BACKPACK = 1;
        public static final byte SPECIAL_SLOTS = 2;
        public static final byte CARRIED_BACKPACK = 3;
    }

    public class GuiCommands {
        public static final byte CLEAR = 1;
        public static final byte SAVE = 2;
        public static final byte PREV = 3;
        public static final byte NEXT = 4;
    }

    public class NBT {
        public static final String BACKPACK = "backpack";
        public static final String UID = "backpack-UID";
        public static final String SLOT = "slot";
        public static final String SLOTS = "slots";
        public static final String SLOTS_USED = "slots_used";
        public static final String SLOTS_PER_ROW = "slots_per_row";
        public static final String FRAME_TIER = "frame_tier";
        public static final String LEATHER_TIER = "leather_tier";
        public static final String MODULE_SLOTS = "module_slots";
    }

    public class NBTTypes extends net.minecraftforge.common.util.Constants.NBT {
    }
}
