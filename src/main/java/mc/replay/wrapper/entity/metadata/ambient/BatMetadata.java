package mc.replay.wrapper.entity.metadata.ambient;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BatMetadata extends AmbientCreatureMetadata {

    public static final byte OFFSET = AmbientCreatureMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    private static final byte IS_HANGING_BIT = 0x01;

    public BatMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setHanging(boolean value) {
        this.setMaskBit(OFFSET, IS_HANGING_BIT, value);
    }

    public boolean isHanging() {
        return this.getMaskBit(OFFSET, IS_HANGING_BIT);
    }
}