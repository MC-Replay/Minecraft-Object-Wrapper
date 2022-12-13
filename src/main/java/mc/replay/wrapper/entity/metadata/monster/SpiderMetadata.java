package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpiderMetadata extends MonsterMetadata {

    public static final byte OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    private static final byte CLIMBING_BIT = 0x01;

    public SpiderMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setClimbing(boolean value) {
        this.setMaskBit(OFFSET, CLIMBING_BIT, value);
    }

    public boolean isClimbing() {
        return this.getMaskBit(OFFSET, CLIMBING_BIT);
    }
}