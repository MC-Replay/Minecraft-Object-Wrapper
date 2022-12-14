package mc.replay.wrapper.entity.metadata.monster.skeleton;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkeletonMetadata extends AbstractSkeletonMetadata {

    public static final int OFFSET = AbstractSkeletonMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    public SkeletonMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}