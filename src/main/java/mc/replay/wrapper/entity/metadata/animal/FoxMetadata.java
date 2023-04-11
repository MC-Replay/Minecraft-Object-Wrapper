package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FoxMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 4;

    private static final byte SITTING_BIT = 0x01;
    private static final byte CROUCHING_BIT = 0x04;
    private static final byte INTERESTED_BIT = 0x08;
    private static final byte POUNCING_BIT = 0x10;
    private static final byte SLEEPING_BIT = 0x20;
    private static final byte FACEPLANTED_BIT = 0x40;
    private static final byte DEFENDING_BIT = (byte) 0x80;

    public FoxMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setType(@NotNull Type type) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(type.ordinal()));
    }

    public void setSitting(boolean value) {
        this.setMaskBit(OFFSET + 1, SITTING_BIT, value);
    }

    public void setFoxSneaking(boolean value) {
        this.setMaskBit(OFFSET + 1, CROUCHING_BIT, value);
    }

    public void setInterested(boolean value) {
        this.setMaskBit(OFFSET + 1, INTERESTED_BIT, value);
    }

    public void setPouncing(boolean value) {
        this.setMaskBit(OFFSET + 1, POUNCING_BIT, value);
    }

    public void setSleeping(boolean value) {
        this.setMaskBit(OFFSET + 1, SLEEPING_BIT, value);
    }

    public void setFaceplanted(boolean value) {
        this.setMaskBit(OFFSET + 1, FACEPLANTED_BIT, value);
    }

    public void setDefending(boolean value) {
        this.setMaskBit(OFFSET + 1, DEFENDING_BIT, value);
    }

    public void setFirstUuid(@Nullable UUID value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.OptUUID(value));
    }

    public void setSecondUuid(@Nullable UUID value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.OptUUID(value));
    }

    public @NotNull Type getType() {
        return Type.VALUES[super.metadata.getIndex(OFFSET, 0)];
    }

    public boolean isSitting() {
        return this.getMaskBit(OFFSET + 1, SITTING_BIT);
    }

    public boolean isFoxSneaking() {
        return this.getMaskBit(OFFSET + 1, CROUCHING_BIT);
    }

    public boolean isInterested() {
        return this.getMaskBit(OFFSET + 1, INTERESTED_BIT);
    }

    public boolean isPouncing() {
        return this.getMaskBit(OFFSET + 1, POUNCING_BIT);
    }

    public boolean isSleeping() {
        return this.getMaskBit(OFFSET + 1, SLEEPING_BIT);
    }

    public boolean isFaceplanted() {
        return this.getMaskBit(OFFSET + 1, FACEPLANTED_BIT);
    }

    public boolean isDefending() {
        return this.getMaskBit(OFFSET + 1, DEFENDING_BIT);
    }

    public @Nullable UUID getFirstUuid() {
        return super.metadata.getIndex(OFFSET + 2, null);
    }

    public @Nullable UUID getSecondUuid() {
        return super.metadata.getIndex(OFFSET + 3, null);
    }

    public enum Type {

        RED,
        SNOW;

        private final static Type[] VALUES = values();
    }
}