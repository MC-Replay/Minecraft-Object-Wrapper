package mc.replay.wrapper.entity.metadata;

import org.jetbrains.annotations.Nullable;

public interface ShooterProvider {

    @Nullable Integer getShooterId();

    void setShooterId(@Nullable Integer shooterId);
}