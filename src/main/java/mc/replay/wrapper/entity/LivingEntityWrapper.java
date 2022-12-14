package mc.replay.wrapper.entity;

import mc.replay.wrapper.entity.metadata.LivingEntityMetadata;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class LivingEntityWrapper extends EntityWrapper {

    public LivingEntityWrapper(@NotNull EntityType entityType, int entityId, @NotNull UUID uuid) {
        super(entityType, entityId, uuid);
    }

    public LivingEntityWrapper(@NotNull LivingEntity entity) {
        super(entity);
    }

    @Override
    public @Nullable LivingEntityMetadata getMetadata() {
        return this.getMetaData(LivingEntityMetadata.class);
    }
}