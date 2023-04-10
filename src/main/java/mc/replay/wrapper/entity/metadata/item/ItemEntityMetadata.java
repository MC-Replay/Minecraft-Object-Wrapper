package mc.replay.wrapper.entity.metadata.item;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemEntityMetadata extends ItemContainingMetadata implements ObjectDataProvider {

    public static final int OFFSET = ItemContainingMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    public ItemEntityMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata, Material.AIR);
    }

    @Override
    public int getObjectData() {
        return 0;
    }

    @Override
    public boolean requiresVelocityPacketAtSpawn() {
        return true;
    }
}