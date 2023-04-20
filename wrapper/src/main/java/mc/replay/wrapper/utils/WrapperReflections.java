package mc.replay.wrapper.utils;

import com.google.common.collect.ForwardingMultimap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import mc.replay.packetlib.data.PlayerProfileProperty;
import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.packetlib.network.ReplayByteBuffer;
import mc.replay.packetlib.utils.ProtocolVersion;
import mc.replay.packetlib.utils.ReflectionUtils;
import mc.replay.packetlib.utils.Reflections;
import mc.replay.wrapper.data.PlayerProfile;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.String;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

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
    public static Class<?> PACKET_DATA_SERIALIZER;
    public static Class<?> MINECRAFT_KEY;
    public static Class<?> ENTITY_TYPES;
    public static Class<?> I_REGISTRY;
    public static Class<?> REGISTRY_BLOCKS;
    public static Class<?> BUILT_IN_REGISTRIES_1193;

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

    public static Constructor<?> PACKET_DATA_SERIALIZER_CONSTRUCTOR;
    public static Constructor<?> GAME_PROFILE_CONSTRUCTOR;
    public static Constructor<?> GAME_PROFILE_PROPERTY_CONSTRUCTOR;

    public static Object ENTITY_TYPE_REGISTRY;
    public static Method GET_ENTITY_TYPE_ID_METHOD;
    public static Method GET_ENTITY_TYPE_NAME_METHOD;
    public static Method GET_ENTITY_TYPES_FROM_ID_METHOD;
    public static Method GET_ENTITY_TYPES_FROM_NAME_METHOD;

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
            PACKET_DATA_SERIALIZER = ReflectionUtils.nmsClass("network", "PacketDataSerializer");
            MINECRAFT_KEY = ReflectionUtils.nmsClass("resources", "MinecraftKey");
            ENTITY_TYPES = ReflectionUtils.nmsClass("world.entity", "EntityTypes");
            I_REGISTRY = ReflectionUtils.nmsClass("core", "IRegistry");
            REGISTRY_BLOCKS = ReflectionUtils.nmsClass("core", "RegistryBlocks");
            if (ProtocolVersion.getServerVersion().isHigherOrEqual(ProtocolVersion.MINECRAFT_1_19_3)) {
                BUILT_IN_REGISTRIES_1193 = ReflectionUtils.nmsClass("core.registries", "BuiltInRegistries");
            }

            GET_ENTITY_HANDLE_METHOD = lookup.findVirtual(CRAFT_ENTITY, "getHandle", MethodType.methodType(ENTITY));

            ENTITY_COUNT_FIELD = ReflectionUtils.findFieldEquals(ENTITY, AtomicInteger.class);

            Class<?> craftMetaSkull = ReflectionUtils.obcClass("inventory.CraftMetaSkull");
            Class<?> gameProfile = ReflectionUtils.getClass("com.mojang.authlib.GameProfile");
            Class<?> property = ReflectionUtils.getClass("com.mojang.authlib.properties.Property");

            DATA_WATCHER_FIELD = ReflectionUtils.findFieldEquals(ENTITY, DATA_WATCHER);
            GET_DATA_WATCHER_ITEMS_METHOD = DATA_WATCHER.getMethod("c");
            GET_DATA_WATCHER_ITEM_OBJECT_FIELD = ReflectionUtils.getField(DATA_WATCHER_ITEM, "a");
            GET_DATA_WATCHER_ITEM_VALUE_FIELD = ReflectionUtils.getField(DATA_WATCHER_ITEM, "b");
            GET_DATA_WATCHER_OBJECT_INDEX_FIELD = ReflectionUtils.getField(DATA_WATCHER_OBJECT, "a");
            GET_DATA_WATCHER_OBJECT_SERIALIZER_METHOD = DATA_WATCHER_OBJECT.getMethod("b");
            GET_DATA_WATCHER_SERIALIZER_TYPE_METHOD = DATA_WATCHER_REGISTRY.getMethod("b", DATA_WATCHER_SERIALIZER);
            WRITE_DATA_WATCHER_OBJECT_METHOD = DATA_WATCHER_SERIALIZER.getMethod("a", PACKET_DATA_SERIALIZER, Object.class);
            WRITE_DATA_WATCHER_OBJECT_METHOD.setAccessible(true);

            GAME_PROFILE_ENTITY_PLAYER_FIELD = ReflectionUtils.findFieldEquals(ENTITY_HUMAN, gameProfile);
            GAME_PROFILE_SKULL_META_FIELD = ReflectionUtils.getField(craftMetaSkull, "profile");
            GAME_PROFILE_UUID_FIELD = ReflectionUtils.getField(gameProfile, "id");
            GAME_PROFILE_NAME_FIELD = ReflectionUtils.getField(gameProfile, "name");
            GAME_PROFILE_PROPERTIES_FIELD = ReflectionUtils.getField(gameProfile, "properties");
            PROPERTY_NAME_FIELD = ReflectionUtils.getField(property, "name");
            PROPERTY_VALUE_FIELD = ReflectionUtils.getField(property, "value");
            PROPERTY_SIGNATURE_FIELD = ReflectionUtils.getField(property, "signature");

            PACKET_DATA_SERIALIZER_CONSTRUCTOR = PACKET_DATA_SERIALIZER.getConstructor(ByteBuf.class);
            GAME_PROFILE_CONSTRUCTOR = gameProfile.getConstructor(UUID.class, String.class);
            GAME_PROFILE_PROPERTY_CONSTRUCTOR = property.getConstructor(String.class, String.class, String.class);

            if (ProtocolVersion.getServerVersion().isHigherOrEqual(ProtocolVersion.MINECRAFT_1_19_3)) {
                ENTITY_TYPE_REGISTRY = findAssignableGenericField(BUILT_IN_REGISTRIES_1193, REGISTRY_BLOCKS, ENTITY_TYPES).get(null);
            } else {
                ENTITY_TYPE_REGISTRY = findAssignableGenericField(I_REGISTRY, REGISTRY_BLOCKS, ENTITY_TYPES).get(null);
            }

            GET_ENTITY_TYPE_ID_METHOD = Arrays.stream(I_REGISTRY.getMethods())
                    .filter((method) -> method.getName().equals("a") && method.getParameterCount() == 1 && method.getReturnType() == int.class)
                    .findFirst()
                    .orElseThrow(NoSuchMethodError::new);
            GET_ENTITY_TYPE_NAME_METHOD = Arrays.stream(ENTITY_TYPES.getMethods())
                    .filter((method) -> method.getParameterCount() == 0 && method.getReturnType() == MINECRAFT_KEY)
                    .findFirst()
                    .orElseThrow(NoSuchMethodError::new);
            GET_ENTITY_TYPES_FROM_ID_METHOD = Arrays.stream(I_REGISTRY.getMethods())
                    .filter((method) -> method.getParameterCount() == 1 && method.getParameterTypes()[0].equals(int.class) && method.getReturnType() == Object.class)
                    .findFirst()
                    .orElseThrow(NoSuchMethodError::new);
            GET_ENTITY_TYPES_FROM_NAME_METHOD = ENTITY_TYPES.getMethod("a", String.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static PlayerProfile getPlayerProfile(@NotNull Object gameProfile) {
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

    @SuppressWarnings("unchecked")
    public static Object getGameProfile(@NotNull PlayerProfile profile) {
        try {
            Object gameProfile = GAME_PROFILE_CONSTRUCTOR.newInstance(profile.uuid(), profile.name());
            ForwardingMultimap<String, Object> properties = (ForwardingMultimap<String, Object>) GAME_PROFILE_PROPERTIES_FIELD.get(gameProfile);
            for (PlayerProfileProperty property : profile.properties().values()) {
                properties.put(property.name(), getPropertyObjectFromProperty(property));
            }
            return gameProfile;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static PlayerProfile getPlayerProfileFromSkullMeta(@NotNull SkullMeta skullMeta) {
        try {
            return getPlayerProfile(GAME_PROFILE_SKULL_META_FIELD.get(skullMeta));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void setPlayerProfileToSkullMeta(@NotNull SkullMeta meta, @NotNull PlayerProfile profile) {
        try {
            Object gameProfile = getGameProfile(profile);
            GAME_PROFILE_SKULL_META_FIELD.set(meta, gameProfile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static PlayerProfile getPlayerProfileFromPlayer(@NotNull Player player) {
        try {
            Object entityPlayer = Reflections.getEntityPlayer(player);
            return getPlayerProfile(GAME_PROFILE_ENTITY_PLAYER_FIELD.get(entityPlayer));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public static Object createPacketDataSerializer(ByteBuf buffer) throws Exception {
        return PACKET_DATA_SERIALIZER_CONSTRUCTOR.newInstance(buffer);
    }

    @SuppressWarnings("unchecked, rawtypes")
    public static Map<Integer, Entry<?>> readDataWatcher(@NotNull Entity entity) {
        Map<Integer, Entry<?>> entries = new HashMap<>();
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

    public static EntityType getEntityTypeById(int entityTypeId) {
        try {
            Object entityTypes = GET_ENTITY_TYPES_FROM_ID_METHOD.invoke(ENTITY_TYPE_REGISTRY, entityTypeId);
            String entityKey = GET_ENTITY_TYPE_NAME_METHOD.invoke(entityTypes).toString();
            String key = entityKey.contains("/") ? entityKey.split("\\/")[1] : entityKey.split("\\:")[1];
            NamespacedKey namespacedKey = NamespacedKey.minecraft(key);
            return Registry.ENTITY_TYPE.get(namespacedKey);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public static int getEntityTypeId(@NotNull EntityType entityType) {
        try {
            Object entityTypes = ((Optional<Object>) GET_ENTITY_TYPES_FROM_NAME_METHOD.invoke(null, entityType.getKey().toString()))
                    .orElseThrow();
            return (int) GET_ENTITY_TYPE_ID_METHOD.invoke(ENTITY_TYPE_REGISTRY, entityTypes);
        } catch (Exception exception) {
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
        Object packetDataSerializer = createPacketDataSerializer(Unpooled.buffer());
        ((ByteBuf) packetDataSerializer).writerIndex(0);
        ((ByteBuf) packetDataSerializer).readerIndex(0);

        WRITE_DATA_WATCHER_OBJECT_METHOD.invoke(dataWatcherSerializer, packetDataSerializer, value);

        ByteBuffer byteBuffer = ByteBuffer.wrap(((ByteBuf) packetDataSerializer).array());
        ReplayByteBuffer packetBuffer = new ReplayByteBuffer(byteBuffer);
        return packetBuffer.read(serializer);
    }

    private static PlayerProfileProperty getPropertyFromPropertyObject(@NotNull Object propertyObject) throws IllegalAccessException {
        String name = (String) PROPERTY_NAME_FIELD.get(propertyObject);
        String value = (String) PROPERTY_VALUE_FIELD.get(propertyObject);
        String signature = (String) PROPERTY_SIGNATURE_FIELD.get(propertyObject);
        return new PlayerProfileProperty(name, value, signature);
    }

    private static Object getPropertyObjectFromProperty(@NotNull PlayerProfileProperty property) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        return GAME_PROFILE_PROPERTY_CONSTRUCTOR.newInstance(property.name(), property.value(), property.signature());
    }

    private static Field findField(Class<?> instance, Predicate<Field> predicate) throws NoSuchFieldException {
        return Arrays.stream(instance.getDeclaredFields())
                .filter(predicate)
                .findFirst()
                .orElseThrow(NoSuchFieldException::new);
    }

    private static Field findAssignableGenericField(Class<?> instance, Class<?> assignableClass, Type... argumentTypes) throws NoSuchFieldException {
        return findField(instance, (field) -> {
            if (!assignableClass.isAssignableFrom(field.getType())) return false;
            if (!(field.getGenericType() instanceof ParameterizedType type)) return false;

            for (int i = 0; i < argumentTypes.length; i++) {
                if (type.getActualTypeArguments().length <= i) break;

                if (!type.getActualTypeArguments()[i].getTypeName().startsWith(argumentTypes[i].getTypeName())) {
                    return false;
                }
            }

            return true;
        });
    }
}