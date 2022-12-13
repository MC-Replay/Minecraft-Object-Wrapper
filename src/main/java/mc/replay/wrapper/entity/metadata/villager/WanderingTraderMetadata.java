package mc.replay.wrapper.entity.metadata.villager;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WanderingTraderMetadata extends VillagerMetadata {

    public static final byte OFFSET = VillagerMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public WanderingTraderMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}