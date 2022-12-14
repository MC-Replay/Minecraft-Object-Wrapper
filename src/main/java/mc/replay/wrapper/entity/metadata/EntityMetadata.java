package mc.replay.wrapper.entity.metadata;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.packetlib.utils.ProtocolVersion;
import mc.replay.packetlib.utils.Reflections;
import mc.replay.wrapper.entity.EntityWrapper;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Pose;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.Map;

public class EntityMetadata {

    public static final int OFFSET = 0;
    public static final int MAX_OFFSET = OFFSET + getMaxOffset();

    private static int getMaxOffset() {
        return (Reflections.VERSION.isEqual(ProtocolVersion.MINECRAFT_1_16_5)) ? 7 : 8;
    }

    private static final byte ON_FIRE_BIT = 0x01;
    private static final byte CROUCHING_BIT = 0x02;
    private static final byte SPRINTING_BIT = 0x08;
    private static final byte SWIMMING_BIT = 0x10;
    private static final byte INVISIBLE_BIT = 0x20;
    private static final byte HAS_GLOWING_EFFECT_BIT = 0x40;
    private static final byte FLYING_WITH_ELYTRA_BIT = (byte) 0x80;

    private final WeakReference<EntityWrapper> entityRef;
    protected final Metadata metadata;

    public EntityMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        this.entityRef = new WeakReference<>(entity);
        this.metadata = metadata;
    }

    public @NotNull Metadata getMetadata() {
        return this.metadata;
    }

    public @NotNull Map<Integer, Metadata.Entry<?>> getEntries() {
        return this.metadata.getEntries();
    }

    public void setOnFire(boolean value) {
        this.setMaskBit(OFFSET, ON_FIRE_BIT, value);
    }

    public void setSneaking(boolean value) {
        this.setMaskBit(OFFSET, CROUCHING_BIT, value);
    }

    public void setSprinting(boolean value) {
        this.setMaskBit(OFFSET, SPRINTING_BIT, value);
    }

    public void setSwimming(boolean value) {
        this.setMaskBit(OFFSET, SWIMMING_BIT, value);
    }

    public void setInvisible(boolean value) {
        this.setMaskBit(OFFSET, INVISIBLE_BIT, value);
    }

    public void setHasGlowingEffect(boolean value) {
        this.setMaskBit(OFFSET, HAS_GLOWING_EFFECT_BIT, value);
    }

    public void setIsFlyingWithElytra(boolean value) {
        this.setMaskBit(OFFSET, FLYING_WITH_ELYTRA_BIT, value);
    }

    public void setAirTicks(int value) {
        this.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public void setCustomName(@NotNull Component value) {
        this.metadata.setIndex(OFFSET + 2, Metadata.Chat(value));
    }

    public void setCustomNameVisible(boolean value) {
        this.metadata.setIndex(OFFSET + 3, Metadata.Boolean(value));
    }

    public void setSilent(boolean value) {
        this.metadata.setIndex(OFFSET + 4, Metadata.Boolean(value));
    }

    public void setHasGravity(boolean value) {
        this.metadata.setIndex(OFFSET + 5, Metadata.Boolean(!value));
    }

    public void setPose(@NotNull Pose value) {
        this.metadata.setIndex(OFFSET + 6, Metadata.Pose(value));
    }

    public void setTickFrozen(int value) {
        if (Reflections.VERSION.isEqual(ProtocolVersion.MINECRAFT_1_16_5)) return;
        this.metadata.setIndex(OFFSET + 7, Metadata.VarInt(value));
    }

    public boolean isOnFire() {
        return this.getMaskBit(OFFSET, ON_FIRE_BIT);
    }

    public boolean isSneaking() {
        return this.getMaskBit(OFFSET, CROUCHING_BIT);
    }

    public boolean isSprinting() {
        return this.getMaskBit(OFFSET, SPRINTING_BIT);
    }

    public boolean isSwimming() {
        return this.getMaskBit(OFFSET, SWIMMING_BIT);
    }

    public boolean isInvisible() {
        return this.getMaskBit(OFFSET, INVISIBLE_BIT);
    }

    public boolean isHasGlowingEffect() {
        return this.getMaskBit(OFFSET, HAS_GLOWING_EFFECT_BIT);
    }

    public boolean isFlyingWithElytra() {
        return this.getMaskBit(OFFSET, FLYING_WITH_ELYTRA_BIT);
    }

    public int getAirTicks() {
        return this.metadata.getIndex(OFFSET + 1, 300);
    }

    public @NotNull Component getCustomName() {
        return this.metadata.getIndex(OFFSET + 2, Component.empty());
    }

    public boolean isCustomNameVisible() {
        return this.metadata.getIndex(OFFSET + 3, false);
    }

    public boolean isSilent() {
        return this.metadata.getIndex(OFFSET + 4, false);
    }

    public boolean hasGravity() {
        return !this.metadata.getIndex(OFFSET + 5, false);
    }

    public @NotNull Pose getPose() {
        return this.metadata.getIndex(OFFSET + 6, Pose.STANDING);
    }

    public int getTickFrozen() {
        if (Reflections.VERSION.isEqual(ProtocolVersion.MINECRAFT_1_16_5)) return 0;
        return this.metadata.getIndex(OFFSET + 7, 0);
    }

    protected byte getMask(int index) {
        return this.metadata.getIndex(index, (byte) 0);
    }

    protected void setMask(int index, byte mask) {
        this.metadata.setIndex(index, Metadata.Byte(mask));
    }

    protected boolean getMaskBit(int index, byte bit) {
        return (this.getMask(index) & bit) == bit;
    }

    protected void setMaskBit(int index, byte bit, boolean value) {
        byte mask = this.getMask(index);
        boolean currentValue = (mask & bit) == bit;
        if (currentValue == value) return;

        if (value) {
            mask |= bit;
        } else {
            mask &= ~bit;
        }

        this.setMask(index, mask);
    }
}