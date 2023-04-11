package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import org.jetbrains.annotations.NotNull;

public class PrimedTntMetadata extends EntityMetadata {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public PrimedTntMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setFuseTime(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public int getFuseTime() {
        return super.metadata.getIndex(OFFSET, 80);
    }
}