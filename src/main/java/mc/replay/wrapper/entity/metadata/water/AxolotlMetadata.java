package mc.replay.wrapper.entity.metadata.water;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AxolotlMetadata extends WaterAnimalMetadata {

    public static final int OFFSET = WaterAnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 3;

    public AxolotlMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setVariant(@NotNull Variant variant) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(variant.ordinal()));
    }

    public void setPlayingDead(boolean playingDead) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(playingDead));
    }

    public void setFromBucket(boolean fromBucket) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(fromBucket));
    }

    public @NotNull Variant getVariant() {
        return Variant.VALUES[super.metadata.getIndex(OFFSET, 0)];
    }

    public boolean isPlayingDead() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public boolean isFromBucket() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }

    public enum Variant {

        LUCY,
        WILD,
        GOLD,
        CYAN,
        BLUE;

        private static final Variant[] VALUES = values();
    }
}