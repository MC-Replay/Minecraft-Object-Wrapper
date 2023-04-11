package mc.replay.wrapper.entity.metadata.arrow;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import org.jetbrains.annotations.NotNull;

public class AbstractArrowMetadata extends EntityMetadata {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    private static final byte CRITICAL_BIT = 0x01;
    private static final byte NO_CLIP_BIT = 0x02;

    protected AbstractArrowMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setCritical(boolean value) {
        this.setMaskBit(OFFSET, CRITICAL_BIT, value);
    }

    public void setNoClip(boolean value) {
        this.setMaskBit(OFFSET, NO_CLIP_BIT, value);
    }

    public void setPiercingLevel(byte value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Byte(value));
    }

    public boolean isCritical() {
        return this.getMaskBit(OFFSET, CRITICAL_BIT);
    }

    public boolean isNoClip() {
        return this.getMaskBit(OFFSET, NO_CLIP_BIT);
    }

    public byte getPiercingLevel() {
        return super.metadata.getIndex(OFFSET + 1, (byte) 0);
    }
}