package mc.replay.wrapper.entity;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EntityWrapper {

    protected final int id;
    protected final UUID uuid;

    protected Metadata metadata = new Metadata();
    protected EntityMetadata entityMetadata;

    public EntityWrapper(int entityId, UUID uuid) {
        this.id = entityId;
        this.uuid = uuid;
    }

    public EntityWrapper(@NotNull Entity entity) {
        this(entity.getEntityId(), entity.getUniqueId());
    }

    public int getEntityId() {
        return this.id;
    }

    public UUID getUniqueId() {
        return this.uuid;
    }
}