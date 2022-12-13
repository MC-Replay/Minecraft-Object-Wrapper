package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BasePiglinMetadata extends MonsterMetadata {

    public static final byte OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    protected BasePiglinMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setImmuneToZombification(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isImmuneToZombification() {
        return super.metadata.getIndex(OFFSET, false);
    }
}