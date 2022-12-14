package mc.replay.wrapper.entity.metadata.water.fish;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PufferfishMetadata extends AbstractFishMetadata {

    public static final byte OFFSET = AbstractFishMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    public PufferfishMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setState(@NotNull State state) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(state.ordinal()));
    }

    public @NotNull State getState() {
        return State.VALUES[super.metadata.getIndex(OFFSET, 0)];
    }

    public enum State {

        UNPUFFED,
        SEMI_PUFFED,
        FULLY_PUFFED;

        private static final State[] VALUES = values();
    }
}