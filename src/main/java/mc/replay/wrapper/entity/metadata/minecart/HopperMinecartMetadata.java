package mc.replay.wrapper.entity.metadata.minecart;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HopperMinecartMetadata extends AbstractMinecartContainerMetadata {

    public static final byte OFFSET = AbstractMinecartContainerMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public HopperMinecartMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    @Override
    public int getObjectData() {
        return 5;
    }
}