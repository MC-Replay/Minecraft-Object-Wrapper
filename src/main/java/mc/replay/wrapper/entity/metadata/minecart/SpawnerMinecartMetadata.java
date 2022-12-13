package mc.replay.wrapper.entity.metadata.minecart;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpawnerMinecartMetadata extends AbstractMinecartMetadata {

    public static final byte OFFSET = AbstractMinecartMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public SpawnerMinecartMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    @Override
    public int getObjectData() {
        return 4;
    }
}