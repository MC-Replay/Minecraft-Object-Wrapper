package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GlowItemFrameMetadata extends ItemFrameMetadata {

    public static final int OFFSET = ItemFrameMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    public GlowItemFrameMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}