package mc.replay.wrapper.entity.metadata.arrow;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import mc.replay.wrapper.entity.metadata.ProjectileMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpectralArrowMetadata extends AbstractArrowMetadata implements ObjectDataProvider, ProjectileMetadata {

    public static final int OFFSET = AbstractArrowMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    private EntityWrapper shooter;

    public SpectralArrowMetadata(@NotNull Metadata metadata) {
        super(metadata);
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