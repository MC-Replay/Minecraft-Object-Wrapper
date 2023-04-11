package mc.replay.wrapper.entity.metadata.monster.skeleton;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class WitherSkeletonMetadata extends AbstractSkeletonMetadata {

    public static final int OFFSET = AbstractSkeletonMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    public WitherSkeletonMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }
}