package mc.replay.wrapper.data;

import mc.replay.packetlib.data.PlayerProfileProperty;
import mc.replay.wrapper.utils.WrapperReflections;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public record PlayerProfile(UUID uuid, String name, Map<String, PlayerProfileProperty> properties) {

    public static @NotNull PlayerProfile fromPlayer(@NotNull Player player) {
        PlayerProfile profile = WrapperReflections.getGameProfileFromPlayer(player);
        if (profile == null) throw new IllegalStateException("Couldn't get profile from player");
        return profile;
    }
}