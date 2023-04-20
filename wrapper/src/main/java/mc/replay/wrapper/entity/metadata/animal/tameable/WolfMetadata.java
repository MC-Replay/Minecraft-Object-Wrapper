package mc.replay.wrapper.entity.metadata.animal.tameable;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class WolfMetadata extends TameableAnimalMetadata {

    public static final int OFFSET = TameableAnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 3;

    public WolfMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setBegging(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public void setCollarColor(int value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public void setAngerTime(int value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.VarInt(value));
    }

    public boolean isBegging() {
        return super.metadata.getIndex(OFFSET, false);
    }

    public int getCollarColor() {
        return super.metadata.getIndex(OFFSET + 1, 14);
    }

    public int getAngerTime() {
        return super.metadata.getIndex(OFFSET + 2, 0);
    }
}