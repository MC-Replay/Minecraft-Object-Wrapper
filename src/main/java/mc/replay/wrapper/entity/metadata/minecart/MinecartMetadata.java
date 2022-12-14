package mc.replay.wrapper.entity.metadata.minecart;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MinecartMetadata extends AbstractMinecartMetadata {

    public static final int OFFSET = AbstractMinecartMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    public MinecartMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    @Override
    public int getObjectData() {
        return 0;
    }
}