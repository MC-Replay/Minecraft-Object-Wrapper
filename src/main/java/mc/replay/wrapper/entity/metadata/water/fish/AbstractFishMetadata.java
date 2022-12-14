package mc.replay.wrapper.entity.metadata.water.fish;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.water.WaterAnimalMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AbstractFishMetadata extends WaterAnimalMetadata {

    public static final int OFFSET = WaterAnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    protected AbstractFishMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setFromBucket(boolean fromBucket) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(fromBucket));
    }

    public boolean isFromBucket() {
        return super.metadata.getIndex(OFFSET, false);
    }
}