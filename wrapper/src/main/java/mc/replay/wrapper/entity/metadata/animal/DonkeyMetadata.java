package mc.replay.wrapper.entity.metadata.animal;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DonkeyMetadata extends ChestedHorseMetadata {

    public static final int OFFSET = ChestedHorseMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 0;

    public DonkeyMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }
}