package mc.replay.wrapper.entity.metadata.monster.zombie;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import mc.replay.wrapper.entity.metadata.villager.VillagerMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ZombieVillagerMetadata extends ZombieMetadata {

    public static final byte OFFSET = ZombieMetadata.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 2;

    public ZombieVillagerMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setConverting(boolean value) {
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public VillagerMetadata.VillagerData getVillagerData() {
        int[] data = super.metadata.getIndex(OFFSET + 1, null);
        if (data == null) {
            return new VillagerMetadata.VillagerData(VillagerMetadata.Type.PLAINS, VillagerMetadata.Profession.NONE, VillagerMetadata.Level.NOVICE);
        }
        return new VillagerMetadata.VillagerData(VillagerMetadata.Type.VALUES[data[0]], VillagerMetadata.Profession.VALUES[data[1]], VillagerMetadata.Level.VALUES[data[2] - 1]);
    }

    public boolean isConverting() {
        return super.metadata.getIndex(OFFSET + 1, false);
    }

    public void setVillagerData(VillagerMetadata.VillagerData value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.VillagerData(
                value.getType().ordinal(),
                value.getProfession().ordinal(),
                value.getLevel().ordinal() + 1
        ));
    }
}