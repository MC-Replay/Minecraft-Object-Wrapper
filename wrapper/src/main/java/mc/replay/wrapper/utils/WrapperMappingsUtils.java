package mc.replay.wrapper.utils;

import mc.replay.packetlib.utils.ProtocolVersion;
import mc.replay.wrapper.mappings.MappingId;
import mc.replay.wrapper.mappings.MappingKey;
import mc.replay.wrapper.mappings.MappingsLoader;
import mc.replay.wrapper.mappings.objects.EntityTypeMapping;
import mc.replay.wrapper.mappings.objects.MaterialMapping;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class WrapperMappingsUtils {

    private WrapperMappingsUtils() {
    }

    private static final MappingsLoader MAPPINGS;

    static {
        MAPPINGS = new MappingsLoader();
    }

    public static @NotNull EntityTypeMapping getEntityMapping(@NotNull ProtocolVersion version, @NotNull EntityType type) {
        Map<MappingKey, EntityTypeMapping> mappings = MAPPINGS.getEntityMappingsByKey(version);
        return mappings.get(MappingKey.from(type.getKey()));
    }

    public static @NotNull EntityTypeMapping getEntityMapping(@NotNull ProtocolVersion version, int entityTypeId) {
        Map<MappingId, EntityTypeMapping> mappings = MAPPINGS.getEntityMappingsById(version);
        return mappings.get(MappingId.from(entityTypeId));
    }

    public static @NotNull MaterialMapping getMaterialMapping(@NotNull ProtocolVersion version, @NotNull Material material) {
        Map<MappingKey, MaterialMapping> mappings = MAPPINGS.getMaterialMappingsByKey(version);
        return mappings.get(MappingKey.from(material.getKey()));
    }

    public static @NotNull MaterialMapping getMaterialMapping(@NotNull ProtocolVersion version, int materialId) {
        Map<MappingId, MaterialMapping> mappings = MAPPINGS.getMaterialMappingsById(version);
        return mappings.get(MappingId.from(materialId));
    }
}