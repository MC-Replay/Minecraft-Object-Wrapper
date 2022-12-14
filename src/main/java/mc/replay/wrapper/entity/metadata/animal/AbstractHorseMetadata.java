package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AbstractHorseMetadata extends AnimalMetadata {

    public static final byte OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 2;

    private static final byte TAMED_BIT = 0x02;
    private static final byte SADDLED_BIT = 0x04;
    private static final byte HAS_BRED_BIT = 0x08;
    private static final byte EATING_BIT = 0x10;
    private static final byte REARING_BIT = 0x20;
    private static final byte MOUTH_OPEN_BIT = 0x40;

    protected AbstractHorseMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setTamed(boolean value) {
        this.setMaskBit(OFFSET, TAMED_BIT, value);
    }

    public void setSaddled(boolean value) {
        this.setMaskBit(OFFSET, SADDLED_BIT, value);
    }

    public void setHasBred(boolean value) {
        this.setMaskBit(OFFSET, HAS_BRED_BIT, value);
    }

    public void setEating(boolean value) {
        this.setMaskBit(OFFSET, EATING_BIT, value);
    }

    public void setRearing(boolean value) {
        this.setMaskBit(OFFSET, REARING_BIT, value);
    }

    public void setMouthOpen(boolean value) {
        this.setMaskBit(OFFSET, MOUTH_OPEN_BIT, value);
    }

    public void setOwner(@Nullable UUID value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.OptUUID(value));
    }

    public boolean isTamed() {
        return this.getMaskBit(OFFSET, TAMED_BIT);
    }

    public boolean isSaddled() {
        return this.getMaskBit(OFFSET, SADDLED_BIT);
    }

    public boolean hasBred() {
        return this.getMaskBit(OFFSET, HAS_BRED_BIT);
    }

    public boolean isEating() {
        return this.getMaskBit(OFFSET, EATING_BIT);
    }

    public boolean isRearing() {
        return this.getMaskBit(OFFSET, REARING_BIT);
    }

    public boolean isMouthOpen() {
        return this.getMaskBit(OFFSET, MOUTH_OPEN_BIT);
    }

    public @Nullable UUID getOwner() {
        return super.metadata.getIndex(OFFSET + 1, null);
    }
}