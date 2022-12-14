package mc.replay.wrapper.entity.metadata.monster.zombie;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ZombifiedPiglinMetadata extends ZombieMetadata {

    public static final int OFFSET = ZombieMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    public ZombifiedPiglinMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}