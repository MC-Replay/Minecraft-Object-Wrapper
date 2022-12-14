package mc.replay.wrapper.entity.metadata.item;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.inventory.ItemWrapper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class ItemContainingMetadata extends EntityMetadata {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    private final ItemWrapper defaultItem;

    public ItemContainingMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata, @NotNull Material defaultItemMaterial) {
        super(entity, metadata);
        this.defaultItem = new ItemWrapper(new ItemStack(defaultItemMaterial));
    }

    public void setItem(@NotNull ItemWrapper value) {
        super.metadata.setIndex(OFFSET, Metadata.Slot(value));
    }

    public @NotNull ItemWrapper getItem() {
        return super.metadata.getIndex(OFFSET, this.defaultItem);
    }
}