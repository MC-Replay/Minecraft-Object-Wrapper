package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CaveSpiderMetadata extends SpiderMetadata {

    public static final int OFFSET = SpiderMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET;

    public CaveSpiderMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}