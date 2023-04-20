package mc.replay.wrapper.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

public final class AdventureUtils {

    private AdventureUtils() {
    }

    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().build();
    private static final GsonComponentSerializer GSON_SERIALIZER = GsonComponentSerializer.builder().build();

    public static @NotNull Component legacy(@NotNull String text) {
        return LEGACY_SERIALIZER.deserialize(text);
    }

    public static @NotNull String legacy(@NotNull Component component) {
        return LEGACY_SERIALIZER.serialize(component);
    }

    public static @NotNull Component gson(@NotNull String text) {
        return GSON_SERIALIZER.deserialize(text);
    }

    public static @NotNull String gson(@NotNull Component component) {
        return GSON_SERIALIZER.serialize(component);
    }
}