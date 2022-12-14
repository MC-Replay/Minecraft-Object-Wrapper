package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShulkerBulletMetadata extends EntityMetadata implements ObjectDataProvider {

    public static final byte OFFSET = EntityMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public ShulkerBulletMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    @Override
    public int getObjectData() {
        return 0;
    }

    @Override
    public boolean requiresVelocityPacketAtSpawn() {
        return true;
    }
}