package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import org.jetbrains.annotations.NotNull;

public class AreaEffectCloudMetadata extends EntityMetadata {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 4;

    public AreaEffectCloudMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setRadius(float value) {
        super.metadata.setIndex(OFFSET, Metadata.Float(value));
    }

    public void setColor(int value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public void setSinglePoint(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public float getRadius() {
        return super.metadata.getIndex(OFFSET, .5F);
    }

    public int getColor() {
        return super.metadata.getIndex(OFFSET + 1, 0);
    }

    public boolean isSinglePoint() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }
}