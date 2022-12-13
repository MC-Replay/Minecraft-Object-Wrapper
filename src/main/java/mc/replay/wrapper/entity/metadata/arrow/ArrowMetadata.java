package mc.replay.wrapper.entity.metadata.arrow;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import mc.replay.wrapper.entity.metadata.ProjectileMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArrowMetadata extends AbstractArrowMetadata implements ObjectDataProvider, ProjectileMetadata {

    public static final byte OFFSET = AbstractArrowMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    private EntityWrapper shooter;

    public ArrowMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setColor(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public int getColor() {
        return super.metadata.getIndex(OFFSET, -1);
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
        return (this.shooter == null) ? 0 : this.shooter.getEntityId() + 1;
    }

    @Override
    public boolean requiresVelocityPacketAtSpawn() {
        return true;
    }
}