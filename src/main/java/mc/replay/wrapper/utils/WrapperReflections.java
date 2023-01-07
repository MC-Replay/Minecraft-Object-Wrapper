package mc.replay.wrapper.utils;

import com.google.common.collect.ForwardingMultimap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import mc.replay.packetlib.data.PlayerProfileProperty;
import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.packetlib.network.ReplayByteBuffer;
import mc.replay.packetlib.utils.ReflectionUtils;
import mc.replay.packetlib.utils.Reflections;
import mc.replay.wrapper.data.PlayerProfile;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.String;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static mc.replay.packetlib.data.entity.Metadata.*;

public final class WrapperReflections {

    private WrapperReflections() {
    }

    public static Class<?> ENTITY;
    public static Class<?> ENTITY_HUMAN;
    public static Class<?> DATA_WATCHER;
    public static Class<?> DATA_WATCHER_REGISTRY;
    public static Class<?> DATA_WATCHER_SERIALIZER;
    public static Class<?> DATA_WATCHER_ITEM;
    public static Class<?> DATA_WATCHER_OBJECT;
    public static Class<?> CRAFT_ENTITY;

    public static MethodHandle GET_ENTITY_HANDLE_METHOD;

    public static Field ENTITY_COUNT_FIELD;

    public static Field DATA_WATCHER_FIELD;
    public static Method GET_DATA_WATCHER_ITEMS_METHOD;
    public static Field GET_DATA_WATCHER_ITEM_OBJECT_FIELD;
    public static Field GET_DATA_WATCHER_ITEM_VALUE_FIELD;
    public static Field GET_DATA_WATCHER_OBJECT_INDEX_FIELD;
    public static Method GET_DATA_WATCHER_OBJECT_SERIALIZER_METHOD;
    public static Method GET_DATA_WATCHER_SERIALIZER_TYPE_METHOD;
    public static Method WRITE_DATA_WATCHER_OBJECT_METHOD;

    public static Field GAME_PROFILE_ENTITY_PLAYER_FIELD;
    public static Field GAME_PROFILE_SKULL_META_FIELD;
    public static Field GAME_PROFILE_UUID_FIELD;
    public static Field GAME_PROFILE_NAME_FIELD;
    public static Field GAME_PROFILE_PROPERTIES_FIELD;
    public static Field PROPERTY_NAME_FIELD;
    public static Field PROPERTY_VALUE_FIELD;
    public static Field PROPERTY_SIGNATURE_FIELD;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();

            ENTITY = ReflectionUtils.nmsClass("world.entity", "Entity");
            ENTITY_HUMAN = ReflectionUtils.nmsClass("world.entity.player", "EntityHuman");
            DATA_WATCHER = ReflectionUtils.nmsClass("network.syncher", "DataWatcher");
            DATA_WATCHER_REGISTRY = ReflectionUtils.nmsClass("network.syncher", "DataWatcherRegistry");
            DATA_WATCHER_SERIALIZER = ReflectionUtils.nmsClass("network.syncher", "DataWatcherSerializer");
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
            GET_DATA_WATCHER_OBJECT_INDEX_FIELD = ReflectionUtils.getField(DATA_WATCHER_OBJECT, "a");
            GET_DATA_WATCHER_OBJECT_SERIALIZER_METHOD = DATA_WATCHER_OBJECT.getMethod("b");
            GET_DATA_WATCHER_SERIALIZER_TYPE_METHOD = DATA_WATCHER_REGISTRY.getMethod("b", DATA_WATCHER_SERIALIZER);
            WRITE_DATA_WATCHER_OBJECT_METHOD = DATA_WATCHER_SERIALIZER.getMethod("a", Reflections.PACKET_DATA_SERIALIZER, Object.class);
            WRITE_DATA_WATCHER_OBJECT_METHOD.setAccessible(true);

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
            for (Object item : items) {
                Object dataWatcherSerializer = getSerializer(item);
                int index = getIndex(item);

                int type = getSerializerId(dataWatcherSerializer);
                Object value = GET_DATA_WATCHER_ITEM_VALUE_FIELD.get(item);
                ReplayByteBuffer.Type<?> serializer = Metadata.getSerializer(type);
                if (serializer == null) continue;

                if (value instanceof Optional<?> optional && optional.isEmpty()) {
                    value = null;
                } else if (type != TYPE_BYTE && type != TYPE_VAR_INT && type != TYPE_FLOAT && type != TYPE_STRING
                        && type != TYPE_BOOLEAN) {
                    value = readSpecialValue(value, dataWatcherSerializer, serializer);
                    if (value == null) continue;
                }

                Entry entry = new Entry(type, value, serializer);
                entries.put(index, entry);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return entries;
    }

    public static int getNewEntityId() {
        try {
            AtomicInteger entityCount = (AtomicInteger) ENTITY_COUNT_FIELD.get(null);
            return entityCount.incrementAndGet();
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static int getSerializerId(Object serializer) throws Exception {
        return (int) GET_DATA_WATCHER_SERIALIZER_TYPE_METHOD.invoke(null, serializer);
    }

    private static int getIndex(Object item) throws Exception {
        Object dataWatcherObject = GET_DATA_WATCHER_ITEM_OBJECT_FIELD.get(item);
        return (int) GET_DATA_WATCHER_OBJECT_INDEX_FIELD.get(dataWatcherObject);
    }

    private static Object getSerializer(Object item) throws Exception {
        Object dataWatcherObject = GET_DATA_WATCHER_ITEM_OBJECT_FIELD.get(item);
        return GET_DATA_WATCHER_OBJECT_SERIALIZER_METHOD.invoke(dataWatcherObject);
    }

    private static Object getNMSEntity(@NotNull Entity entity) throws Throwable {
        return GET_ENTITY_HANDLE_METHOD.invoke(entity);
    }

    private static <T> T readSpecialValue(Object value, Object dataWatcherSerializer, ReplayByteBuffer.Type<T> serializer) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocateDirect(2_097_152);

        Object packetDataSerializer = Reflections.createPacketDataSerializer(Unpooled.copiedBuffer(buffer));
        ((ByteBuf) packetDataSerializer).writerIndex(0);
        ((ByteBuf) packetDataSerializer).readerIndex(0);

        WRITE_DATA_WATCHER_OBJECT_METHOD.invoke(dataWatcherSerializer, packetDataSerializer, value);

        ReplayByteBuffer packetBuffer = new ReplayByteBuffer(buffer);
        return packetBuffer.read(serializer);
    }

    private static PlayerProfileProperty getPropertyFromPropertyObject(@NotNull Object propertyObject) throws IllegalAccessException {
        String name = (String) PROPERTY_NAME_FIELD.get(propertyObject);
        String value = (String) PROPERTY_VALUE_FIELD.get(propertyObject);
        String signature = (String) PROPERTY_SIGNATURE_FIELD.get(propertyObject);
        return new PlayerProfileProperty(name, value, signature);
    }
}