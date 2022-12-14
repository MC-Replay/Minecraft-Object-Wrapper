package mc.replay.wrapper.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

public final class AdventureUtils {

    private AdventureUtils() {
    }

    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().build();

    public static @NotNull Component legacy(@NotNull String text) {
        return LEGACY_SERIALIZER.deserialize(text);
    }
}