package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ZoglinMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public ZoglinMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setBaby(boolean value) {
        if (this.isBaby() == value) return;
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isBaby() {
        return super.metadata.getIndex(OFFSET, false);
    }
}