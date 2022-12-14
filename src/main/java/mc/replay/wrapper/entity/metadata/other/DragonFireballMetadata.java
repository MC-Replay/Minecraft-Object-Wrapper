package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import mc.replay.wrapper.entity.metadata.ProjectileMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DragonFireballMetadata extends EntityMetadata implements ObjectDataProvider, ProjectileMetadata {

    public static final byte OFFSET = EntityMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    private EntityWrapper shooter;

    public DragonFireballMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    @Override
    public @Nullable EntityWrapper getShooter() {
        return this.shooter;
    }

    @Override
    public void setShooter(@Nullable EntityWrapper shooter) {
        this.shooter = shooter;
    }

    @Override
    public int getObjectData() {
        return (this.shooter == null) ? 0 : this.shooter.getEntityId();
    }

    @Override
    public boolean requiresVelocityPacketAtSpawn() {
        return true;
    }
}