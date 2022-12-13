package mc.replay.wrapper.entity.metadata;

import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.Nullable;

public interface ProjectileMetadata {

    @Nullable EntityWrapper getShooter();

    void setShooter(@Nullable EntityWrapper shooter);
}