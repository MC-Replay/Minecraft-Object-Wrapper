package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.AgeableMobMetadata;
import org.jetbrains.annotations.NotNull;

public class AnimalMetadata extends AgeableMobMetadata {

    public static final int OFFSET = AgeableMobMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    protected AnimalMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }
}