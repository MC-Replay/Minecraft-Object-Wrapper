package mc.replay.wrapper.entity.metadata.item;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ThrownExpierenceBottleMetadata extends ItemContainingMetadata {

    public static final byte OFFSET = ItemContainingMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public ThrownExpierenceBottleMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata, Material.EXPERIENCE_BOTTLE);
    }
}