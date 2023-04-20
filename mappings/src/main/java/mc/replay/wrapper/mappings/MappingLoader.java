package mc.replay.wrapper.mappings;

import com.google.gson.Gson;
import mc.replay.wrapper.mappings.objects.Mapping;
import mc.replay.wrapper.utils.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface MappingLoader<T extends Mapping> {

    Map<T, Pair<MappingKey, MappingId>> loadMappings(@NotNull Gson gson, @NotNull InputStreamReader reader);

    default Map<T, Pair<MappingKey, MappingId>> convertMap(Map<String, T> map, Function<T, MappingId> idFunction) {
        Map<T, Pair<MappingKey, MappingId>> newMap = new HashMap<>();

        for (Map.Entry<String, T> entry : map.entrySet()) {
            MappingKey mappingKey = MappingKey.from(entry.getKey());
            MappingId mappingId = idFunction.apply(entry.getValue());

            newMap.put(entry.getValue(), new Pair<>(mappingKey, mappingId));
        }

        return newMap;
    }
}