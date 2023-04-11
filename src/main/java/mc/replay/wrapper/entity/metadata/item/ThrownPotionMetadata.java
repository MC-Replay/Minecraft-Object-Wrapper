package mc.replay.wrapper.entity.metadata.item;

import mc.replay.packetlib.data.entity.Metadata;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class ThrownPotionMetadata extends ItemContainingMetadata {

    public static final int OFFSET = ItemContainingMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    public ThrownPotionMetadata(@NotNull Metadata metadata) {
        super(metadata, Material.AIR);
    }
}