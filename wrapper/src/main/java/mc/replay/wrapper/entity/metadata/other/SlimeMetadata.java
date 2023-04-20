package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.MobMetadata;
import org.jetbrains.annotations.NotNull;

public class SlimeMetadata extends MobMetadata {

    public static final int OFFSET = MobMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public SlimeMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setSize(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public int getSize() {
        return super.metadata.getIndex(OFFSET, 0);
    }
}