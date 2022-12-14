package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuardianMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    private EntityWrapper target;

    public GuardianMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setRetractingSpikes(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public void setTarget(@Nullable EntityWrapper value) {
        this.target = value;
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt((value == null) ? 0 : value.getEntityId()));
    }

    public boolean isRetractingSpikes() {
        return super.metadata.getIndex(OFFSET, false);
    }

    public @Nullable EntityWrapper getTarget() {
        return this.target;
    }
}