package mc.replay.wrapper.entity;

import mc.replay.packetlib.data.Pos;
import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.utils.WrapperReflections;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

public class EntityWrapper {

    protected int id;
    protected final UUID uuid;

    protected final EntityTypeWrapper entityType;

    protected Metadata metadata = new Metadata();
    protected EntityMetadata entityMetadata;

    protected Pos position;

    protected Vector velocity;

    protected boolean isPassenger = false;

    public EntityWrapper(@NotNull EntityType entityType, int entityId, @NotNull UUID uuid) {
        this.id = entityId;
        this.uuid = uuid;
        this.entityType = new EntityTypeWrapper(entityType);

        this.position = new Pos(0, 0, 0, 0, 0);
        this.velocity = new Vector(0, 0, 0);

        this.entityMetadata = EntityTypes.createMetadata(entityType, this.metadata);
    }

    public EntityWrapper(@NotNull EntityType entityType, @NotNull UUID uuid) {
        this(entityType, WrapperReflections.getNewEntityId(), uuid);
    }

    public EntityWrapper(@NotNull Entity entity) {
        this(entity.getType(), entity.getEntityId(), entity.getUniqueId());

        this.position = Pos.from(entity.getLocation());
        this.velocity = entity.getVelocity();

        this.readDataWatcherItems(entity);
    }

    public int getEntityId() {
        return this.id;
    }

    public @NotNull UUID getUniqueId() {
        return this.uuid;
    }

    public @NotNull EntityTypeWrapper getType() {
        return this.entityType;
    }

    public @NotNull EntityType getBukkitType() {
        return this.entityType.getBukkitType();
    }

    public EntityMetadata getMetadata() {
        return this.entityMetadata;
    }

    public @NotNull Pos getPosition() {
        return this.position;
    }

    public @NotNull Vector getVelocity() {
        return this.velocity;
    }

    public boolean isPassenger() {
        return this.isPassenger;
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

    public void setVelocity(@NotNull Vector velocity) {
        this.velocity = velocity;
    }

    public void setIsPassenger(boolean isPassenger) {
        this.isPassenger = isPassenger;
    }

    public final <T extends EntityMetadata> @NotNull T getMetaData(@NotNull Class<T> clazz) {
        if (clazz.isAssignableFrom(this.entityMetadata.getClass())) {
            return clazz.cast(this.entityMetadata);
        }

        throw new IllegalArgumentException("The metadata class " + clazz.getName() + " is not assignable from " + this.entityMetadata.getClass().getName());
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

    public @NotNull EntityWrapper withUniqueId() {
        this.id = WrapperReflections.getNewEntityId();
        return this;
    }
}