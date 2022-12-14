package mc.replay.wrapper.entity.metadata.flying;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PhantomMetadata extends FlyingMetadata {

    public static final byte OFFSET = FlyingMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    public PhantomMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setSize(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public int getSize() {
        return super.metadata.getIndex(OFFSET, 0);
    }
}