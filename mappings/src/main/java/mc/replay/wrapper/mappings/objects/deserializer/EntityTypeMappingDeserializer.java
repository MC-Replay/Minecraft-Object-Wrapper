package mc.replay.wrapper.mappings.objects.deserializer;

import com.google.gson.*;
import mc.replay.wrapper.mappings.objects.EntityTypeMapping;

import java.lang.reflect.Type;

public final class EntityTypeMappingDeserializer implements JsonDeserializer<EntityTypeMapping> {

    @Override
    public EntityTypeMapping deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String key = object.get("mojangName").getAsString().toLowerCase();
        int id = object.get("id").getAsInt();
        EntityTypeMapping.PacketType packetType = context.deserialize(object.get("packetType"), EntityTypeMapping.PacketType.class);
        return new EntityTypeMapping(key, id, packetType);
    }
}