package mc.replay.wrapper.entity.metadata.monster.zombie;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DrownedMetadata extends ZombieMetadata {

    public static final byte OFFSET = ZombieMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public DrownedMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}