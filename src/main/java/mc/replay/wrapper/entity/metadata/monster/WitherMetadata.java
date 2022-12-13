package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WitherMetadata extends MonsterMetadata {

    public static final byte OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 4;

    private EntityWrapper centerHead;
    private EntityWrapper leftHead;
    private EntityWrapper rightHead;

    public WitherMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setCenterHead(@Nullable EntityWrapper value) {
        this.centerHead = value;
        super.metadata.setIndex(OFFSET, Metadata.VarInt((value == null) ? 0 : value.getEntityId()));
    }

    public void setLeftHead(@Nullable EntityWrapper value) {
        this.leftHead = value;
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt((value == null) ? 0 : value.getEntityId()));
    }

    public void setRightHead(@Nullable EntityWrapper value) {
        this.rightHead = value;
        super.metadata.setIndex(OFFSET + 2, Metadata.VarInt((value == null) ? 0 : value.getEntityId()));
    }

    public void setInvulnerableTime(int value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.VarInt(value));
    }

    public @Nullable EntityWrapper getCenterHead() {
        return this.centerHead;
    }

    public @Nullable EntityWrapper getLeftHead() {
        return this.leftHead;
    }

    public @Nullable EntityWrapper getRightHead() {
        return this.rightHead;
    }

    public int getInvulnerableTime() {
        return super.metadata.getIndex(OFFSET + 3, 0);
    }
}