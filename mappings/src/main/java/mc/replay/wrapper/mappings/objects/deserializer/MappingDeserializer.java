package mc.replay.wrapper.mappings.objects.deserializer;

import com.google.gson.JsonDeserializer;
import mc.replay.wrapper.mappings.objects.Mapping;

public interface MappingDeserializer<T extends Mapping> extends JsonDeserializer<T> {
}