package mc.replay.wrapper.entity.metadata.water;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DolphinMetadata extends WaterAnimalMetadata {

    public static final byte OFFSET = WaterAnimalMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 3;

    public DolphinMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setTreasurePosition(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET, Metadata.Position(value));
    }

    public void setHasFish(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public void setCanFindTreasure(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public @NotNull Vector getTreasurePosition() {
        return super.metadata.getIndex(OFFSET, new Vector(0, 0, 0));
    }

    public boolean canFindTreasure() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public boolean hasFish() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }
}