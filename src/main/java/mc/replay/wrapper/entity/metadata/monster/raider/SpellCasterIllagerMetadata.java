package mc.replay.wrapper.entity.metadata.monster.raider;

import mc.replay.packetlib.data.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class SpellCasterIllagerMetadata extends AbstractIllagerMetadata {

    public static final int OFFSET = AbstractIllagerMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 1;

    protected SpellCasterIllagerMetadata(@NotNull Metadata metadata) {
        super(metadata);
    }

    public void setSpell(@NotNull Spell spell) {
        super.metadata.setIndex(OFFSET, Metadata.Byte((byte) spell.ordinal()));
    }

    public @NotNull Spell getSpell() {
        return Spell.VALUES[super.metadata.getIndex(OFFSET, (byte) 0)];
    }

    public enum Spell {

        NONE,
        SUMMON_VEX,
        ATTACK,
        WOLOLO,
        DISAPPEAR,
        BLINDNESS;

        private static final Spell[] VALUES = values();
    }
}