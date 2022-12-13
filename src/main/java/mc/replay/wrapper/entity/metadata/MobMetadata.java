package mc.replay.wrapper.entity.metadata;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MobMetadata extends LivingEntityMetadata {

    public static final byte OFFSET = LivingEntityMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    private final static byte NO_AI_BIT = 0x01;
    private final static byte IS_LEFT_HANDED_BIT = 0x02;
    private final static byte IS_AGGRESSIVE_BIT = 0x04;

    public MobMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setAi(boolean value) {
        this.setMaskBit(OFFSET, NO_AI_BIT, !value);
    }

    public void setLeftHanded(boolean value) {
        this.setMaskBit(OFFSET, IS_LEFT_HANDED_BIT, value);
    }

    public void setAggressive(boolean value) {
        this.setMaskBit(OFFSET, IS_AGGRESSIVE_BIT, value);
    }

    public boolean hasAi() {
        return !this.getMaskBit(OFFSET, NO_AI_BIT);
    }

    public boolean isLeftHanded() {
        return this.getMaskBit(OFFSET, IS_LEFT_HANDED_BIT);
    }

    public boolean isAggressive() {
        return this.getMaskBit(OFFSET, IS_AGGRESSIVE_BIT);
    }
}