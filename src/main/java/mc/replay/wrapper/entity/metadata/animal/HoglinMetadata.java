package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class HoglinMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public HoglinMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setImmuneToZombification(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isImmuneToZombification() {
        return super.metadata.getIndex(OFFSET, false);
    }
}