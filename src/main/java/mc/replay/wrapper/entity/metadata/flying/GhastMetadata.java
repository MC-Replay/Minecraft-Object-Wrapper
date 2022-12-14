package mc.replay.wrapper.entity.metadata.flying;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GhastMetadata extends FlyingMetadata {

    public static final int OFFSET = FlyingMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public GhastMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setAttacking(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isAttacking() {
        return super.metadata.getIndex(OFFSET, false);
    }
}