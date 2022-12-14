package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ElderGuardianMetadata extends GuardianMetadata {

    public static final int OFFSET = GuardianMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    public ElderGuardianMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}