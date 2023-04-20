package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RabbitMetadata extends AnimalMetadata {

    public static final int OFFSET = AnimalMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public RabbitMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setType(@NotNull Type value) {
        int id = value == Type.KILLER_BUNNY ? 99 : value.ordinal();
        super.metadata.setIndex(OFFSET, Metadata.VarInt(id));
    }

    public @NotNull Type getType() {
        int id = super.metadata.getIndex(OFFSET, 0);
        return (id == 99) ? Type.KILLER_BUNNY : Type.VALUES[id];
    }

    public enum Type {

        BROWN,
        WHITE,
        BLACK,
        BLACK_AND_WHITE,
        GOLD,
        SALT_AND_PEPPER,
        KILLER_BUNNY;

        private static final Type[] VALUES = values();
    }
}