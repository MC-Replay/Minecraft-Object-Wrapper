package mc.replay.wrapper.entity.metadata.other;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.entity.metadata.ObjectDataProvider;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class FallingBlockMetadata extends EntityMetadata implements ObjectDataProvider {

    public static final int OFFSET = EntityMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    // TODO change material to block object with stateId and type
    private Material material = Material.STONE;

    public FallingBlockMetadata(@NotNull Metadata metadata) {
        super(metadata);
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