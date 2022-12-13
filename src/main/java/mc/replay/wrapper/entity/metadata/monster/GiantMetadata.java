package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GiantMetadata extends MonsterMetadata {

    public static final byte OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public GiantMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}