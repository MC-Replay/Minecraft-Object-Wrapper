package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.MobMetadata;
import org.jetbrains.annotations.NotNull;

public class EnderDragonMetadata extends MobMetadata {

    public static final int OFFSET = MobMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public EnderDragonMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setPhase(@NotNull Phase value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value.ordinal()));
    }

    public @NotNull Phase getPhase() {
        return Phase.VALUES[super.metadata.getIndex(OFFSET, 0)];
    }

    public enum Phase {

        CIRCLING,
        STRAFING,
        FLYING_TO_THE_PORTAL,
        LANDING_ON_THE_PORTAL,
        TAKING_OFF_FROM_THE_PORTAL,
        BREATH_ATTACK,
        LOOKING_FOR_BREATH_ATTACK_PLAYER,
        ROAR,
        CHARGING_PLAYER,
        FLYING_TO_THE_PORTAL_TO_DIE,
        HOVERING_WITHOUT_AI;

        private static final Phase[] VALUES = values();
    }
}