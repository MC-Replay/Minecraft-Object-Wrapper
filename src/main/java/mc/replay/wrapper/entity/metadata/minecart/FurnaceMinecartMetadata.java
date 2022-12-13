package mc.replay.wrapper.entity.metadata.minecart;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FurnaceMinecartMetadata extends AbstractMinecartMetadata {

    public static final byte OFFSET = AbstractMinecartMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    public FurnaceMinecartMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setHasFuel(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean hasFuel() {
        return metadata.getIndex(OFFSET, false);
    }

    @Override
    public int getObjectData() {
        return 2;
    }
}