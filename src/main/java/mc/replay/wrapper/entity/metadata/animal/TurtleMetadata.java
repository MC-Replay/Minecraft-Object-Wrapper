package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TurtleMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 6;

    public TurtleMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setHomePosition(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET, Metadata.Position(value));
    }

    public void setHasEgg(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public void setLayingEgg(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public void setTravelPosition(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.Position(value));
    }

    public void setGoingHome(boolean value) {
        super.metadata.setIndex(OFFSET + 4, Metadata.Boolean(value));
    }

    public void setTravelling(boolean value) {
        super.metadata.setIndex(OFFSET + 5, Metadata.Boolean(value));
    }

    public @NotNull Vector getHomePosition() {
        return super.metadata.getIndex(OFFSET, new Vector(0, 0, 0));
    }

    public boolean isHasEgg() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public boolean isLayingEgg() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }

    public @NotNull Vector getTravelPosition() {
        return super.metadata.getIndex(OFFSET + 3, new Vector(0, 0, 0));
    }

    public boolean isGoingHome() {
        return super.metadata.getIndex(OFFSET + 4, false);
    }

    public boolean isTravelling() {
        return super.metadata.getIndex(OFFSET + 5, false);
    }
}