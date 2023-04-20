package mc.replay.wrapper.entity.metadata;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class AgeableMobMetadata extends MobMetadata {

    public static final int OFFSET = MobMetadata.OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public AgeableMobMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setBaby(boolean value) {
        if (this.isBaby() == value) return;
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isBaby() {
        return super.metadata.getIndex(OFFSET, false);
    }
}