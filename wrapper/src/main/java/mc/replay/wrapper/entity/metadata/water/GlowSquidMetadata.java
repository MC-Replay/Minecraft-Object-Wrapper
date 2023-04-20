package mc.replay.wrapper.entity.metadata.water;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class GlowSquidMetadata extends WaterAnimalMetadata {

    public static final int OFFSET = WaterAnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public GlowSquidMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setDarkTicksRemaining(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public int getDarkTicksRemaining() {
        return super.metadata.getIndex(OFFSET, 0);
    }
}