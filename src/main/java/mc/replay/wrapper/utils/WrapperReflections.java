package mc.replay.wrapper.utils;

import com.google.common.collect.ForwardingMultimap;
import mc.replay.packetlib.data.PlayerProfileProperty;
import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.packetlib.network.PacketBuffer;
import mc.replay.packetlib.utils.ReflectionUtils;
import mc.replay.packetlib.utils.Reflections;
import mc.replay.wrapper.data.PlayerProfile;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public final class WrapperReflections {

    private WrapperReflections() {
    }

    private static Class<?> ENTITY;
    private static Class<?> ENTITY_HUMAN;
    private static Class<?> DATA_WATCHER;
    private static Class<?> DATA_WATCHER_ITEM;
    private static Class<?> DATA_WATCHER_OBJECT;
    private static Class<?> CRAFT_ENTITY;

    private static MethodHandle GET_ENTITY_HANDLE_METHOD;

    private static Field ENTITY_COUNT_FIELD;

    private static Field DATA_WATCHER_FIELD;
    private static Method GET_DATA_WATCHER_ITEMS_METHOD;
    private static Field GET_DATA_WATCHER_ITEM_OBJECT_FIELD;
    private static Field GET_DATA_WATCHER_ITEM_VALUE_FIELD;
    private static Field GET_DATA_WATCHER_OBJECT_SERIALIZER_ID_FIELD;

    private static Field GAME_PROFILE_ENTITY_PLAYER_FIELD;
    private static Field GAME_PROFILE_SKULL_META_FIELD;
    private static Field GAME_PROFILE_UUID_FIELD;
    private static Field GAME_PROFILE_NAME_FIELD;
    private static Field GAME_PROFILE_PROPERTIES_FIELD;
    private static Field PROPERTY_NAME_FIELD;
    private static Field PROPERTY_VALUE_FIELD;
    private static Field PROPERTY_SIGNATURE_FIELD;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();

            ENTITY = ReflectionUtils.nmsClass("world.entity", "Entity");
            ENTITY_HUMAN = ReflectionUtils.nmsClass("world.entity.player", "EntityHuman");
            DATA_WATCHER = ReflectionUtils.nmsClass("network.syncher", "DataWatcher");
            DATA_WATCHER_ITEM = ReflectionUtils.nmsClass("network.syncher", "DataWatcher$Item");
            DATA_WATCHER_OBJECT = ReflectionUtils.nmsClass("network.syncher", "DataWatcherObject");
            CRAFT_ENTITY = ReflectionUtils.obcClass("entity.CraftEntity");

            GET_ENTITY_HANDLE_METHOD = lookup.findVirtual(CRAFT_ENTITY, "getHandle", MethodType.methodType(ENTITY));

            ENTITY_COUNT_FIELD = ReflectionUtils.getField(ENTITY, "entityCount"); // TODO fix for other versions

            Class<?> CRAFT_META_SKULL = ReflectionUtils.obcClass("inventory.CraftMetaSkull");
            Class<?> GAME_PROFILE = ReflectionUtils.getClass("com.mojang.authlib.GameProfile");
            Class<?> PROPERTY = ReflectionUtils.getClass("com.mojang.authlib.properties.Property");

            DATA_WATCHER_FIELD = ReflectionUtils.getField(ENTITY, "datawatcher"); // TODO fix for other versions
            GET_DATA_WATCHER_ITEMS_METHOD = DATA_WATCHER.getMethod("c");
            GET_DATA_WATCHER_ITEM_OBJECT_FIELD = ReflectionUtils.getField(DATA_WATCHER_ITEM, "a");
            GET_DATA_WATCHER_ITEM_VALUE_FIELD = ReflectionUtils.getField(DATA_WATCHER_ITEM, "b");
            GET_DATA_WATCHER_OBJECT_SERIALIZER_ID_FIELD = ReflectionUtils.getField(DATA_WATCHER_OBJECT, "a");

            GAME_PROFILE_ENTITY_PLAYER_FIELD = ReflectionUtils.getField(ENTITY_HUMAN, "bJ"); // TODO fix for other versions
            GAME_PROFILE_SKULL_META_FIELD = ReflectionUtils.getField(CRAFT_META_SKULL, "profile");
            GAME_PROFILE_UUID_FIELD = ReflectionUtils.getField(GAME_PROFILE, "id");
            GAME_PROFILE_NAME_FIELD = ReflectionUtils.getField(GAME_PROFILE, "name");
            GAME_PROFILE_PROPERTIES_FIELD = ReflectionUtils.getField(GAME_PROFILE, "properties");
            PROPERTY_NAME_FIELD = ReflectionUtils.getField(PROPERTY, "name");
            PROPERTY_VALUE_FIELD = ReflectionUtils.getField(PROPERTY, "value");
            PROPERTY_SIGNATURE_FIELD = ReflectionUtils.getField(PROPERTY, "signature");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static PlayerProfile getGameProfile(@NotNull Object gameProfile) {
        try {
            UUID uuid = (UUID) GAME_PROFILE_UUID_FIELD.get(gameProfile);
            String name = (String) GAME_PROFILE_NAME_FIELD.get(gameProfile);
            ForwardingMultimap<String, Object> properties = (ForwardingMultimap<String, Object>) GAME_PROFILE_PROPERTIES_FIELD.get(gameProfile);
            Map<String, PlayerProfileProperty> propertyMap = new HashMap<>();

            for (Map.Entry<String, Object> entry : properties.entries()) {
                propertyMap.put(entry.getKey(), getPropertyFromPropertyObject(entry.getValue()));
            }

            return new PlayerProfile(uuid, name, propertyMap);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static PlayerProfile getGameProfileFromSkullMeta(@NotNull SkullMeta skullMeta) {
        try {
            return getGameProfile(GAME_PROFILE_SKULL_META_FIELD.get(skullMeta));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static PlayerProfile getGameProfileFromPlayer(@NotNull Player player) {
        try {
            Object entityPlayer = Reflections.getEntityPlayer(player);
            return getGameProfile(GAME_PROFILE_ENTITY_PLAYER_FIELD.get(entityPlayer));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked, rawtypes")
    public static Map<Integer, Metadata.Entry<?>> readDataWatcher(@NotNull Entity entity) {
        Map<Integer, Metadata.Entry<?>> entries = new HashMap<>();
        try {
            Object nmsEntity = getNMSEntity(entity);
            Object dataWatcher = DATA_WATCHER_FIELD.get(nmsEntity);

            List<Object> items = (List<Object>) GET_DATA_WATCHER_ITEMS_METHOD.invoke(dataWatcher);
            for (int i = 0; i < items.size(); i++) {
                Object item = items.get(i);

                int type = getSerializerId(item);
                Object value = GET_DATA_WATCHER_ITEM_VALUE_FIELD.get(item);
                PacketBuffer.Type<?> serializer = Metadata.getSerializer(type);

                Metadata.Entry entry = new Metadata.Entry(type, value, serializer);
                entries.put(i, entry);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return entries;
    }

    public static int getNewEntityId() {
        try {
            AtomicInteger entityCount = (AtomicInteger) ENTITY_COUNT_FIELD.get(null);
            return entityCount.getAndIncrement();
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static int getSerializerId(Object item) throws IllegalAccessException {
        Object dataWatcherObject = GET_DATA_WATCHER_ITEM_OBJECT_FIELD.get(item);
        return (int) GET_DATA_WATCHER_OBJECT_SERIALIZER_ID_FIELD.get(dataWatcherObject);
    }

    private static Object getNMSEntity(@NotNull Entity entity) throws Throwable {
        return GET_ENTITY_HANDLE_METHOD.invoke(entity);
    }

    private static PlayerProfileProperty getPropertyFromPropertyObject(@NotNull Object propertyObject) throws IllegalAccessException {
        String name = (String) PROPERTY_NAME_FIELD.get(propertyObject);
        String value = (String) PROPERTY_VALUE_FIELD.get(propertyObject);
        String signature = (String) PROPERTY_SIGNATURE_FIELD.get(propertyObject);
        return new PlayerProfileProperty(name, value, signature);
    }
}