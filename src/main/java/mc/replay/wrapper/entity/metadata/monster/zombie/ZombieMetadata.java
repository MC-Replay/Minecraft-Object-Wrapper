package mc.replay.wrapper.entity.metadata.monster.zombie;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.monster.MonsterMetadata;
import org.jetbrains.annotations.NotNull;

public class ZombieMetadata extends MonsterMetadata {

    public static final int OFFSET = MonsterMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 3;

    public ZombieMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setBaby(boolean value) {
        if (this.isBaby() == value) return;
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public void setBecomingDrowned(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public boolean isBaby() {
        return super.metadata.getIndex(OFFSET, false);
    }

    public boolean isBecomingDrowned() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }
}