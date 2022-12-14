package mc.replay.wrapper.entity.metadata.animal.tameable;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParrotMetadata extends TameableAnimalMetadata {

    public static final byte OFFSET = TameableAnimalMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    public ParrotMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setColor(@NotNull Color value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value.ordinal()));
    }

    public @NotNull Color getColor() {
        return Color.VALUES[super.metadata.getIndex(OFFSET, 0)];
    }

    public enum Color {

        RED_BLUE,
        BLUE,
        GREEN,
        YELLOW_BLUE,
        GREY;

        private static final Color[] VALUES = values();
    }
}