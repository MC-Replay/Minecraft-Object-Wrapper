package mc.replay.wrapper.entity.metadata.water.fish;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.water.WaterAnimalMetadata;
import org.jetbrains.annotations.NotNull;

public class AbstractFishMetadata extends WaterAnimalMetadata {

    public static final int OFFSET = WaterAnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    protected AbstractFishMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setFromBucket(boolean fromBucket) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(fromBucket));
    }

    public boolean isFromBucket() {
        return super.metadata.getIndex(OFFSET, false);
    }
}