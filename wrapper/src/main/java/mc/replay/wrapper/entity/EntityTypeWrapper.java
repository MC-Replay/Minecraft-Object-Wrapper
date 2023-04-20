package mc.replay.wrapper.entity;

import mc.replay.packetlib.utils.ProtocolVersion;
import mc.replay.wrapper.mappings.objects.EntityTypeMapping;
import mc.replay.wrapper.utils.WrapperMappingsUtils;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public final class EntityTypeWrapper {

    private final EntityType entityType;
    private final EntityTypeMapping mapping;

    public EntityTypeWrapper(@NotNull ProtocolVersion protocolVersion, @NotNull EntityType entityType) {
        this.entityType = entityType;
        this.mapping = WrapperMappingsUtils.getEntityMapping(protocolVersion, entityType);
    }

    public EntityTypeWrapper(@NotNull EntityType entityType) {
        this(ProtocolVersion.getServerVersion(), entityType);
    }

    public EntityTypeWrapper(@NotNull ProtocolVersion protocolVersion, int typeId) {
        this.mapping = WrapperMappingsUtils.getEntityMapping(protocolVersion, typeId);
        this.entityType = EntityType.fromName(this.mapping.key());
    }

    public EntityTypeWrapper(int typeId) {
        this(ProtocolVersion.getServerVersion(), typeId);
    }

    public @NotNull EntityType getBukkitType() {
        return this.entityType;
    }

    public @NotNull EntityTypeMapping getMapping() {
        return this.mapping;
    }
}