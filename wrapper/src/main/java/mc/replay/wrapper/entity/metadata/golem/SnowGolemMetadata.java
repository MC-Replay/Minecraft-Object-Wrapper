package mc.replay.wrapper.entity.metadata.golem;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class SnowGolemMetadata extends AbstractGolemMetadata {

    public static final int OFFSET = AbstractGolemMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    private static final byte PLAYER_CREATED_BIT = 0x01;

    public SnowGolemMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setHasPumpkinHat(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Byte((value) ? (byte) 0x10 : (byte) 0x00));
    }

    public boolean hasPumpkinHat() {
        return super.metadata.getIndex(OFFSET, (byte) 0x10) == (byte) 0x10;
    }
}