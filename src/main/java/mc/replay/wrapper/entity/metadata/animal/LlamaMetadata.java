package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LlamaMetadata extends ChestedHorseMetadata {

    public static final byte OFFSET = ChestedHorseMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 3;

    public LlamaMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setStrength(int value) {
        super.metadata.setIndex(OFFSET, Metadata.VarInt(value));
    }

    public void setCarpetColor(int value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VarInt(value));
    }

    public void setVariant(@NotNull Variant value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.VarInt(value.ordinal()));
    }

    public int getStrength() {
        return super.metadata.getIndex(OFFSET, 0);
    }

    public int getCarpetColor() {
        return super.metadata.getIndex(OFFSET + 1, -1);
    }

    public @NotNull Variant getVariant() {
        return Variant.VALUES[super.metadata.getIndex(OFFSET + 2, 0)];
    }

    public enum Variant {

        CREAMY,
        WHITE,
        BROWN,
        GRAY;

        private static final Variant[] VALUES = values();
    }
}