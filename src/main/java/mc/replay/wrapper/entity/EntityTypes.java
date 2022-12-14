package mc.replay.wrapper.entity;

import mc.replay.packetlib.data.entity.Metadata;
import mc.replay.wrapper.entity.metadata.EntityMetadata;
import mc.replay.wrapper.entity.metadata.PlayerMetadata;
import mc.replay.wrapper.entity.metadata.ambient.BatMetadata;
import mc.replay.wrapper.entity.metadata.animal.*;
import mc.replay.wrapper.entity.metadata.animal.tameable.CatMetadata;
import mc.replay.wrapper.entity.metadata.animal.tameable.ParrotMetadata;
import mc.replay.wrapper.entity.metadata.animal.tameable.WolfMetadata;
import mc.replay.wrapper.entity.metadata.arrow.ArrowMetadata;
import mc.replay.wrapper.entity.metadata.arrow.SpectralArrowMetadata;
import mc.replay.wrapper.entity.metadata.arrow.ThrownTridentMetadata;
import mc.replay.wrapper.entity.metadata.flying.GhastMetadata;
import mc.replay.wrapper.entity.metadata.flying.PhantomMetadata;
import mc.replay.wrapper.entity.metadata.golem.IronGolemMetadata;
import mc.replay.wrapper.entity.metadata.golem.ShulkerMetadata;
import mc.replay.wrapper.entity.metadata.golem.SnowGolemMetadata;
import mc.replay.wrapper.entity.metadata.item.*;
import mc.replay.wrapper.entity.metadata.minecart.*;
import mc.replay.wrapper.entity.metadata.monster.*;
import mc.replay.wrapper.entity.metadata.monster.raider.*;
import mc.replay.wrapper.entity.metadata.monster.skeleton.SkeletonMetadata;
import mc.replay.wrapper.entity.metadata.monster.skeleton.StrayMetadata;
import mc.replay.wrapper.entity.metadata.monster.skeleton.WitherSkeletonMetadata;
import mc.replay.wrapper.entity.metadata.monster.zombie.*;
import mc.replay.wrapper.entity.metadata.other.*;
import mc.replay.wrapper.entity.metadata.villager.VillagerMetadata;
import mc.replay.wrapper.entity.metadata.villager.WanderingTraderMetadata;
import mc.replay.wrapper.entity.metadata.water.AxolotlMetadata;
import mc.replay.wrapper.entity.metadata.water.DolphinMetadata;
import mc.replay.wrapper.entity.metadata.water.GlowSquidMetadata;
import mc.replay.wrapper.entity.metadata.water.SquidMetadata;
import mc.replay.wrapper.entity.metadata.water.fish.CodMetadata;
import mc.replay.wrapper.entity.metadata.water.fish.PufferfishMetadata;
import mc.replay.wrapper.entity.metadata.water.fish.SalmonMetadata;
import mc.replay.wrapper.entity.metadata.water.fish.TropicalFishMetadata;
import org.bukkit.entity.EntityType;

import java.util.Map;
import java.util.function.BiFunction;

import static java.util.Map.entry;

public final class EntityTypes {

    static final Map<String, BiFunction<EntityWrapper, Metadata, EntityMetadata>> ENTITY_META_SUPPLIER = createMetadataMap();

    static EntityMetadata createMetadata(EntityType entityType, EntityWrapper entity, Metadata metadata) {
        return ENTITY_META_SUPPLIER.get(entityType.getKey().toString()).apply(entity, metadata);
    }

    private static Map<String, BiFunction<EntityWrapper, Metadata, EntityMetadata>> createMetadataMap() {
        return Map.<String, BiFunction<EntityWrapper, Metadata, EntityMetadata>>ofEntries(
                entry("minecraft:allay", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EntityMetadata::new), // TODO dedicated metadata
                entry("minecraft:area_effect_cloud", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) AreaEffectCloudMetadata::new),
                entry("minecraft:armor_stand", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ArmorStandMetadata::new),
                entry("minecraft:arrow", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ArrowMetadata::new),
                entry("minecraft:axolotl", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) AxolotlMetadata::new),
                entry("minecraft:bat", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) BatMetadata::new),
                entry("minecraft:bee", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) BeeMetadata::new),
                entry("minecraft:blaze", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) BlazeMetadata::new),
                entry("minecraft:boat", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) BoatMetadata::new),
                entry("minecraft:chest_boat", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EntityMetadata::new), // TODO dedicated metadata
                entry("minecraft:cat", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) CatMetadata::new),
                entry("minecraft:cave_spider", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) CaveSpiderMetadata::new),
                entry("minecraft:chicken", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ChickenMetadata::new),
                entry("minecraft:cod", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) CodMetadata::new),
                entry("minecraft:cow", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) CowMetadata::new),
                entry("minecraft:creeper", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) CreeperMetadata::new),
                entry("minecraft:dolphin", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) DolphinMetadata::new),
                entry("minecraft:donkey", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) DonkeyMetadata::new),
                entry("minecraft:dragon_fireball", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) DragonFireballMetadata::new),
                entry("minecraft:drowned", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) DrownedMetadata::new),
                entry("minecraft:elder_guardian", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ElderGuardianMetadata::new),
                entry("minecraft:end_crystal", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EndCrystalMetadata::new),
                entry("minecraft:ender_dragon", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EnderDragonMetadata::new),
                entry("minecraft:enderman", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EndermanMetadata::new),
                entry("minecraft:endermite", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EndermiteMetadata::new),
                entry("minecraft:evoker", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EvokerMetadata::new),
                entry("minecraft:evoker_fangs", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EvokerFangsMetadata::new),
                entry("minecraft:experience_orb", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ExperienceOrbMetadata::new),
                entry("minecraft:eye_of_ender", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EyeOfEnderMetadata::new),
                entry("minecraft:falling_block", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) FallingBlockMetadata::new),
                entry("minecraft:firework_rocket", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) FireworkRocketMetadata::new),
                entry("minecraft:fox", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) FoxMetadata::new),
                entry("minecraft:frog", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EntityMetadata::new), // TODO dedicated metadata
                entry("minecraft:ghast", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) GhastMetadata::new),
                entry("minecraft:giant", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) GiantMetadata::new),
                entry("minecraft:glow_item_frame", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) GlowItemFrameMetadata::new),
                entry("minecraft:glow_squid", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) GlowSquidMetadata::new),
                entry("minecraft:goat", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) GoatMetadata::new),
                entry("minecraft:guardian", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) GuardianMetadata::new),
                entry("minecraft:hoglin", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) HoglinMetadata::new),
                entry("minecraft:horse", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) HorseMetadata::new),
                entry("minecraft:husk", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) HuskMetadata::new),
                entry("minecraft:illusioner", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) IllusionerMetadata::new),
                entry("minecraft:iron_golem", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) IronGolemMetadata::new),
                entry("minecraft:item", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ItemEntityMetadata::new),
                entry("minecraft:item_frame", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ItemFrameMetadata::new),
                entry("minecraft:fireball", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) FireballMetadata::new),
                entry("minecraft:leash_knot", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) LeashKnotMetadata::new),
                entry("minecraft:lightning_bolt", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) LightningBoltMetadata::new),
                entry("minecraft:llama", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) LlamaMetadata::new),
                entry("minecraft:llama_spit", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) LlamaSpitMetadata::new),
                entry("minecraft:magma_cube", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) MagmaCubeMetadata::new),
                entry("minecraft:marker", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) MarkerMetadata::new),
                entry("minecraft:minecart", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) MinecartMetadata::new),
                entry("minecraft:chest_minecart", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ChestMinecartMetadata::new),
                entry("minecraft:command_block_minecart", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) CommandBlockMinecartMetadata::new),
                entry("minecraft:furnace_minecart", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) FurnaceMinecartMetadata::new),
                entry("minecraft:hopper_minecart", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) HopperMinecartMetadata::new),
                entry("minecraft:spawner_minecart", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SpawnerMinecartMetadata::new),
                entry("minecraft:tnt_minecart", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) TntMinecartMetadata::new),
                entry("minecraft:mule", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) MuleMetadata::new),
                entry("minecraft:mooshroom", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) MooshroomMetadata::new),
                entry("minecraft:ocelot", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) OcelotMetadata::new),
                entry("minecraft:painting", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PaintingMetadata::new),
                entry("minecraft:panda", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PandaMetadata::new),
                entry("minecraft:parrot", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ParrotMetadata::new),
                entry("minecraft:phantom", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PhantomMetadata::new),
                entry("minecraft:pig", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PigMetadata::new),
                entry("minecraft:piglin", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PiglinMetadata::new),
                entry("minecraft:piglin_brute", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PiglinBruteMetadata::new),
                entry("minecraft:pillager", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PillagerMetadata::new),
                entry("minecraft:polar_bear", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PolarBearMetadata::new),
                entry("minecraft:tnt", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PrimedTntMetadata::new),
                entry("minecraft:pufferfish", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PufferfishMetadata::new),
                entry("minecraft:rabbit", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) RabbitMetadata::new),
                entry("minecraft:ravager", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) RavagerMetadata::new),
                entry("minecraft:salmon", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SalmonMetadata::new),
                entry("minecraft:sheep", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SheepMetadata::new),
                entry("minecraft:shulker", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ShulkerMetadata::new),
                entry("minecraft:shulker_bullet", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ShulkerBulletMetadata::new),
                entry("minecraft:silverfish", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SilverfishMetadata::new),
                entry("minecraft:skeleton", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SkeletonMetadata::new),
                entry("minecraft:skeleton_horse", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SkeletonHorseMetadata::new),
                entry("minecraft:slime", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SlimeMetadata::new),
                entry("minecraft:small_fireball", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SmallFireballMetadata::new),
                entry("minecraft:snow_golem", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SnowGolemMetadata::new),
                entry("minecraft:snowball", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SnowballMetadata::new),
                entry("minecraft:spectral_arrow", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SpectralArrowMetadata::new),
                entry("minecraft:spider", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SpiderMetadata::new),
                entry("minecraft:squid", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) SquidMetadata::new),
                entry("minecraft:stray", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) StrayMetadata::new),
                entry("minecraft:strider", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) StriderMetadata::new),
                entry("minecraft:tadpole", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EntityMetadata::new), // TODO dedicated metadata
                entry("minecraft:egg", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ThrownEggMetadata::new),
                entry("minecraft:ender_pearl", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ThrownEnderPearlMetadata::new),
                entry("minecraft:experience_bottle", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ThrownExpierenceBottleMetadata::new),
                entry("minecraft:potion", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ThrownPotionMetadata::new),
                entry("minecraft:trident", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ThrownTridentMetadata::new),
                entry("minecraft:trader_llama", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) TraderLlamaMetadata::new),
                entry("minecraft:tropical_fish", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) TropicalFishMetadata::new),
                entry("minecraft:turtle", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) TurtleMetadata::new),
                entry("minecraft:vex", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) VexMetadata::new),
                entry("minecraft:villager", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) VillagerMetadata::new),
                entry("minecraft:vindicator", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) VindicatorMetadata::new),
                entry("minecraft:wandering_trader", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) WanderingTraderMetadata::new),
                entry("minecraft:warden", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) EntityMetadata::new), // TODO dedicated metadata
                entry("minecraft:witch", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) WitchMetadata::new),
                entry("minecraft:wither", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) WitherMetadata::new),
                entry("minecraft:wither_skeleton", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) WitherSkeletonMetadata::new),
                entry("minecraft:wither_skull", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) WitherSkullMetadata::new),
                entry("minecraft:wolf", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) WolfMetadata::new),
                entry("minecraft:zoglin", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ZoglinMetadata::new),
                entry("minecraft:zombie", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ZombieMetadata::new),
                entry("minecraft:zombie_horse", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ZombieHorseMetadata::new),
                entry("minecraft:zombie_villager", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ZombieVillagerMetadata::new),
                entry("minecraft:zombified_piglin", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) ZombifiedPiglinMetadata::new),
                entry("minecraft:player", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) PlayerMetadata::new),
                entry("minecraft:fishing_bobber", (BiFunction<EntityWrapper, Metadata, EntityMetadata>) FishingHookMetadata::new)
        );
    }
}