package mc.replay.wrapper.entity.metadata.monster.raider;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IllusionerMetadata extends SpellCasterIllagerMetadata {

    public static final byte OFFSET = SpellCasterIllagerMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public IllusionerMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }
}