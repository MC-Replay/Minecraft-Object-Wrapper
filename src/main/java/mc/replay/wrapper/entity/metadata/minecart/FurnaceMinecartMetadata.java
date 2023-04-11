package mc.replay.wrapper.entity.metadata.minecart;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class FurnaceMinecartMetadata extends AbstractMinecartMetadata {

    public static final int OFFSET = AbstractMinecartMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public FurnaceMinecartMetadata(@NotNull Metadata metadata) {
        super(metadata);
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