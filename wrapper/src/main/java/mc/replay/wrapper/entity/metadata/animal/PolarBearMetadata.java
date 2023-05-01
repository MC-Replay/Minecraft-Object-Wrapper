package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PolarBearMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public static final int STANDING_UP_INDEX = OFFSET;

    public PolarBearMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setStandingUp(boolean value) {
        super.metadata.setIndex(STANDING_UP_INDEX, Metadata.Boolean(value));
    }

    public boolean isStandingUp() {
        return super.metadata.getIndex(STANDING_UP_INDEX, false);
    }
}