package de.eydamos.backpack.factory;

import de.eydamos.backpack.gui.GuiConfiguration;
import de.eydamos.backpack.init.Configurations;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FactoryConfigurationGui implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraftInstance) {
    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen guiScreen) {
        List<IConfigElement> configElements = new ArrayList<>();
        ConfigElement general = new ConfigElement(Configurations.config.getCategory(Configuration.CATEGORY_GENERAL));
        ConfigElement features = new ConfigElement(Configurations.config.getCategory(Constants.CONFIG_CATEGORY_FEATURES));

        configElements.add(general);
        configElements.add(features);

        return new GuiConfiguration(guiScreen, configElements);
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}
