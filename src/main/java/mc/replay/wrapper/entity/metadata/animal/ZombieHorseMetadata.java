package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ZombieHorseMetadata extends AbstractHorseMetadata {

    public static final byte OFFSET = AbstractHorseMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public ZombieHorseMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}