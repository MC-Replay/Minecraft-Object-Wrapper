package mc.replay.wrapper.entity.metadata.item;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.item.ItemWrapper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

class ItemContainingMetadata extends EntityMetadata {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public static final int ITEM_INDEX = OFFSET;

    private final ItemWrapper defaultItem;

    public ItemContainingMetadata(@NotNull Metadata metadata, @NotNull Material defaultItemMaterial) {
        super(metadata);
        this.defaultItem = new ItemWrapper(new ItemStack(defaultItemMaterial));
    }

    public void setItem(@NotNull ItemWrapper value) {
        super.metadata.setIndex(ITEM_INDEX, Metadata.Slot(value));
    }

    public @NotNull ItemWrapper getItem() {
        return super.metadata.getIndex(ITEM_INDEX, this.defaultItem);
    }
}