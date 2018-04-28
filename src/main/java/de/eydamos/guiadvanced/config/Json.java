package de.eydamos.guiadvanced.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.Iterator;

public class Json {
    private JsonObject jsonObject;

    public Json(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Json get(Object key) {
        return get(key.toString());
    }

    public Json get(String key) {
        return new Json(getJsonElement(key).getAsJsonObject());
    }

    public JsonElement getJsonElement(String key) {
        Iterator<String> it = Arrays.asList(key.split("\\.")).iterator();
        JsonObject json = jsonObject;
        String subKey;

        while (it.hasNext()) {
            subKey = it.next();

            if (!json.has(subKey)) {
                return null;
            }

            if (json.get(subKey).isJsonPrimitive()) {
                if (it.hasNext()) {
                    return null;
                } else {
                    return json.get(subKey);
                }
            }

            json = json.getAsJsonObject(subKey);
        }

        return json;
    }

    public int getInt(String key) {
        return getJsonElement(key).getAsInt();
    }

    public String getString(String key) {
        return getJsonElement(key).getAsString();
    }

    public boolean has(String key) {
        if (jsonObject == null) {
            return false;
        }

        Iterator<String> it = Arrays.asList(key.split("\\.")).iterator();
        JsonObject json = jsonObject;
        String subKey;

        while (it.hasNext()) {
            subKey = it.next();

            if (!json.has(subKey)) {
                return false;
            }

            if (json.get(subKey).isJsonPrimitive()) {
                if (it.hasNext()) {
                    return false;
                } else {
                    return true;
                }
            }

            json = json.getAsJsonObject(subKey);
        }

        return true;
    }
}
