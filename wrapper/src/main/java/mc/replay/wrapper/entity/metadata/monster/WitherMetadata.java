package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WitherMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 4;

    public static final int CENTER_HEAD_INDEX = OFFSET;
    public static final int LEFT_HEAD_INDEX = OFFSET + 1;
    public static final int RIGHT_HEAD_INDEX = OFFSET + 2;
    public static final int INVULNERABLE_TIME_INDEX = OFFSET + 3;

    private EntityWrapper centerHead;
    private EntityWrapper leftHead;
    private EntityWrapper rightHead;

    public WitherMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setCenterHead(@Nullable EntityWrapper value) {
        this.centerHead = value;
        super.metadata.setIndex(CENTER_HEAD_INDEX, Metadata.VarInt((value == null) ? 0 : value.getEntityId()));
    }

    public void setLeftHead(@Nullable EntityWrapper value) {
        this.leftHead = value;
        super.metadata.setIndex(LEFT_HEAD_INDEX, Metadata.VarInt((value == null) ? 0 : value.getEntityId()));
    }

    public void setRightHead(@Nullable EntityWrapper value) {
        this.rightHead = value;
        super.metadata.setIndex(RIGHT_HEAD_INDEX, Metadata.VarInt((value == null) ? 0 : value.getEntityId()));
    }

    public void setInvulnerableTime(int value) {
        super.metadata.setIndex(INVULNERABLE_TIME_INDEX, Metadata.VarInt(value));
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
        return super.metadata.getIndex(INVULNERABLE_TIME_INDEX, 0);
    }
}