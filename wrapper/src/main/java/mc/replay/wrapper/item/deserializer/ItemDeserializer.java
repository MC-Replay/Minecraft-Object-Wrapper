package mc.replay.wrapper.item.deserializer;

import com.github.steveice10.opennbt.tag.builtin.*;
import mc.replay.packetlib.utils.ProtocolVersion;
import mc.replay.wrapper.utils.AdventureUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ItemDeserializer {

    private ItemDeserializer() {
    }

    public static @NotNull CompoundTag readDisplay(@NotNull ItemMeta meta) {
        CompoundTag displayTag = new CompoundTag("display");
        if (meta.hasDisplayName()) {
            Component component = AdventureUtils.legacy(meta.getDisplayName());
            displayTag.put(new StringTag("Name", AdventureUtils.gson(component)));
        }

        if (meta.getLore() != null && !meta.getLore().isEmpty()) {
            List<Tag> lore = new ArrayList<>();

            for (String loreLine : meta.getLore()) {
                Component component = AdventureUtils.legacy(loreLine);
                lore.add(new StringTag("", AdventureUtils.gson(component)));
            }

            displayTag.put(new ListTag("Lore", lore));
        }

        if (meta instanceof LeatherArmorMeta leatherArmorMeta) {
            displayTag.put(new IntTag("color", leatherArmorMeta.getColor().asRGB()));
        }

        return displayTag;
    }

    public static @Nullable IntTag readHideFlags(@NotNull ItemMeta meta) {
        if (meta.getItemFlags().isEmpty()) return null;

        int result = 0;
        for (ItemFlag itemFlag : meta.getItemFlags()) {
            result |= 1 << itemFlag.ordinal();
        }

        return new IntTag("HideFlags", result);
    }

    public static @Nullable ListTag readEnchantments(@NotNull ItemMeta meta) {
        if (!meta.hasEnchants() || meta instanceof SkullMeta) return null;

        ListTag enchantments = new ListTag("Enchantments");

        for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
            CompoundTag enchantmentTag = readEnchantment(entry.getKey(), entry.getValue().shortValue());
            enchantments.add(enchantmentTag);
        }

        return enchantments;
    }

    public static @Nullable Tag readItemMeta(@NotNull ItemMeta meta) {
        if (meta instanceof LeatherArmorMeta) return null;

        Tag tag = null;
        if (ProtocolVersion.getServerVersion().isHigherOrEqual(ProtocolVersion.MINECRAFT_1_17) && meta instanceof AxolotlBucketMeta axolotlBucketMeta) {
            tag = AxolotlBucketMetaDeserializer.readAxolotlBucketMeta(axolotlBucketMeta);
        } else if (meta instanceof BannerMeta bannerMeta) {
            tag = BannerMetaDeserializer.readBannerMeta(bannerMeta);
        } else if (meta instanceof BookMeta bookMeta) {
            //            tag = readBookMeta(bookMeta);
        } else if (ProtocolVersion.getServerVersion().isHigherOrEqual(ProtocolVersion.MINECRAFT_1_17) && meta instanceof BundleMeta bundleMeta) {
            //            tag = readBundleMeta(bundleMeta);
        } else if (meta instanceof CompassMeta compassMeta) {
            //            tag = readCompassMeta(compassMeta);
        } else if (meta instanceof CrossbowMeta crossbowMeta) {
            //            tag = readCrossbowMeta(crossbowMeta);
        } else if (meta instanceof EnchantmentStorageMeta enchantmentStorageMeta) {
            //            tag = readEnchantmentStorageMeta(enchantmentStorageMeta);
        } else if (meta instanceof FireworkEffectMeta fireworkEffectMeta) {
            //            tag = readFireworkEffectMeta(fireworkEffectMeta);
        } else if (meta instanceof FireworkMeta fireworkMeta) {
            //            tag = readFireworkMeta(fireworkMeta);
        } else if (meta instanceof KnowledgeBookMeta knowledgeBookMeta) {
            //            tag = readKnowledgeBookMeta(knowledgeBookMeta);
        } else if (meta instanceof MapMeta mapMeta) {
            //            tag = readMapMeta(mapMeta);
        } else if (meta instanceof PotionMeta potionMeta) {
            //            tag = readPotionMeta(potionMeta);
        } else if (meta instanceof SkullMeta skullMeta) {
            tag = SkullMetaDeserializer.readSkullMeta(skullMeta);
        } else if (meta instanceof SpawnEggMeta spawnEggMeta) {
            //            tag = readSpawnEggMeta(spawnEggMeta);
        } else if (meta instanceof SuspiciousStewMeta suspiciousStewMeta) {
            //            tag = readSuspiciousStewMeta(suspiciousStewMeta);
        } else if (meta instanceof TropicalFishBucketMeta tropicalFishBucketMeta) {
            //            tag = readTropicalFishBucketMeta(tropicalFishBucketMeta);
        }

        return tag;
    }

    static CompoundTag readEnchantment(@NotNull Enchantment enchantment, short level) {
        CompoundTag enchantmentTag = new CompoundTag("");
        enchantmentTag.put(new StringTag("id", enchantment.getKey().getKey()));
        enchantmentTag.put(new ShortTag("lvl", level));
        return enchantmentTag;
    }
}