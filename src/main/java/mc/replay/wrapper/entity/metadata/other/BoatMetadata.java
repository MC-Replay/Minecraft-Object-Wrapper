package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BoatMetadata extends EntityMetadata {

    public static final byte OFFSET = EntityMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 7;

    public BoatMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setTimeSinceLastHit(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public void setForwardDirection(int value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public void setDamageTaken(float value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Float(value));
    }

    public void setType(@NotNull Type value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.VarInt(value.ordinal()));
    }

    public void setLeftPaddleTurning(boolean value) {
        super.metadata.setIndex(OFFSET + 4, Metadata.Boolean(value));
    }

    public void setRightPaddleTurning(boolean value) {
        super.metadata.setIndex(OFFSET + 5, Metadata.Boolean(value));
    }

    public void setSplashTimer(int value) {
        super.metadata.setIndex(OFFSET + 6, Metadata.VarInt(value));
    }

    public int getTimeSinceLastHit() {
        return super.metadata.getIndex(OFFSET, 0);
    }

    public int getForwardDirection() {
        return super.metadata.getIndex(OFFSET + 1, 1);
    }

    public float getDamageTaken() {
        return super.metadata.getIndex(OFFSET + 2, 0);
    }

    public @NotNull Type getType() {
        return Type.VALUES[super.metadata.getIndex(OFFSET + 3, 0)];
    }

    public boolean isLeftPaddleTurning() {
        return super.metadata.getIndex(OFFSET + 4, false);
    }

    public boolean isRightPaddleTurning() {
        return super.metadata.getIndex(OFFSET + 5, false);
    }

    public int getSplashTimer() {
        return super.metadata.getIndex(OFFSET + 6, 0);
    }

    public enum Type {

        OAK,
        SPRUCE,
        BIRCH,
        JUNGLE,
        ACACIA,
        DARK_OAK;

        private static final Type[] VALUES = values();
    }
}