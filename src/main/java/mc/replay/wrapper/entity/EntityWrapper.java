package mc.replay.wrapper.entity;

import mc.replay.packetlib.data.Pos;
import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.utils.WrapperReflections;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class EntityWrapper {

    protected final int id;
    protected final UUID uuid;

    protected final EntityType entityType;

    protected Metadata metadata = new Metadata();
    protected EntityMetadata entityMetadata;

    protected Pos position;

    public EntityWrapper(@NotNull EntityType entityType, int entityId, @NotNull UUID uuid) {
        this.id = entityId;
        this.uuid = uuid;
        this.entityType = entityType;

        this.position = new Pos(0, 0, 0, 0, 0);

        this.entityMetadata = EntityTypes.createMetadata(entityType, this, this.metadata);
    }

    public EntityWrapper(@NotNull Entity entity) {
        this(entity.getType(), entity.getEntityId(), entity.getUniqueId());

        this.position = Pos.from(entity.getLocation());

        this.readDataWatcherItems(entity);
    }

    public int getEntityId() {
        return this.id;
    }

    public UUID getUniqueId() {
        return this.uuid;
    }

    public EntityMetadata getMetadata() {
        return this.entityMetadata;
    }

    public @NotNull Pos getPosition() {
        return this.position;
    }

    public void setPosition(@NotNull Pos position) {
        this.position = position;
    }

    public void setPosition(double x, double y, double z, float yaw, float pitch) {
        this.setPosition(new Pos(x, y, z, yaw, pitch));
    }

    public void setPosition(double x, double y, double z) {
        this.setPosition(new Pos(x, y, z, this.position.yaw(), this.position.pitch()));
    }

    public final <T extends EntityMetadata> @Nullable T getMetaData(@NotNull Class<T> clazz) {
        if (clazz.isAssignableFrom(this.entityMetadata.getClass())) {
            return clazz.cast(this.entityMetadata);
        }

        return null;
    }

    public void addMetadata(@NotNull Map<@NotNull Integer, Metadata.Entry<?>> entries) {
        for (Map.Entry<Integer, Metadata.Entry<?>> entry : entries.entrySet()) {
            this.metadata.setIndex(entry.getKey(), entry.getValue());
        }
    }

    private void readDataWatcherItems(@NotNull Entity entity) {
        Map<Integer, Metadata.Entry<?>> entries = WrapperReflections.readDataWatcher(entity);
        for (Map.Entry<Integer, Metadata.Entry<?>> entry : entries.entrySet()) {
            this.metadata.setIndex(entry.getKey(), entry.getValue());
        }
    }
}