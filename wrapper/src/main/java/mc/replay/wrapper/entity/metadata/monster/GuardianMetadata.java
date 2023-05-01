package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuardianMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    public static final int RETRACTING_SPIKES_INDEX = OFFSET;
    public static final int TARGET_INDEX = OFFSET + 1;

    private EntityWrapper target;

    public GuardianMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setRetractingSpikes(boolean value) {
        super.metadata.setIndex(RETRACTING_SPIKES_INDEX, Metadata.Boolean(value));
    }

    public void setTarget(@Nullable EntityWrapper value) {
        this.target = value;
        super.metadata.setIndex(TARGET_INDEX, Metadata.VarInt((value == null) ? 0 : value.getEntityId()));
    }

    public boolean isRetractingSpikes() {
        return super.metadata.getIndex(RETRACTING_SPIKES_INDEX, false);
    }

    public @Nullable EntityWrapper getTarget() {
        return this.target;
    }
}