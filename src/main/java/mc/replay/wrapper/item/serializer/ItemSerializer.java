package mc.replay.wrapper.item.serializer;

import com.github.steveice10.opennbt.tag.builtin.*;
import mc.replay.packetlib.utils.ProtocolVersion;
import mc.replay.wrapper.utils.AdventureUtils;
import mc.replay.wrapper.utils.Pair;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class ItemSerializer {

    private ItemSerializer() {
    }

    public static void writeDisplay(@NotNull ItemMeta meta, @NotNull CompoundTag compound) {
        CompoundTag displayTag = compound.get("display");
        if (displayTag == null) return;

        StringTag displayName = displayTag.get("Name");
        if (displayName != null) {
            meta.setDisplayName(AdventureUtils.legacy(AdventureUtils.gson(displayName.getValue())));
        }

        ListTag loreTag = displayTag.get("Lore");
        if (loreTag != null && loreTag.size() > 0) {
            List<String> lore = new ArrayList<>();
            for (Tag tag : loreTag.getValue()) {
                if (tag instanceof StringTag stringTag) {
                    lore.add(AdventureUtils.legacy(AdventureUtils.gson(stringTag.getValue())));
                }
            }

            meta.setLore(lore);
        }

        IntTag colorTag = displayTag.get("color");
        if (colorTag != null && meta instanceof LeatherArmorMeta leatherArmorMeta) {
            leatherArmorMeta.setColor(Color.fromRGB(colorTag.getValue()));
        }
    }

    public static void writeHideFlags(@NotNull ItemMeta meta, @NotNull CompoundTag compound) {
        IntTag hideFlagsTag = compound.get("HideFlags");
        if (hideFlagsTag == null) return;

        int hideFlags = hideFlagsTag.getValue();
        for (ItemFlag itemFlag : ItemFlag.values()) {
            if ((hideFlags & (1 << itemFlag.ordinal())) != 0) {
                meta.addItemFlags(itemFlag);
            }
        }
    }

    public static void writeEnchantments(@NotNull ItemMeta meta, @NotNull CompoundTag compound) {
        if (meta instanceof SkullMeta) return;

        ListTag enchantmentsTag = compound.get("Enchantments");
        if (enchantmentsTag == null) return;

        for (Tag tag : enchantmentsTag.getValue()) {
            if (tag instanceof CompoundTag enchantmentTag) {
                Pair<Enchantment, Short> enchantment = writeEnchantment(enchantmentTag);
                if (enchantment != null) {
                    meta.addEnchant(enchantment.getKey(), enchantment.getValue(), true);
                }
            }
        }
    }

    public static void writeItemMeta(@NotNull ItemMeta meta, @NotNull CompoundTag compound) {
        if (meta instanceof LeatherArmorMeta) return;

        if (ProtocolVersion.getServerVersion().isHigherOrEqual(ProtocolVersion.MINECRAFT_1_17) && meta instanceof AxolotlBucketMeta axolotlBucketMeta) {

        } else if (meta instanceof BannerMeta bannerMeta) {

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
            SkullMetaSerializer.writeSkullMeta(skullMeta, compound);
        } else if (meta instanceof SpawnEggMeta spawnEggMeta) {
            //            tag = readSpawnEggMeta(spawnEggMeta);
        } else if (meta instanceof SuspiciousStewMeta suspiciousStewMeta) {
            //            tag = readSuspiciousStewMeta(suspiciousStewMeta);
        } else if (meta instanceof TropicalFishBucketMeta tropicalFishBucketMeta) {
            //            tag = readTropicalFishBucketMeta(tropicalFishBucketMeta);
        }
    }

    static Pair<Enchantment, Short> writeEnchantment(@NotNull CompoundTag enchantmentTag) {
        StringTag idTag = enchantmentTag.get("id");
        ShortTag levelTag = enchantmentTag.get("lvl");
        if (idTag == null || levelTag == null) return null;

        Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(idTag.getValue()));
        if (enchantment == null) return null;

        return new Pair<>(enchantment, levelTag.getValue());
    }
}