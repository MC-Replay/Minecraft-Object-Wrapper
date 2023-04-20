package mc.replay.wrapper.entity.metadata;

import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.Nullable;

public interface ProjectileMetadata {

    void setShooter(@Nullable EntityWrapper shooter);

    @Nullable EntityWrapper getShooter();
}