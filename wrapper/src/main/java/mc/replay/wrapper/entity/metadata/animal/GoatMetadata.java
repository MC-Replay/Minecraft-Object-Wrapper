package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GoatMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public static final int SCREAMING_INDEX = OFFSET;

    public GoatMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setScreaming(boolean value) {
        super.metadata.setIndex(SCREAMING_INDEX, Metadata.Boolean(value));
    }

    public boolean isScreaming() {
        return super.metadata.getIndex(SCREAMING_INDEX, false);
    }
}