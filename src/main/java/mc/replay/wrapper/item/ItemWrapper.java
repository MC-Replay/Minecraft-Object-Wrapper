package mc.replay.wrapper.item;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.IntTag;
import com.github.steveice10.opennbt.tag.builtin.ListTag;
import com.github.steveice10.opennbt.tag.builtin.Tag;
import mc.replay.packetlib.data.Item;
import mc.replay.wrapper.item.deserializer.ItemDeserializer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ItemWrapper extends Item {

    public ItemWrapper(int materialId, byte amount, @Nullable CompoundTag meta) {
        super(materialId, amount, meta);
    }

    public ItemWrapper(Item item) {
        this(item.materialId(), item.amount(), item.meta());
    }

    public ItemWrapper(@NotNull ItemStack itemStack) {
        this(of(itemStack));
    }

    private static @NotNull Item of(@NotNull ItemStack itemStack) {
        int materialId = itemStack.getType().ordinal();
        byte amount = (byte) itemStack.getAmount();

        CompoundTag meta = new CompoundTag("");
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            CompoundTag displayTag = ItemDeserializer.readDisplay(itemMeta);
            if (!displayTag.isEmpty()) {
                meta.put(displayTag);
            }

            IntTag hideFlagsTag = ItemDeserializer.readHideFlags(itemMeta);
            if (hideFlagsTag != null) {
                meta.put(hideFlagsTag);
            }

            ListTag enchantmentTag = ItemDeserializer.readEnchantments(itemMeta);
            if (enchantmentTag != null && enchantmentTag.size() > 0) {
                meta.put(enchantmentTag);
            }

            Tag tag = ItemDeserializer.readItemMeta(itemMeta);
            if (tag != null) {
                meta.put(tag);
            }
        }

        return new Item(materialId, amount, (meta.isEmpty()) ? null : meta);
    }
}