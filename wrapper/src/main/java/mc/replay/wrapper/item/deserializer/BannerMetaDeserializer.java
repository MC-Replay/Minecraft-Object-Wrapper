package mc.replay.wrapper.item.deserializer;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.ListTag;
import com.github.steveice10.opennbt.tag.builtin.StringTag;
import com.github.steveice10.opennbt.tag.builtin.Tag;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class BannerMetaDeserializer {

    private BannerMetaDeserializer() {
    }

    static @Nullable Tag readBannerMeta(@NotNull BannerMeta bannerMeta) {
        if (bannerMeta.getPatterns().isEmpty()) return null;

        CompoundTag blockEntityTag = new CompoundTag("BlockEntityTag");
        ListTag patternsTag = new ListTag("Patterns");
        for (Pattern pattern : bannerMeta.getPatterns()) {
            CompoundTag patternTag = new CompoundTag("");
            patternTag.put(new StringTag("Color", pattern.getColor().name()));
            patternTag.put(new StringTag("Pattern", pattern.getPattern().getIdentifier()));
            patternsTag.add(patternTag);
        }

        blockEntityTag.put(patternsTag);
        return blockEntityTag;
    }
}