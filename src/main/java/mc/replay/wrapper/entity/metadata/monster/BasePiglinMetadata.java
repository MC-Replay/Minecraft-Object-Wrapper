package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class BasePiglinMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    protected BasePiglinMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setImmuneToZombification(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isImmuneToZombification() {
        return super.metadata.getIndex(OFFSET, false);
    }
}