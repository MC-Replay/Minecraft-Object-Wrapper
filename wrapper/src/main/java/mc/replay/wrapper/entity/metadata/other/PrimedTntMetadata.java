package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import org.jetbrains.annotations.NotNull;

public class PrimedTntMetadata extends EntityMetadata {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public static final int FUSE_TIME_INDEX = OFFSET;

    public PrimedTntMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setFuseTime(int value) {
        super.metadata.setIndex(FUSE_TIME_INDEX, Metadata.VarInt(value));
    }

    public int getFuseTime() {
        return super.metadata.getIndex(FUSE_TIME_INDEX, 80);
    }
}