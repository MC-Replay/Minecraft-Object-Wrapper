package mc.replay.wrapper.entity.metadata.monster.skeleton;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StrayMetadata extends AbstractSkeletonMetadata {

    public static final byte OFFSET = AbstractSkeletonMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public StrayMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}