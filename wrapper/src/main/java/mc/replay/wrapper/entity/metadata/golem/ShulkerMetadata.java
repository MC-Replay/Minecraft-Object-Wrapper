package mc.replay.wrapper.entity.metadata.golem;

import mc.replay.packetlib.data.entity.Metadata;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShulkerMetadata extends AbstractGolemMetadata {

    public static final int OFFSET = AbstractGolemMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 4;

    public ShulkerMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setAttachFace(@NotNull BlockFace value) {
        super.metadata.setIndex(OFFSET, Metadata.BlockFace(value));
    }

    public void setAttachmentPosition(Vector value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.OptPosition(value));
    }

    public void setShieldHeight(byte value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Byte(value));
    }

    public void setColor(byte value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.Byte(value));
    }

    public @NotNull BlockFace getAttachFace() {
        return super.metadata.getIndex(OFFSET, BlockFace.DOWN);
    }

    public @Nullable Vector getAttachmentPosition() {
        return super.metadata.getIndex(OFFSET + 1, null);
    }

    public byte getShieldHeight() {
        return super.metadata.getIndex(OFFSET + 2, (byte) 0);
    }

    public byte getColor() {
        return super.metadata.getIndex(OFFSET + 3, (byte) 10);
    }
}