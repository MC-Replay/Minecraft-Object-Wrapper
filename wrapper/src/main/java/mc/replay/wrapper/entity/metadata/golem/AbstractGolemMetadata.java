package mc.replay.wrapper.entity.metadata.golem;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.MobMetadata;
import org.jetbrains.annotations.NotNull;

public class AbstractGolemMetadata extends MobMetadata {

    public static final int OFFSET = MobMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    protected AbstractGolemMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }
}