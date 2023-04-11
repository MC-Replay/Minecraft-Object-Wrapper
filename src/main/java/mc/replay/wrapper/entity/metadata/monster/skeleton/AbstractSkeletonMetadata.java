package mc.replay.wrapper.entity.metadata.monster.skeleton;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.monster.MonsterMetadata;
import org.jetbrains.annotations.NotNull;

public class AbstractSkeletonMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    protected AbstractSkeletonMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }
}