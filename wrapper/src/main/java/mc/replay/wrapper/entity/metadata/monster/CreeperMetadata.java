package mc.replay.wrapper.entity.metadata.monster;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class CreeperMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 3;

    public CreeperMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setState(@NotNull State value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt((value == State.IDLE) ? -1 : 1));
    }

    public void setCharged(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public void setIgnited(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public @NotNull State getState() {
        int id = super.metadata.getIndex(OFFSET, -1);
        return (id == -1) ? State.IDLE : State.FUSE;
    }

    public boolean isCharged() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public boolean isIgnited() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }

    public enum State {

        IDLE,
        FUSE
    }
}