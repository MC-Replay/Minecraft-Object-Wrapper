package mc.replay.wrapper.entity.metadata.animal.tameable;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WolfMetadata extends TameableAnimalMetadata {

    public static final byte OFFSET = TameableAnimalMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 3;

    public WolfMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
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