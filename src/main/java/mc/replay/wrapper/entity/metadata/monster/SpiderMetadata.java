package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class SpiderMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    private static final byte CLIMBING_BIT = 0x01;

    public SpiderMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setClimbing(boolean value) {
        this.setMaskBit(OFFSET, CLIMBING_BIT, value);
    }

    public boolean isClimbing() {
        return this.getMaskBit(OFFSET, CLIMBING_BIT);
    }
}