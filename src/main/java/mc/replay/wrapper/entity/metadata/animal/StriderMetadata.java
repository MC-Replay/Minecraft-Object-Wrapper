package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class StriderMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 3;

    public StriderMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setTimeToBoost(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public void setShaking(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public void setHasSaddle(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public int getTimeToBoost() {
        return super.metadata.getIndex(OFFSET, 0);
    }

    public boolean isShaking() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public boolean hasSaddle() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }
}