package mc.replay.wrapper.entity.metadata.arrow;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class ThrownTridentMetadata extends AbstractArrowMetadata {

    public static final int OFFSET = AbstractArrowMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    public ThrownTridentMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setLoyaltyLevel(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public void setHasEnchantmentGlint(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public int getLoyaltyLevel() {
        return super.metadata.getIndex(OFFSET, 0);
    }

    public boolean isHasEnchantmentGlint() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }
}