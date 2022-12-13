package mc.replay.wrapper.entity.metadata.minecart;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMinecartMetadata extends EntityMetadata implements ObjectDataProvider {

    public static final byte OFFSET = EntityMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 6;

    protected AbstractMinecartMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setShakingPower(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public void setShakingDirection(int value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public void setShakingMultiplier(float value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Float(value));
    }

    public void setCustomBlockIdAndDamage(int value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.VarInt(value));
    }

    public void setCustomBlockYPosition(int value) {
        super.metadata.setIndex(OFFSET + 4, Metadata.VarInt(value));
    }

    public int getShakingPower() {
        return super.metadata.getIndex(OFFSET, 0);
    }

    public int getShakingDirection() {
        return super.metadata.getIndex(OFFSET + 1, 1);
    }

    public float getShakingMultiplier() {
        return super.metadata.getIndex(OFFSET + 2, 0F);
    }

    public int getCustomBlockIdAndDamage() {
        return super.metadata.getIndex(OFFSET + 3, 0);
    }

    public int getCustomBlockYPosition() {
        return super.metadata.getIndex(OFFSET + 4, 6);
    }

    @Override
    public boolean requiresVelocityPacketAtSpawn() {
        return true;
    }
}