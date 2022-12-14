package mc.replay.wrapper.entity.metadata.golem;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IronGolemMetadata extends AbstractGolemMetadata {

    public static final int OFFSET = AbstractGolemMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    private static final byte PLAYER_CREATED_BIT = 0x01;

    public IronGolemMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setPlayerCreated(boolean value) {
        this.setMaskBit(OFFSET, PLAYER_CREATED_BIT, value);
    }

    public boolean isPlayerCreated() {
        return this.getMaskBit(OFFSET, PLAYER_CREATED_BIT);
    }
}