package mc.replay.wrapper.entity.metadata;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.packetlib.data.entity.PlayerHand;
import mc.replay.wrapper.entity.EntityWrapper;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LivingEntityMetadata extends EntityMetadata {

    public static final byte OFFSET = EntityMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 7;

    private static final byte IS_HAND_ACTIVE_BIT = 0x01;
    private static final byte ACTIVE_HAND_BIT = 0x02;
    private static final byte IS_IN_SPIN_ATTACK_BIT = 0x04;

    public LivingEntityMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setHandActive(boolean value) {
        this.setMaskBit(OFFSET, IS_HAND_ACTIVE_BIT, value);
    }

    public void setActiveHand(@NotNull PlayerHand hand) {
        this.setMaskBit(OFFSET, ACTIVE_HAND_BIT, hand == PlayerHand.OFF_HAND);
    }

    public void setInRiptideSpinAttack(boolean value) {
        this.setMaskBit(OFFSET, IS_IN_SPIN_ATTACK_BIT, value);
    }

    public void setHealth(float value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Float(value));
    }

    public void setPotionEffectColor(int value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.VarInt(value));
    }

    public void setPotionEffectAmbient(boolean value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.Boolean(value));
    }

    public void setArrowCount(int value) {
        super.metadata.setIndex(OFFSET + 4, Metadata.VarInt(value));
    }

    public void setBeeStingerCount(int value) {
        super.metadata.setIndex(OFFSET + 5, Metadata.VarInt(value));
    }

    public void setBedInWhichSleepingPosition(@Nullable Vector value) {
        super.metadata.setIndex(OFFSET + 6, Metadata.OptPosition(value));
    }

    public boolean isHandActive() {
        return this.getMaskBit(OFFSET, IS_HAND_ACTIVE_BIT);
    }

    public @NotNull PlayerHand getActiveHand() {
        return this.getMaskBit(OFFSET, ACTIVE_HAND_BIT) ? PlayerHand.OFF_HAND : PlayerHand.MAIN_HAND;
    }

    public boolean isInRiptideSpinAttack() {
        return this.getMaskBit(OFFSET, IS_IN_SPIN_ATTACK_BIT);
    }

    public float getHealth() {
        return super.metadata.getIndex(OFFSET + 1, 1f);
    }

    public int getPotionEffectColor() {
        return super.metadata.getIndex(OFFSET + 2, 0);
    }

    public boolean isPotionEffectAmbient() {
        return super.metadata.getIndex(OFFSET + 3, false);
    }

    public int getArrowCount() {
        return super.metadata.getIndex(OFFSET + 4, 0);
    }

    public int getBeeStingerCount() {
        return super.metadata.getIndex(OFFSET + 5, 0);
    }

    public @Nullable Vector getBedInWhichSleepingPosition() {
        return super.metadata.getIndex(OFFSET + 6, null);
    }
}