package mc.replay.wrapper.entity;

import mc.replay.wrapper.entity.metadata.PlayerMetadata;
import mc.replay.wrapper.data.PlayerProfile;
import mc.replay.wrapper.data.SkinTexture;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerWrapper extends LivingEntityWrapper {

    private final PlayerProfile profile;

    private final String username;
    private SkinTexture skin;

    public PlayerWrapper(int entityId, @NotNull PlayerProfile profile) {
        super(EntityType.PLAYER, entityId, profile.uuid());

        this.profile = profile;
        this.username = profile.name();
        this.skin = SkinTexture.fromProfile(profile);
    }

    public PlayerWrapper(@NotNull Player player) {
        super(player);

        this.profile = PlayerProfile.fromPlayer(player);
        this.username = this.profile.name();
        this.skin = SkinTexture.fromProfile(this.profile);
    }

    public @NotNull PlayerProfile getProfile() {
        return this.profile;
    }

    public @NotNull String getUsername() {
        return this.username;
    }

    public @Nullable SkinTexture getSkin() {
        return this.skin;
    }

    public void setSkin(@NotNull SkinTexture skin) {
        this.skin = skin;
    }

    @Override
    public @Nullable PlayerMetadata getMetadata() {
        return this.getMetaData(PlayerMetadata.class);
    }
}