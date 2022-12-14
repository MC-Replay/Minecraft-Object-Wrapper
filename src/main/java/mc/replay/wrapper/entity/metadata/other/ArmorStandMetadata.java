package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.LivingEntityMetadata;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArmorStandMetadata extends LivingEntityMetadata {

    public static final int OFFSET = LivingEntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 7;

    private static final byte IS_SMALL_BIT = 0x01;
    private static final byte HAS_ARMS_BIT = 0x04;
    private static final byte HAS_NO_BASE_PLATE_BIT = 0x08;
    private static final byte IS_MARKER_BIT = 0x10;

    public ArmorStandMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setSmall(boolean value) {
        this.setMaskBit(OFFSET, IS_SMALL_BIT, value);
    }

    public void setHasArms(boolean value) {
        this.setMaskBit(OFFSET, HAS_ARMS_BIT, value);
    }

    public void setHasBasePlate(boolean value) {
        this.setMaskBit(OFFSET, HAS_NO_BASE_PLATE_BIT, !value);
    }

    public void setMarker(boolean value) {
        this.setMaskBit(OFFSET, IS_MARKER_BIT, value);
    }

    public void setHeadRotation(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Rotation(value));
    }

    public void setBodyRotation(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Rotation(value));
    }

    public void setLeftArmRotation(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.Rotation(value));
    }

    public void setRightArmRotation(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET + 4, Metadata.Rotation(value));
    }

    public void setLeftLegRotation(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET + 5, Metadata.Rotation(value));
    }

    public void setRightLegRotation(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET + 6, Metadata.Rotation(value));
    }

    public boolean isSmall() {
        return this.getMaskBit(OFFSET, IS_SMALL_BIT);
    }

    public boolean hasArms() {
        return this.getMaskBit(OFFSET, HAS_ARMS_BIT);
    }

    public boolean hasBasePlate() {
        return !this.getMaskBit(OFFSET, HAS_NO_BASE_PLATE_BIT);
    }

    public boolean isMarker() {
        return this.getMaskBit(OFFSET, IS_MARKER_BIT);
    }

    public @NotNull Vector getHeadRotation() {
        return super.metadata.getIndex(OFFSET + 1, new Vector(0, 0, 0));
    }

    public @NotNull Vector getBodyRotation() {
        return super.metadata.getIndex(OFFSET + 2, new Vector(0, 0, 0));
    }

    public @NotNull Vector getLeftArmRotation() {
        return super.metadata.getIndex(OFFSET + 3, new Vector(-10D, 0D, -10D));
    }

    public @NotNull Vector getRightArmRotation() {
        return super.metadata.getIndex(OFFSET + 4, new Vector(-15D, 0D, 10D));
    }

    public @NotNull Vector getLeftLegRotation() {
        return super.metadata.getIndex(OFFSET + 5, new Vector(-1D, 0D, -1D));
    }

    public @NotNull Vector getRightLegRotation() {
        return super.metadata.getIndex(OFFSET + 6, new Vector(1D, 0D, 1D));
    }
}