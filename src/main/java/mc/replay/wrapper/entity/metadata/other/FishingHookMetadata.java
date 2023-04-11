package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FishingHookMetadata extends EntityMetadata implements ObjectDataProvider {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    private EntityWrapper hooked;
    private EntityWrapper owner;

    public FishingHookMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setHookedEntity(@Nullable EntityWrapper value) {
        this.hooked = value;
        int entityId = (value == null) ? 0 : value.getEntityId() + 1;
        super.metadata.setIndex(OFFSET, Metadata.VarInt(entityId));
    }

    public void setCatchable(boolean value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Boolean(value));
    }

    public void setOwnerEntity(@Nullable EntityWrapper value) {
        this.owner = value;
    }

    public @Nullable EntityWrapper getHookedEntity() {
        return this.hooked;
    }

    public boolean isCatchable() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public @Nullable EntityWrapper getOwnerEntity() {
        return this.owner;
    }

    @Override
    public int getObjectData() {
        return (this.owner == null) ? 0 : this.owner.getEntityId();
    }

    @Override
    public boolean requiresVelocityPacketAtSpawn() {
        return false;
    }
}