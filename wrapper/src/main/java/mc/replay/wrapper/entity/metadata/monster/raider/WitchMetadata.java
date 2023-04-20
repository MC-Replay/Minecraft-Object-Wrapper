package mc.replay.wrapper.entity.metadata.monster.raider;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class WitchMetadata extends RaiderMetadata {

    public static final int OFFSET = RaiderMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public WitchMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setDrinkingPotion(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isDrinkingPotion(boolean value) {
        return super.metadata.getIndex(OFFSET, false);
    }
}