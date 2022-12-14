package mc.replay.wrapper.entity.metadata.animal.tameable;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.animal.AnimalMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class TameableAnimalMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    private static final byte SITTING_BIT = 0x01;
    private static final byte TAMED_BIT = 0x04;

    protected TameableAnimalMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setSitting(boolean value) {
        this.setMaskBit(OFFSET, SITTING_BIT, value);
    }

    public void setTamed(boolean value) {
        this.setMaskBit(OFFSET, TAMED_BIT, value);
    }

    public void setOwner(@NotNull UUID value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.OptUUID(value));
    }

    public boolean isSitting() {
        return this.getMaskBit(OFFSET, SITTING_BIT);
    }

    public boolean isTamed() {
        return this.getMaskBit(OFFSET, TAMED_BIT);
    }

    public @NotNull UUID getOwner() {
        return super.metadata.getIndex(OFFSET + 1, null);
    }
}