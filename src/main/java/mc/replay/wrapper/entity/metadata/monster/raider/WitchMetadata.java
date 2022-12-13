package mc.replay.wrapper.entity.metadata.monster.raider;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WitchMetadata extends RaiderMetadata {

    public static final byte OFFSET = RaiderMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    public WitchMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setDrinkingPotion(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isDrinkingPotion(boolean value) {
        return super.metadata.getIndex(OFFSET, false);
    }
}