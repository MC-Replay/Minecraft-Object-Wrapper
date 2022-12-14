package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrimedTntMetadata extends EntityMetadata {

    public static final byte OFFSET = EntityMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    public PrimedTntMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setFuseTime(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public int getFuseTime() {
        return super.metadata.getIndex(OFFSET, 80);
    }
}