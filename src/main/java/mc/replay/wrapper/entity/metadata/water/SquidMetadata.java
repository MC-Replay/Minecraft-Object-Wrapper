package mc.replay.wrapper.entity.metadata.water;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SquidMetadata extends WaterAnimalMetadata {

    public static final byte OFFSET = WaterAnimalMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public SquidMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}