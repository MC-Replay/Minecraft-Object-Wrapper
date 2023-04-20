package mc.replay.wrapper.item.deserializer;

import com.github.steveice10.opennbt.tag.builtin.IntTag;
import com.github.steveice10.opennbt.tag.builtin.Tag;
import org.bukkit.inventory.meta.AxolotlBucketMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class AxolotlBucketMetaDeserializer {

    private AxolotlBucketMetaDeserializer() {
    }

    static @Nullable Tag readAxolotlBucketMeta(@NotNull AxolotlBucketMeta axolotlBucketMeta) {
        if (!axolotlBucketMeta.hasVariant()) return null;

        return new IntTag("BucketVariantType", axolotlBucketMeta.getVariant().ordinal());
    }
}