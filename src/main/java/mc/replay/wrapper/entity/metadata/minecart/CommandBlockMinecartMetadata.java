package mc.replay.wrapper.entity.metadata.minecart;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.EntityWrapper;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandBlockMinecartMetadata extends AbstractMinecartMetadata {

    public static final int OFFSET = AbstractMinecartMetadata.MAX_OFFSET;
    public static final int MAX_OFFSET = OFFSET + 2;

    public CommandBlockMinecartMetadata(@Nullable EntityWrapper entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public void setCommand(@NotNull String value) {
        super.metadata.setIndex(OFFSET, Metadata.String(value));
    }

    public void setLastOutput(@NotNull Component value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.Chat(value));
    }

    public @NotNull String getCommand() {
        return super.metadata.getIndex(OFFSET, "");
    }

    public @NotNull Component getLastOutput() {
        return super.metadata.getIndex(OFFSET + 1, Component.empty());
    }

    @Override
    public int getObjectData() {
        return 6;
    }
}