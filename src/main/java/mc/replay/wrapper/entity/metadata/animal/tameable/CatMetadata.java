package mc.replay.wrapper.entity.metadata.animal.tameable;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CatMetadata extends TameableAnimalMetadata {

    public static final int OFFSET = TameableAnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 4;

    public CatMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setColor(@NotNull Color value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value.ordinal()));
    }

    public void setLying(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public void setRelaxed(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public void setCollarColor(int value) {
        super.metadata.setIndex(OFFSET + 3, Metadata.VarInt(value));
    }

    public @NotNull Color getColor() {
        return Color.VALUES[super.metadata.getIndex(OFFSET, 1)];
    }

    public boolean isLying() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public boolean isRelaxed() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }

    public int getCollarColor() {
        return super.metadata.getIndex(OFFSET + 3, 14);
    }

    public enum Color {

        TABBY,
        BLACK,
        RED,
        SIAMESE,
        BRITISH_SHORTHAIR,
        CALICO,
        PERSIAN,
        RAGDOLL,
        WHITE,
        JELLIE,
        ALL_BLACK;

        private static final Color[] VALUES = values();
    }
}