package mc.replay.wrapper.entity.metadata.villager;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.AgeableMobMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AbstractVillagerMetadata extends AgeableMobMetadata {

    public static final int OFFSET = AgeableMobMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    protected AbstractVillagerMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setHeadShakeTimer(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public int getHeadShakeTimer() {
        return super.metadata.getIndex(OFFSET, 0);
    }
}