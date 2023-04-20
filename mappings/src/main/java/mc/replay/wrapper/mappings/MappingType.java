package mc.replay.wrapper.mappings;

import com.google.gson.JsonDeserializer;
import mc.replay.wrapper.mappings.loader.EntityTypeMappingLoader;
import mc.replay.wrapper.mappings.loader.MaterialMappingLoader;
import mc.replay.wrapper.mappings.objects.EntityTypeMapping;
import mc.replay.wrapper.mappings.objects.Mapping;
import mc.replay.wrapper.mappings.objects.MaterialMapping;
import mc.replay.wrapper.mappings.objects.deserializer.EntityTypeMappingDeserializer;
import mc.replay.wrapper.mappings.objects.deserializer.MaterialMappingDeserializer;
import org.jetbrains.annotations.NotNull;

public enum MappingType {

    ENTITY_TYPE("entities", EntityTypeMappingLoader.class, EntityTypeMapping.class, new EntityTypeMappingDeserializer()),
    MATERIAL("items", MaterialMappingLoader.class, MaterialMapping.class, new MaterialMappingDeserializer());

    private final String filePrefix;
    private final Class<? extends MappingLoader<?>> mappingLoaderClass;
    private final Class<? extends Mapping> mappingClass;
    private final JsonDeserializer<? extends Mapping> deserializer;

    MappingType(@NotNull String filePrefix, @NotNull Class<? extends MappingLoader<?>> mappingLoaderClass, @NotNull Class<? extends Mapping> mappingClass, @NotNull JsonDeserializer<? extends Mapping> deserializer) {
        this.filePrefix = filePrefix;
        this.mappingLoaderClass = mappingLoaderClass;
        this.mappingClass = mappingClass;
        this.deserializer = deserializer;
    }

    public @NotNull String getFilePrefix() {
        return this.filePrefix;
    }

    public @NotNull Class<? extends MappingLoader<?>> getMappingLoaderClass() {
        return this.mappingLoaderClass;
    }

    public @NotNull Class<? extends Mapping> getMappingClass() {
        return this.mappingClass;
    }

    public @NotNull JsonDeserializer<? extends Mapping> getDeserializer() {
        return this.deserializer;
    }
}