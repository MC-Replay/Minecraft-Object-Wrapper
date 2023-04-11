package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EndermanMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 3;

    public EndermanMetadata( @NotNull Metadata metadata) {
        super( metadata);
    }

    public void setCarriedBlockId(@Nullable Integer value) {
        super.metadata.setIndex(OFFSET, Metadata.OptBlockID(value));
    }

    public void setScreaming(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public void setStaring(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public @Nullable Integer getCarriedBlockId() {
        return super.metadata.getIndex(OFFSET, null);
    }

    public boolean isScreaming() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public boolean isStaring() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }
}