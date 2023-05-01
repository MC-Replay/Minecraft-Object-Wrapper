package mc.replay.wrapper.entity;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class EntityTracker {

    public final Map<Integer, EntityWrapper> entityCache = new HashMap<>();

    public @Nullable EntityWrapper getEntityWrapper(int entityId) {
        return entityCache.get(entityId);
    }

    public @Nullable EntityWrapper getOrFindEntityWrapper(@Nullable World bukkitWorld, int entityId, boolean shouldCache) {
        EntityWrapper entityWrapper = this.getEntityWrapper(entityId);
        if (entityWrapper != null) return entityWrapper;

        return this.findEntityWrapper(bukkitWorld, entityId, shouldCache);
    }

    public @Nullable EntityWrapper findEntityWrapper(@Nullable World bukkitWorld, int entityId, boolean shouldCache) {
        Entity entity = findEntity(bukkitWorld, entityId);
        if (entity == null) return null;

        EntityWrapper entityWrapper = new EntityWrapper(entity);
        if (shouldCache) {
            try {
                entityCache.put(entityId, entityWrapper);
            } catch (Exception ignored) {
            }
        }

        return entityWrapper;
    }

    public @Nullable Entity findEntity(@Nullable World bukkitWorld, int entityId) {
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