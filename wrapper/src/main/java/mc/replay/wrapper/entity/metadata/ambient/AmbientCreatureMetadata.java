package mc.replay.wrapper.entity.metadata.ambient;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.MobMetadata;
import org.jetbrains.annotations.NotNull;

public class AmbientCreatureMetadata extends MobMetadata {

    public static final int OFFSET = MobMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    protected AmbientCreatureMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }
}