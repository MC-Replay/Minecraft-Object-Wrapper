package mc.replay.wrapper.entity.metadata.ambient;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class BatMetadata extends AmbientCreatureMetadata {

    public static final int OFFSET = AmbientCreatureMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    private static final byte IS_HANGING_BIT = 0x01;

    public BatMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setHanging(boolean value) {
        this.setMaskBit(OFFSET, IS_HANGING_BIT, value);
    }

    public boolean isHanging() {
        return this.getMaskBit(OFFSET, IS_HANGING_BIT);
    }
}