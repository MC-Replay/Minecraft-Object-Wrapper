package mc.replay.wrapper.entity.metadata.ambient;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.MobMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AmbientCreatureMetadata extends MobMetadata {

    public static final byte OFFSET = MobMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    protected AmbientCreatureMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}