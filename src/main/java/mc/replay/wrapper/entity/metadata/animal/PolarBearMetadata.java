package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PolarBearMetadata extends AnimalMetadata {

    public static final byte OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    public PolarBearMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setStandingUp(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isStandingUp() {
        return super.metadata.getIndex(OFFSET, false);
    }
}