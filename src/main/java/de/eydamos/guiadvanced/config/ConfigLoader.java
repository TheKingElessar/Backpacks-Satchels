package de.eydamos.guiadvanced.config;

import com.google.gson.JsonParser;
import de.eydamos.backpack.Backpack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

public class ConfigLoader implements IResourceManagerReloadListener {
    private IResourceManager resourceManager;

    private Hashtable<String, Json> configs = new Hashtable<>();

    @Override
    @ParametersAreNonnullByDefault
    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.resourceManager = resourceManager;

        Backpack.logger.info("Loading backpack sprite data.");
        Element.clear();
        for (Element element : Element.values()) {
            loadFragmentContainer(element);
        }
        configs.clear();
        Backpack.logger.info("Done loading backpack sprite data.");
    }

    private void loadFragmentContainer(Element element) {
        Json config = loadConfigFile(element.getFileName());

        buildFragments(element, config);
    }

    private Json loadConfigFile(String fileName) {
        try {
            if (configs.containsKey(fileName)) {
                return configs.get(fileName);
            }

            ResourceLocation resourceLocation = new ResourceLocation(
                "guiadvanced",
                "models/gui/" + fileName + ".json"
            );
            IResource resource = resourceManager.getResource(resourceLocation);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            JsonParser parser = new JsonParser();

            Json config = new Json(parser.parse(reader).getAsJsonObject());
            configs.put(fileName, config);

            return config;
        } catch (IOException e) {
            Backpack.logger.error("Could not load guiadvanced:models/gui/{}.json\nException: {}", fileName, e);
        }

        return new Json(null);
    }

    private void buildFragments(Element element, Json config) {
        buildFragments(element, config, new Offset());
    }

    private void buildFragments(Element element, Json config, Offset variantOffset) {
        Hashtable<State, Offset> states = buildStates(config);

        if (config.has("extends.parent")) {
            Json parentConfig = loadConfigFile(config.getString("extends.parent"));
            Offset offset = buildOffset(config.get("extends"));
            buildFragments(element, parentConfig, offset);

            element.addStates(states);
        }

        for (Fragment fragment : Fragment.values()) {
            if (!config.has(fragment.getKey())) {
                continue;
            }

            Json fragmentConfig = config.get(fragment.getKey());
            FragmentHelper fragmentHelper = buildFragmentHelper(fragmentConfig, variantOffset, fragment.getVariant());
            element.addFragment(fragment, fragmentHelper);
        }

        element.addStates(states);
    }

    private FragmentHelper buildFragmentHelper(Json config, Offset offset, Variant variant) {
        String widthKey = variant.getWidthKey();
        String heightKey = variant.getHeightKey();

        int width = config.getInt(widthKey);
        int height = config.getInt(heightKey);
        int u = config.getInt(variant.getKey(".") + "x");
        int v = config.getInt(variant.getKey(".") + "y");

        u = u + offset.getU();
        v = v + offset.getV();

        return new FragmentHelper(width, height, u, v, buildStates(config, false));
    }

    private Hashtable<State, Offset> buildStates(Json config) {
        return buildStates(config, true);
    }

    private Hashtable<State, Offset> buildStates(Json config, boolean withDefaults) {
        Hashtable<State, Offset> states = new Hashtable<>();

        if (withDefaults) {
            // default state is an offset of 0,0
            states.put(State.ENABLED, new Offset());
            states.put(State.DISABLED, new Offset());
            states.put(State.HOVER, new Offset());
        }

        if (!config.has("states")) {
            return states;
        }

        config = config.get("states");

        // load and overwrite states from config
        for (State state : State.values()) {
            if (!state.equals(State.ENABLED) && config.has(state.getIdentifier())) {
                states.put(state, buildOffset(config.get(state.getIdentifier())));
            }
        }

        return states;
    }

    private Offset buildOffset(Json config) {
        int u = 0;
        int v = 0;

        if (config.has("xOffset")) {
            u = config.getInt("xOffset");
        }

        if (config.has("yOffset")) {
            v = config.getInt("yOffset");
        }

        return new Offset(u, v);
    }
}
