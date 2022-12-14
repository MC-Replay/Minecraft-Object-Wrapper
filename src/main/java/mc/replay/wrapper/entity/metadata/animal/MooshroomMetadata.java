package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MooshroomMetadata extends CowMetadata {

    public static final int OFFSET = CowMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public MooshroomMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setVariant(@NotNull Variant value) {
        super.metadata.setIndex(OFFSET, Metadata.String(value.name().toLowerCase()));
    }

    public @NotNull Variant getVariant() {
        return Variant.valueOf(super.metadata.getIndex(OFFSET, "red").toUpperCase());
    }

    public enum Variant {

        RED,
        BROWN
    }
}