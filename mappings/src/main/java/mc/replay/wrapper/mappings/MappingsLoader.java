package mc.replay.wrapper.mappings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mc.replay.packetlib.utils.ProtocolVersion;
import mc.replay.wrapper.mappings.objects.EntityTypeMapping;
import mc.replay.wrapper.mappings.objects.Mapping;
import mc.replay.wrapper.mappings.objects.MaterialMapping;
import mc.replay.wrapper.utils.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class MappingsLoader {

    private final Map<MappingType, Map<MappingsVersion, Map<MappingKey, Mapping>>> mappingsByKey = new HashMap<>();
    private final Map<MappingType, Map<MappingsVersion, Map<MappingId, Mapping>>> mappingsById = new HashMap<>();

    public MappingsLoader() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        for (MappingType type : MappingType.values()) {
            gsonBuilder.registerTypeAdapter(type.getMappingClass(), type.getDeserializer());
        }
        Gson gson = gsonBuilder.create();

        ClassLoader classLoader = this.getClass().getClassLoader();

        for (MappingsVersion version : MappingsVersion.values()) {
            String versionString = version.name().toLowerCase().substring(1);
            String directory = "mappings/mappings_" + versionString + "/";

            for (MappingType mappingType : MappingType.values()) {
                String fileName = versionString + "_" + mappingType.getFilePrefix() + ".json";

                try (InputStream inputStream = classLoader.getResourceAsStream(directory + fileName)) {
                    if (inputStream == null) continue;

                    try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                        MappingLoader<? extends Mapping> loader = mappingType.getMappingLoaderClass().getConstructor().newInstance();
                        Map<? extends Mapping, Pair<MappingKey, MappingId>> map = loader.loadMappings(gson, reader);

                        this.mappingsByKey.putIfAbsent(mappingType, new HashMap<>());
                        this.mappingsByKey.get(mappingType).put(version, new HashMap<>());

                        this.mappingsById.putIfAbsent(mappingType, new HashMap<>());
                        this.mappingsById.get(mappingType).put(version, new HashMap<>());

                        for (Map.Entry<? extends Mapping, Pair<MappingKey, MappingId>> entry : map.entrySet()) {
                            Pair<MappingKey, MappingId> value = entry.getValue();

                            this.mappingsByKey.get(mappingType).get(version).put(value.getKey(), entry.getKey());
                            this.mappingsById.get(mappingType).get(version).put(value.getValue(), entry.getKey());
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public @NotNull Map<MappingKey, EntityTypeMapping> getEntityMappingsByKey(@NotNull ProtocolVersion version) {
        return this.getMappingsByKey(MappingType.ENTITY_TYPE, version);
    }

    public @NotNull Map<MappingId, EntityTypeMapping> getEntityMappingsById(@NotNull ProtocolVersion version) {
        return this.getMappingsById(MappingType.ENTITY_TYPE, version);
    }

    public @NotNull Map<MappingKey, MaterialMapping> getMaterialMappingsByKey(@NotNull ProtocolVersion version) {
        return this.getMappingsByKey(MappingType.MATERIAL, version);
    }

    public @NotNull Map<MappingId, MaterialMapping> getMaterialMappingsById(@NotNull ProtocolVersion version) {
        return this.getMappingsById(MappingType.MATERIAL, version);
    }

    @SuppressWarnings("unchecked")
    private <T extends Mapping> Map<MappingKey, T> getMappingsByKey(MappingType mappingType, ProtocolVersion protocolVersion) {
        MappingsVersion version = MappingsVersion.fromProtocolVersion(protocolVersion);

        Map<MappingsVersion, Map<MappingKey, Mapping>> map = this.mappingsByKey.get(mappingType);
        if (map == null) {
            throw new IllegalStateException("Couldn't find mappings for " + mappingType.name());
        }

        Map<MappingKey, T> mappings = (Map<MappingKey, T>) map.get(version);
        if (mappings == null) {
            throw new IllegalStateException("Couldn't find mappings for " + version.name());
        }

        return mappings;
    }

    @SuppressWarnings("unchecked")
    private <T extends Mapping> Map<MappingId, T> getMappingsById(MappingType mappingType, ProtocolVersion protocolVersion) {
        MappingsVersion version = MappingsVersion.fromProtocolVersion(protocolVersion);

        Map<MappingsVersion, Map<MappingId, Mapping>> map = this.mappingsById.get(mappingType);
        if (map == null) {
            throw new IllegalStateException("Couldn't find mappings for " + mappingType.name());
        }

        Map<MappingId, T> mappings = (Map<MappingId, T>) map.get(version);
        if (mappings == null) {
            throw new IllegalStateException("Couldn't find mappings for " + version.name());
        }

        return mappings;
    }
}