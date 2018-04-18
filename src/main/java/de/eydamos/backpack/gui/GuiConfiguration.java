package de.eydamos.backpack.gui;

import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.List;

public class GuiConfiguration extends GuiConfig {
    public GuiConfiguration(GuiScreen guiScreen, List<IConfigElement> configElements) {
        super(
            guiScreen,
            configElements,
            Constants.MOD_ID,
            false,
            false,
            I18n.format(Localizations.LABEL_BACKPACK_CONFIG)
        );
    }
}
