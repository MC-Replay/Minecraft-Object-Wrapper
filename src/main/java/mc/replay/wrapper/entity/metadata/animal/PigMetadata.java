package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class PigMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    public PigMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setHasSaddle(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public void setTimeToBoost(int value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public boolean hasSaddle() {
        return super.metadata.getIndex(OFFSET, false);
    }

    public int getTimeToBoost() {
        return super.metadata.getIndex(OFFSET + 1, 0);
    }
}