package mc.replay.wrapper.entity;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public final class EntityTracker {

    private EntityTracker() {
    }

    public static @Nullable Entity findEntity(int entityId) {
        for (World world : Bukkit.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getEntityId() == entityId) {
                    return entity;
                }
            }
        }

        return null;
    }
}