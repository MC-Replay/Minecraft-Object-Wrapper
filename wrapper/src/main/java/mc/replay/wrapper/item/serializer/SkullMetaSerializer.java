package mc.replay.wrapper.item.serializer;

import com.github.steveice10.opennbt.tag.builtin.*;
import mc.replay.packetlib.data.PlayerProfileProperty;
import mc.replay.wrapper.data.PlayerProfile;
import mc.replay.wrapper.utils.NBTUtils;
import mc.replay.wrapper.utils.WrapperReflections;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

final class SkullMetaSerializer {

    private SkullMetaSerializer() {
    }

    static void writeSkullMeta(@NotNull SkullMeta meta, @NotNull CompoundTag compound) {
        CompoundTag skullOwnerTag = compound.get("SkullOwner");
        if (skullOwnerTag == null) return;

        StringTag nameTag = skullOwnerTag.get("Name");
        IntArrayTag idTag = skullOwnerTag.get("Id");
        if (nameTag == null || idTag == null) return;

        String name = nameTag.getValue();
        UUID id = NBTUtils.uuidFromTag(idTag);

        CompoundTag propertiesTag = compound.get("Properties");
        if (propertiesTag == null) return;

        for (Map.Entry<String, Tag> entry : propertiesTag.getValue().entrySet()) {
            ListTag listTag = (ListTag) entry.getValue();
            if (listTag == null) continue;

            Map<String, PlayerProfileProperty> properties = new HashMap<>();
            for (Tag tag : listTag.getValue()) {
                CompoundTag propertyTag = (CompoundTag) tag;
                StringTag valueTag = propertyTag.get("Value");
                StringTag signatureTag = propertyTag.get("Signature");

                PlayerProfileProperty property = new PlayerProfileProperty(
                        entry.getKey(),
                        valueTag.getValue(),
                        signatureTag == null ? null : signatureTag.getValue()
                );

                properties.put(entry.getKey(), property);
            }

            PlayerProfile playerProfile = new PlayerProfile(id, name, properties);
            WrapperReflections.setPlayerProfileToSkullMeta(meta, playerProfile);
        }
    }
}