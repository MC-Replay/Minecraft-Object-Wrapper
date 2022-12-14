package mc.replay.wrapper.entity.metadata.water.fish;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SalmonMetadata extends AbstractFishMetadata {

    public static final byte OFFSET = AbstractFishMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public SalmonMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}