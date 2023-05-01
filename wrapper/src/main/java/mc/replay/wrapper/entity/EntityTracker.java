package mc.replay.wrapper.entity;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class EntityTracker {

    public static final Map<Integer, EntityWrapper> ENTITY_CACHE = new HashMap<>();

    private EntityTracker() {
    }

    public static @Nullable EntityWrapper getEntityWrapper(int entityId) {
        return ENTITY_CACHE.get(entityId);
    }

    public static @Nullable EntityWrapper findEntityWrapper(@Nullable World bukkitWorld, int entityId, boolean shouldCache) {
        Entity entity = findEntity(bukkitWorld, entityId);
        if (entity == null) return null;

        EntityWrapper entityWrapper = new EntityWrapper(entity);
        if (shouldCache) {
            try {
                ENTITY_CACHE.put(entityId, entityWrapper);
            } catch (Exception ignored) {
            }
        }

        return entityWrapper;
    }

    public static @Nullable Entity findEntity(@Nullable World bukkitWorld, int entityId) {
        for (World world : (bukkitWorld == null) ? Bukkit.getServer().getWorlds() : Set.of(bukkitWorld)) {
            for (Entity entity : world.getEntities()) {
                if (entity.getEntityId() == entityId) {
                    return entity;
                }
            }
        }

        return null;
    }
}