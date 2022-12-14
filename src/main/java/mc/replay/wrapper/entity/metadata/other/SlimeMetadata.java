package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.MobMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlimeMetadata extends MobMetadata {

    public static final byte OFFSET = MobMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    public SlimeMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setSize(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public int getSize() {
        return super.metadata.getIndex(OFFSET, 0);
    }
}