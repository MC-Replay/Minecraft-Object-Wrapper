package mc.replay.wrapper.entity;

import mc.replay.wrapper.utils.WrapperReflections;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public final class EntityTypeWrapper {

    private final EntityType entityType;
    private final int typeId;

    public EntityTypeWrapper(EntityType entityType) {
        this.entityType = entityType;
        this.typeId = WrapperReflections.getEntityTypeId(entityType);
    }

    public EntityTypeWrapper(int typeId) {
        this.entityType = WrapperReflections.getEntityTypeById(typeId);
        this.typeId = typeId;
    }

    public @NotNull EntityType getBukkitType() {
        return this.entityType;
    }

    public int getTypeId() {
        return this.typeId;
    }
}