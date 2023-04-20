package mc.replay.wrapper.mappings.objects.deserializer;

import com.google.gson.*;
import mc.replay.wrapper.mappings.objects.MaterialMapping;

import java.lang.reflect.Type;

public final class MaterialMappingDeserializer implements JsonDeserializer<MaterialMapping> {

    @Override
    public MaterialMapping deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String key = object.get("mojangName").getAsString().toLowerCase();
        int id = object.get("id").getAsInt();
        return new MaterialMapping(key, id);
    }
}