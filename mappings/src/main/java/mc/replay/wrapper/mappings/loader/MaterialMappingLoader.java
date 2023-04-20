package mc.replay.wrapper.mappings.loader;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import mc.replay.wrapper.mappings.MappingId;
import mc.replay.wrapper.mappings.MappingKey;
import mc.replay.wrapper.mappings.MappingLoader;
import mc.replay.wrapper.mappings.objects.MaterialMapping;
import mc.replay.wrapper.utils.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

public final class MaterialMappingLoader implements MappingLoader<MaterialMapping> {

    @Override
    public Map<MaterialMapping, Pair<MappingKey, MappingId>> loadMappings(@NotNull Gson gson, @NotNull InputStreamReader reader) {
        Type type = new TypeToken<Map<String, MaterialMapping>>() {}.getType();
        Map<String, MaterialMapping> map = gson.fromJson(reader, type);

        return this.convertMap(map, (mapping) -> new MappingId(mapping.id()));
    }
}