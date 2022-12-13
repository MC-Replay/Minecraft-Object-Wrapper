package mc.replay.wrapper.entity.metadata.monster.raider;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RavagerMetadata extends RaiderMetadata {

    public static final byte OFFSET = RaiderMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public RavagerMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}