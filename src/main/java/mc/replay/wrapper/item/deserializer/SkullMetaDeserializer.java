package mc.replay.wrapper.item.deserializer;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.ListTag;
import com.github.steveice10.opennbt.tag.builtin.StringTag;
import com.github.steveice10.opennbt.tag.builtin.Tag;
import mc.replay.packetlib.data.PlayerProfileProperty;
import mc.replay.wrapper.data.PlayerProfile;
import mc.replay.wrapper.utils.NBTUtils;
import mc.replay.wrapper.utils.WrapperReflections;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

final class SkullMetaDeserializer {

    private SkullMetaDeserializer() {
    }

    static @Nullable Tag readSkullMeta(@NotNull SkullMeta skullMeta) {
        PlayerProfile playerProfile = WrapperReflections.getGameProfileFromSkullMeta(skullMeta);
        if (playerProfile == null || playerProfile.properties().isEmpty()) return null;

        CompoundTag skullOwnerTag = new CompoundTag("SkullOwner");
        skullOwnerTag.put(new StringTag("Name", playerProfile.name()));
        skullOwnerTag.put(NBTUtils.uuidToTag("Id", playerProfile.uuid()));

        CompoundTag propertiesTag = new CompoundTag("Properties");
        for (Map.Entry<String, PlayerProfileProperty> entry : playerProfile.properties().entrySet()) {
            ListTag listTag = new ListTag(entry.getKey());

            CompoundTag propertyTag = new CompoundTag("");
            propertyTag.put(new StringTag("Value", entry.getValue().value()));
            if (entry.getValue().signature() != null) {
                propertyTag.put(new StringTag("Signature", entry.getValue().signature()));
            }

            listTag.add(propertyTag);
            propertiesTag.put(listTag);
        }

        skullOwnerTag.put(propertiesTag);

        return skullOwnerTag;
    }
}