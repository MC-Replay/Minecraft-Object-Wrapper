package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PandaMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 6;

    private static final byte SNEEZING_BIT = 0x02;
    private static final byte ROLLING_BIT = 0x04;
    private static final byte SITTING_BIT = 0x08;
    private static final byte ON_BACK_BIT = 0x10;

    public PandaMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setBreedTimer(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public void setSneezeTimer(int value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public void setEatTimer(int value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.VarInt(value));
    }

    public void setMainGene(@NotNull Gene value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.Byte((byte) value.ordinal()));
    }

    public void setHiddenGene(@NotNull Gene value) {
        super.metadata.setIndex(OFFSET + 4, Metadata.Byte((byte) value.ordinal()));
    }

    public void setSneezing(boolean value) {
        this.setMaskBit(OFFSET + 5, SNEEZING_BIT, value);
    }

    public void setRolling(boolean value) {
        this.setMaskBit(OFFSET + 5, ROLLING_BIT, value);
    }

    public void setSitting(boolean value) {
        this.setMaskBit(OFFSET + 5, SITTING_BIT, value);
    }

    public void setOnBack(boolean value) {
        this.setMaskBit(OFFSET + 5, ON_BACK_BIT, value);
    }

    public int getBreedTimer() {
        return super.metadata.getIndex(OFFSET, 0);
    }

    public int getSneezeTimer() {
        return super.metadata.getIndex(OFFSET + 1, 0);
    }

    public int getEatTimer() {
        return super.metadata.getIndex(OFFSET + 2, 0);
    }

    public @NotNull Gene getMainGene() {
        return Gene.VALUES[super.metadata.getIndex(OFFSET + 3, (byte) 0)];
    }

    public @NotNull Gene getHiddenGene() {
        return Gene.VALUES[super.metadata.getIndex(OFFSET + 4, (byte) 0)];
    }

    public boolean isSneezing() {
        return this.getMaskBit(OFFSET + 5, SNEEZING_BIT);
    }

    public boolean isRolling() {
        return this.getMaskBit(OFFSET + 5, ROLLING_BIT);
    }

    public boolean isSitting() {
        return this.getMaskBit(OFFSET + 5, SITTING_BIT);
    }

    public boolean isOnBack() {
        return this.getMaskBit(OFFSET + 5, ON_BACK_BIT);
    }

    public enum Gene {

        NORMAL,
        AGGRESSIVE,
        LAZY,
        WORRIED,
        PLAYFUL,
        WEAK,
        BROWN;

        private static final Gene[] VALUES = values();
    }
}