package mc.replay.wrapper.entity.metadata.villager;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.AgeableMobMetadata;
import org.jetbrains.annotations.NotNull;

public class AbstractVillagerMetadata extends AgeableMobMetadata {

    public static final int OFFSET = AgeableMobMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public static final int HEAD_SHAKE_TIMER_INDEX = OFFSET;

    protected AbstractVillagerMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setHeadShakeTimer(int value) {
        super.metadata.setIndex(HEAD_SHAKE_TIMER_INDEX, Metadata.VarInt(value));
    }

    public int getHeadShakeTimer() {
        return super.metadata.getIndex(HEAD_SHAKE_TIMER_INDEX, 0);
    }
}