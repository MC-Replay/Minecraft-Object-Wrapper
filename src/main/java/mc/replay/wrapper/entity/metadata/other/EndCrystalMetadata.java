package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EndCrystalMetadata extends EntityMetadata {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    public EndCrystalMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setBeamTarget(@Nullable Vector value) {
        super.metadata.setIndex(OFFSET, Metadata.OptPosition(value));
    }

    public void setShowingBottom(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public @Nullable Vector getBeamTarget() {
        return super.metadata.getIndex(OFFSET, null);
    }

    public boolean isShowingBottom() {
        return super.metadata.getIndex(OFFSET + 1, true);
    }
}