package mc.replay.wrapper.team;

import mc.replay.packetlib.data.team.CollisionRule;
import mc.replay.packetlib.data.team.NameTagVisibility;
import mc.replay.packetlib.utils.AdventurePacketConverter;
import mc.replay.wrapper.utils.AdventureUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TeamWrapper {

    private final String name;
    private Component displayName;
    private Component prefix;
    private Component suffix;
    private NamedTextColor color;
    private NameTagVisibility visibility;
    private CollisionRule collisionRule;

    private Collection<String> entries = new HashSet<>();

    public TeamWrapper(@NotNull String name, @NotNull Component displayName, @NotNull Component prefix,
                       @NotNull Component suffix, @NotNull NamedTextColor color, @NotNull NameTagVisibility visibility,
                       @NotNull CollisionRule collisionRule) {
        this.name = name;
        this.displayName = displayName;
        this.prefix = prefix;
        this.suffix = suffix;
        this.color = color;
        this.visibility = visibility;
        this.collisionRule = collisionRule;
    }

    public TeamWrapper(@NotNull String name) {
        this(
                name,
                Component.empty(),
                Component.empty(),
                Component.empty(),
                NamedTextColor.WHITE,
                NameTagVisibility.ALWAYS,
                CollisionRule.ALWAYS
        );
    }

    public TeamWrapper(@NotNull Team team) {
        this(team.getName());

        this.setDisplayName(AdventureUtils.legacy(team.getDisplayName()));
        this.setPrefix(AdventureUtils.legacy(team.getPrefix()));
        this.setSuffix(AdventureUtils.legacy(team.getSuffix()));
        this.setColor(AdventurePacketConverter.getNamedTextColorFromId(team.getColor().ordinal()));

        Team.OptionStatus visibilityOption = team.getOption(Team.Option.NAME_TAG_VISIBILITY);
        this.setVisibility(NameTagVisibility.values()[visibilityOption.ordinal()]);

        Team.OptionStatus collisionRuleOption = team.getOption(Team.Option.COLLISION_RULE);
        this.setCollisionRule(CollisionRule.values()[collisionRuleOption.ordinal()]);

        this.entries = Set.copyOf(team.getEntries());
    }

    public @NotNull Collection<String> getEntries() {
        return Set.copyOf(this.entries);
    }

    public void addEntry(@NotNull String name) {
        if (!this.entries.contains(name)) {
            this.entries.add(name);
        }
    }

    public void removeEntry(@NotNull String name) {
        this.entries.remove(name);
    }

    public boolean isEntry(@NotNull String name) {
        return this.entries.contains(name);
    }

    public void setDisplayName(@NotNull Component displayName) {
        this.displayName = displayName;
    }

    public void setPrefix(@NotNull Component prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(@NotNull Component suffix) {
        this.suffix = suffix;
    }

    public void setColor(@NotNull NamedTextColor color) {
        this.color = color;
    }

    public void setVisibility(@NotNull NameTagVisibility visibility) {
        this.visibility = visibility;
    }

    public void setCollisionRule(@NotNull CollisionRule collisionRule) {
        this.collisionRule = collisionRule;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull Component getDisplayName() {
        return this.displayName;
    }

    public @NotNull Component getPrefix() {
        return this.prefix;
    }

    public @NotNull Component getSuffix() {
        return this.suffix;
    }

    public @NotNull NamedTextColor getColor() {
        return this.color;
    }

    public @NotNull NameTagVisibility getVisibility() {
        return visibility;
    }

    public @NotNull CollisionRule getCollisionRule() {
        return collisionRule;
    }
}