package mc.replay.wrapper.entity.metadata;

import com.github.steveice10.opennbt.tag.builtin.Tag;
import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerMetadata extends LivingEntityMetadata {

    public static final int OFFSET = LivingEntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    private final static byte CAPE_BIT = 0x01;
    private final static byte JACKET_BIT = 0x02;
    private final static byte LEFT_SLEEVE_BIT = 0x04;
    private final static byte RIGHT_SLEEVE_BIT = 0x08;
    private final static byte LEFT_LEG_BIT = 0x10;
    private final static byte RIGHT_LEG_BIT = 0x20;
    private final static byte HAT_BIT = 0x40;

    public PlayerMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setAdditionalHearts(float value) {
        super.metadata.setIndex(OFFSET, Metadata.Float(value));
    }

    public void setScore(int value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public void setCapeEnabled(boolean value) {
        this.setMaskBit(OFFSET + 2, CAPE_BIT, value);
    }

    public void setJacketEnabled(boolean value) {
        this.setMaskBit(OFFSET + 2, JACKET_BIT, value);
    }

    public void setLeftSleeveEnabled(boolean value) {
        this.setMaskBit(OFFSET + 2, LEFT_SLEEVE_BIT, value);
    }

    public void setRightSleeveEnabled(boolean value) {
        this.setMaskBit(OFFSET + 2, RIGHT_SLEEVE_BIT, value);
    }

    public void setLeftLegEnabled(boolean value) {
        this.setMaskBit(OFFSET + 2, LEFT_LEG_BIT, value);
    }

    public void setRightLegEnabled(boolean value) {
        this.setMaskBit(OFFSET + 2, RIGHT_LEG_BIT, value);
    }

    public void setHatEnabled(boolean value) {
        this.setMaskBit(OFFSET + 2, HAT_BIT, value);
    }

    public void setRightMainHand(boolean value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.Byte((value) ? (byte) 1 : (byte) 0));
    }

    public void setLeftShouldEntityData(@Nullable Tag value) {
        super.metadata.setIndex(OFFSET + 4, Metadata.NBT(value));
    }

    public void setRightShouldEntityData(@Nullable Tag value) {
        super.metadata.setIndex(OFFSET + 5, Metadata.NBT(value));
    }

    public float getAdditionalHearts() {
        return super.metadata.getIndex(OFFSET, 0);
    }

    public int getScore() {
        return super.metadata.getIndex(OFFSET + 1, 0);
    }

    public boolean isCapeEnabled() {
        return this.getMaskBit(OFFSET + 2, CAPE_BIT);
    }

    public boolean isJacketEnabled() {
        return this.getMaskBit(OFFSET + 2, JACKET_BIT);
    }

    public boolean isLeftSleeveEnabled() {
        return this.getMaskBit(OFFSET + 2, LEFT_SLEEVE_BIT);
    }

    public boolean isRightSleeveEnabled() {
        return this.getMaskBit(OFFSET + 2, RIGHT_SLEEVE_BIT);
    }

    public boolean isLeftLegEnabled() {
        return this.getMaskBit(OFFSET + 2, LEFT_LEG_BIT);
    }

    public boolean isRightLegEnabled() {
        return this.getMaskBit(OFFSET + 2, RIGHT_LEG_BIT);
    }

    public boolean isHatEnabled() {
        return this.getMaskBit(OFFSET + 2, HAT_BIT);
    }

    public boolean isRightMainHand() {
        return super.metadata.getIndex(OFFSET + 3, (byte) 1) == (byte) 1;
    }

    public @Nullable Tag getLeftShoulderEntityData() {
        return super.metadata.getIndex(OFFSET + 4, null);
    }

    public @Nullable Tag getRightShoulderEntityData() {
        return super.metadata.getIndex(OFFSET + 5, null);
    }
}