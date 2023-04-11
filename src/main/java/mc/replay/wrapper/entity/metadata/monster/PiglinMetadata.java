package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class PiglinMetadata extends BasePiglinMetadata {

    public static final int OFFSET = BasePiglinMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 3;

    public PiglinMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setBaby(boolean value) {
        if (this.isBaby() == value) return;
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public void setChargingCrossbow(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public void setDancing(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public boolean isBaby() {
        return super.metadata.getIndex(OFFSET, false);
    }

    public boolean isChargingCrossbow() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public boolean isDancing() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }
}