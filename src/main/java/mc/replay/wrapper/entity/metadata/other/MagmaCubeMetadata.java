package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MagmaCubeMetadata extends SlimeMetadata {

    public static final byte OFFSET = SlimeMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public MagmaCubeMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}