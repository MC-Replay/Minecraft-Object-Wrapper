package mc.replay.wrapper.entity.metadata.flying;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class PhantomMetadata extends FlyingMetadata {

    public static final int OFFSET = FlyingMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public PhantomMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setSize(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public int getSize() {
        return super.metadata.getIndex(OFFSET, 0);
    }
}