package mc.replay.wrapper.entity.metadata.villager;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VillagerMetadata extends AbstractVillagerMetadata {

    public static final int OFFSET = AbstractVillagerMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    public VillagerMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setVillagerData(@NotNull VillagerData value) {
        super.metadata.setIndex(OFFSET, Metadata.VillagerData(
                value.type.ordinal(),
                value.profession.ordinal(),
                value.level.ordinal()
        ));
    }

    public @NotNull VillagerData getVillagerData() {
        int[] data = super.metadata.getIndex(OFFSET, null);
        if (data == null) {
            return new VillagerData(Type.PLAINS, Profession.NONE, Level.NOVICE);
        }
        return new VillagerData(Type.VALUES[data[0]], Profession.VALUES[data[1]], Level.VALUES[data[2] - 1]);
    }

    public static class VillagerData {

        private Type type;
        private Profession profession;
        private Level level;

        public VillagerData(@NotNull Type type, @NotNull Profession profession, @NotNull Level level) {
            this.type = type;
            this.profession = profession;
            this.level = level;
        }

        public @NotNull Type getType() {
            return type;
        }

        public @NotNull Profession getProfession() {
            return profession;
        }

        public @NotNull Level getLevel() {
            return level;
        }

        public void setType(@NotNull Type type) {
            this.type = type;
        }

        public void setProfession(@NotNull Profession profession) {
            this.profession = profession;
        }

        public void setLevel(@NotNull Level level) {
            this.level = level;
        }
    }

    public enum Type {

        DESERT,
        JUNGLE,
        PLAINS,
        SAVANNA,
        SNOW,
        SWAMP,
        TAIGA;

        public static final Type[] VALUES = values();
    }

    public enum Profession {

        NONE,
        ARMORER,
        BUTCHER,
        CARTOGRAPHER,
        CLERIC,
        FARMER,
        FISHERMAN,
        FLETCHER,
        LEATHERWORKER,
        LIBRARIAN,
        NITWIT,
        UNEMPLOYED,
        MASON,
        SHEPHERD,
        TOOLSMITH,
        WEAPONSMITH;

        public final static Profession[] VALUES = values();
    }

    public enum Level {

        NOVICE,
        APPRENTICE,
        JOURNEYMAN,
        EXPERT,
        MASTER;

        public final static Level[] VALUES = values();
    }
}