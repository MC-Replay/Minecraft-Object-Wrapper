package mc.replay.wrapper.entity.metadata;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AgeableMobMetadata extends MobMetadata {

    public static final byte OFFSET = MobMetadata.OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    public AgeableMobMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setBaby(boolean value) {
        if (this.isBaby() == value) return;
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isBaby() {
        return super.metadata.getIndex(OFFSET, false);
    }
}