package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FallingBlockMetadata extends EntityMetadata implements ObjectDataProvider {

    public static final byte OFFSET = EntityMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    // TODO change material to block object with stateId and type
    private Material material = Material.STONE;

    public FallingBlockMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setSpawnPosition(@NotNull Vector value) {
        super.metadata.setIndex(OFFSET, Metadata.Position(value));
    }

    public @NotNull Vector getSpawnPosition() {
        return super.metadata.getIndex(OFFSET, new Vector(0, 0, 0));
    }

    public void setMaterial(@NotNull Material material) {
        this.material = material;
    }

    public @NotNull Material getMaterial() {
        return this.material;
    }

    @Override
    public int getObjectData() {
        return this.material.ordinal();
    }

    @Override
    public boolean requiresVelocityPacketAtSpawn() {
        return false;
    }
}