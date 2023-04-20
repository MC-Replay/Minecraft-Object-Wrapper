package mc.replay.wrapper.entity.metadata.monster.raider;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.monster.MonsterMetadata;
import org.jetbrains.annotations.NotNull;

public class RaiderMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    protected RaiderMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setCelebrating(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isCelebrating() {
        return super.metadata.getIndex(OFFSET, false);
    }
}