package mc.replay.wrapper.item;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.IntTag;
import com.github.steveice10.opennbt.tag.builtin.ListTag;
import com.github.steveice10.opennbt.tag.builtin.Tag;
import mc.replay.packetlib.data.Item;
import mc.replay.packetlib.utils.ProtocolVersion;
import mc.replay.wrapper.item.deserializer.ItemDeserializer;
import mc.replay.wrapper.item.serializer.ItemSerializer;
import mc.replay.wrapper.mappings.objects.MaterialMapping;
import mc.replay.wrapper.utils.WrapperMappingsUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ItemWrapper extends Item {

    private ItemStack itemStack;

    public ItemWrapper(int materialId, byte amount, @Nullable CompoundTag meta) {
        super(materialId, amount, meta);
    }

    public ItemWrapper(Item item) {
        this(item.materialId(), item.amount(), item.meta());
    }

    public ItemWrapper(@NotNull ProtocolVersion protocolVersion, @NotNull ItemStack itemStack) {
        this(of(protocolVersion, itemStack));
        this.itemStack = itemStack;
    }

    public ItemWrapper(@NotNull ItemStack itemStack) {
        this(ProtocolVersion.getServerVersion(), itemStack);
    }

    public @NotNull ItemStack toItemStack(@NotNull ProtocolVersion protocolVersion) {
        if (this.itemStack != null) return this.itemStack;

        MaterialMapping materialMapping = WrapperMappingsUtils.getMaterialMapping(protocolVersion, this.materialId());
        Material material = Material.getMaterial(materialMapping.key());
        ItemStack itemStack = new ItemStack(material, this.amount());

        CompoundTag meta = this.meta();
        if (meta != null) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta == null) return itemStack;

            ItemSerializer.writeDisplay(itemMeta, meta);
            ItemSerializer.writeHideFlags(itemMeta, meta);
            ItemSerializer.writeEnchantments(itemMeta, meta);
            ItemSerializer.writeItemMeta(itemMeta, meta);

            itemStack.setItemMeta(itemMeta);
        }

        return this.itemStack = itemStack;
    }

    public @NotNull ItemStack toItemStack() {
        return this.toItemStack(ProtocolVersion.getServerVersion());
    }

    private static @NotNull Item of(@NotNull ProtocolVersion protocolVersion, @NotNull ItemStack itemStack) {
        int materialId = WrapperMappingsUtils.getMaterialMapping(protocolVersion, itemStack.getType()).id();
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