package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VexMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    private static final byte ATTACKING_BIT = 0x01;

    public VexMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setAttacking(boolean value) {
        this.setMaskBit(OFFSET, ATTACKING_BIT, value);
    }

    public boolean isAttacking() {
        return this.getMaskBit(OFFSET, ATTACKING_BIT);
    }
}