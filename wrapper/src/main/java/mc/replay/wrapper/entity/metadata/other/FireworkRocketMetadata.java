package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.Item;
import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.entity.metadata.ProjectileMetadata;
import mc.replay.wrapper.entity.metadata.ShooterProvider;
import mc.replay.wrapper.item.ItemWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FireworkRocketMetadata extends EntityMetadata implements ShooterProvider, ProjectileMetadata {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 3;

    private EntityWrapper shooter;

    public FireworkRocketMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setFireworkInfo(@NotNull ItemWrapper value) {
        super.metadata.setIndex(OFFSET, Metadata.Slot(value));
    }

    @Override
    public void setShooter(@Nullable EntityWrapper shooter) {
        this.shooter = shooter;
        Integer entityId = (shooter == null) ? null : shooter.getEntityId();
        this.setShooterId(entityId);
    }

    @Override
    public @Nullable Integer getShooterId() {
        return super.metadata.getIndex(OFFSET + 1, null);
    }

    @Override
    public void setShooterId(@Nullable Integer shooterId) {
        super.metadata.setIndex(OFFSET + 1, Metadata.OptVarInt(shooterId));
    }

    public void setShotAtAngle(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

    public @NotNull ItemWrapper getFireworkInfo() {
        return super.metadata.getIndex(OFFSET, new ItemWrapper(Item.AIR));
    }

    @Override
    public @Nullable EntityWrapper getShooter() {
        return this.shooter;
    }

    public boolean isShotAtAngle() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }
}