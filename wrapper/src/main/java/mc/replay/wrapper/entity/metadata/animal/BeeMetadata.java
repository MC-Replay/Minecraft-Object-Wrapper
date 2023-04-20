package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.AgeableMobMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BeeMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    private static final byte ANGRY_BIT = 0x02;
    private static final byte HAS_STUNG_BIT = 0x04;
    private static final byte HAS_NECTAR_BIT = 0x08;

    public BeeMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setAngry(boolean value) {
        this.setMaskBit(OFFSET, ANGRY_BIT, value);
    }

    public void setHasStung(boolean value) {
        this.setMaskBit(OFFSET, HAS_STUNG_BIT, value);
    }

    public void setHasNectar(boolean value) {
        this.setMaskBit(OFFSET, HAS_NECTAR_BIT, value);
    }

    public void setAngerTicks(int value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public boolean isAngry() {
        return this.getMaskBit(OFFSET, ANGRY_BIT);
    }

    public boolean hasStung() {
        return this.getMaskBit(OFFSET, HAS_STUNG_BIT);
    }

    public boolean hasNectar() {
        return this.getMaskBit(OFFSET, HAS_NECTAR_BIT);
    }

    public int getAngerTicks() {
        return super.metadata.getIndex(OFFSET + 1, 0);
    }
}