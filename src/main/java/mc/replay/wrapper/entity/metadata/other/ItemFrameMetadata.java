package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.Item;
import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import mc.replay.wrapper.inventory.ItemWrapper;
import org.bukkit.Rotation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemFrameMetadata extends EntityMetadata implements ObjectDataProvider {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    private Orientation orientation = Orientation.DOWN;

    public ItemFrameMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setItem(@NotNull ItemWrapper value) {
        super.metadata.setIndex(OFFSET, Metadata.Slot(value));
    }

    public void setRotation(@NotNull Rotation value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value.ordinal()));
    }

    public void setOrientation(@NotNull Orientation value) {
        this.orientation = value;
    }

    public @NotNull ItemWrapper getItem() {
        return super.metadata.getIndex(OFFSET, new ItemWrapper(Item.AIR));
    }

    public @NotNull Rotation getRotation() {
        return Rotation.values()[super.metadata.getIndex(OFFSET + 1, 0)];
    }

    public @NotNull Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public int getObjectData() {
        return this.orientation.ordinal();
    }

    @Override
    public boolean requiresVelocityPacketAtSpawn() {
        return false;
    }

    public enum Orientation {

        DOWN,
        UP,
        NORTH,
        SOUTH,
        WEST,
        EAST
    }
}