package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SheepMetadata extends AnimalMetadata {

    public static final byte OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    private static final byte COLOR_BITS = 0x0F;
    private static final byte SHEARED_BIT = 0x10;

    public SheepMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setColor(byte color) {
        byte before = this.getMask(OFFSET);
        byte mask = before;
        mask &= ~COLOR_BITS;
        mask |= (color & COLOR_BITS);
        if (mask != before) {
            this.setMask(OFFSET, mask);
        }
    }

    public void setSheared(boolean value) {
        this.setMaskBit(OFFSET, SHEARED_BIT, value);
    }

    public int getColor() {
        return this.getMask(OFFSET) & COLOR_BITS;
    }

    public boolean isSheared() {
        return this.getMaskBit(OFFSET, SHEARED_BIT);
    }
}