package com.Polarice3.Goety.common.effects.brew;

import com.Polarice3.Goety.common.blocks.ModBlocks;
import com.Polarice3.Goety.common.effects.GoetyEffects;
import com.Polarice3.Goety.common.effects.brew.block.*;
import com.Polarice3.Goety.common.effects.brew.modifiers.BrewModifier;
import com.Polarice3.Goety.common.effects.brew.modifiers.CapacityModifier;
import com.Polarice3.Goety.common.items.ModItems;
import com.Polarice3.Goety.config.BrewConfig;
import com.Polarice3.Goety.init.ModTags;
import com.google.common.collect.Maps;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.core.registries.BuiltInRegistries;

import javax.annotation.Nullable;
import java.util.Map;

public class BrewEffects {
    public static BrewEffects INSTANCE = new BrewEffects();
    private final Map<String, BrewEffect> effectIDs = Maps.newHashMap();
    private final Map<Item, BrewEffect> catalyst = Maps.newHashMap();
    private final Map<EntityType<?>, BrewEffect> sacrifice = Maps.newHashMap();
    /** Made for external recipe viewers like Patchouli */
    private final Map<String, ItemStack> catalystInverted = Maps.newHashMap();
    private final Map<String, EntityType<?>> sacrificeInverted = Maps.newHashMap();
    private final Map<Item, BrewModifier> modifiers = Maps.newHashMap();

    public BrewEffects(){
        //Modifiers
        this.modifierRegister(new CapacityModifier(0), Items.NETHER_WART);
        this.modifierRegister(new CapacityModifier(1), Items.CRIMSON_FUNGUS);
        this.modifierRegister(new CapacityModifier(2), ModBlocks.SNAP_WARTS_ITEM.get());
        this.modifierRegister(new CapacityModifier(3), ModItems.MAGIC_EMERALD.get());
        this.modifierRegister(new CapacityModifier(4), ModItems.SOUL_EMERALD.get());
        this.modifierRegister(new CapacityModifier(5), ModItems.SOUL_RUBY.get());
        this.modifierRegister(new BrewModifier(BrewModifier.DURATION, 0), Items.REDSTONE);
        this.modifierRegister(new BrewModifier(BrewModifier.DURATION, 1), Items.PRISMARINE);
        this.modifierRegister(new BrewModifier(BrewModifier.DURATION, 2), Items.CHORUS_FLOWER);
        this.modifierRegister(new BrewModifier(BrewModifier.AMPLIFIER, 0), Items.GLOWSTONE_DUST);
        this.modifierRegister(new BrewModifier(BrewModifier.AMPLIFIER, 1), Items.BLAZE_ROD);
        this.modifierRegister(new BrewModifier(BrewModifier.AMPLIFIER, 2), ModItems.MYSTIC_CORE.get());
        this.modifierRegister(new BrewModifier(BrewModifier.AOE, 0), Items.CHARCOAL);
        this.modifierRegister(new BrewModifier(BrewModifier.AOE, 1), Items.FIREWORK_STAR);
        this.modifierRegister(new BrewModifier(BrewModifier.AOE, 2), ModBlocks.TALL_SKULL_ITEM.get());
        this.modifierRegister(new BrewModifier(BrewModifier.LINGER, 0), Items.HANGING_ROOTS);
        this.modifierRegister(new BrewModifier(BrewModifier.LINGER, 1), Items.BIG_DRIPLEAF);
        this.modifierRegister(new BrewModifier(BrewModifier.LINGER, 2), Items.SPORE_BLOSSOM);
        this.modifierRegister(new BrewModifier(BrewModifier.QUAFF, 0), Items.HONEY_BOTTLE);
        this.modifierRegister(new BrewModifier(BrewModifier.QUAFF, 1), Items.GLOW_LICHEN);
        this.modifierRegister(new BrewModifier(BrewModifier.QUAFF, 2), Items.TURTLE_EGG);
        this.modifierRegister(new BrewModifier(BrewModifier.VELOCITY, 0), Items.SNOWBALL);
        this.modifierRegister(new BrewModifier(BrewModifier.VELOCITY, 1), Items.BOW);
        this.modifierRegister(new BrewModifier(BrewModifier.VELOCITY, 2), Items.CROSSBOW);
        this.modifierRegister(new BrewModifier(BrewModifier.AQUATIC), Items.TUBE_CORAL);
        this.modifierRegister(new BrewModifier(BrewModifier.FIRE_PROOF), Items.NETHERITE_SCRAP);
        this.modifierRegister(new BrewModifier(BrewModifier.HIDDEN), Items.ENDER_EYE);
        this.modifierRegister(new BrewModifier(BrewModifier.SPLASH), Items.GUNPOWDER);
        this.modifierRegister(new BrewModifier(BrewModifier.LINGERING), Items.DRAGON_BREATH);
        this.modifierRegister(new BrewModifier(BrewModifier.GAS), ModItems.WIND_CORE.get());

        //Vanilla
        this.register(new PotionBrewEffect(MobEffects.ABSORPTION, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.AbsorptionCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.AbsorptionCapacity, 0), 1800), Items.GOLDEN_APPLE);
        this.register(new PotionBrewEffect(MobEffects.BLINDNESS, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BlindnessCost, 0), 1800), Items.INK_SAC);
        this.register(new PotionBrewEffect(MobEffects.DARKNESS, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.DarknessCost, 0), 1800), Items.SCULK);
        this.register(new PotionBrewEffect(MobEffects.FIRE_RESISTANCE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FireResistanceCost, 0), 3600), Items.MAGMA_CREAM);
        this.register(new PotionBrewEffect(MobEffects.GLOWING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.GlowingCost, 0), 3600), Items.GLOW_INK_SAC);
        this.register(new PotionBrewEffect(MobEffects.DIG_SPEED, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.HasteCost, 0), 3600), Items.COOKIE);
        this.register(new PotionBrewEffect(MobEffects.HUNGER, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.HungerCost, 0), 1800), Items.ROTTEN_FLESH);
        this.register(new PotionBrewEffect(MobEffects.HARM, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.HarmingCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.HarmingCapacity, 0), 1), Items.BRICK);
        this.register(new PotionBrewEffect(MobEffects.HEAL, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.HealingCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.HealingCapacity, 0), 1), Items.GLISTERING_MELON_SLICE);
        this.register(new PotionBrewEffect(MobEffects.HEALTH_BOOST, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.HealthBoostCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.HealthBoostCapacity, 0), 1800), Items.ENCHANTED_GOLDEN_APPLE);
        this.register(new PotionBrewEffect(MobEffects.INVISIBILITY, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.InvisibilityCost, 0), 3600), Items.GLASS_PANE);
        this.register(new PotionBrewEffect(MobEffects.JUMP, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.JumpBoostCost, 0), 3600), Items.RABBIT_FOOT);
        this.register(new PotionBrewEffect(MobEffects.LEVITATION, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.LevitationCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.LevitationCapacity, 0), 600), Items.SHULKER_SHELL);
        this.register(new PotionBrewEffect(MobEffects.LUCK, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.LuckCost, 0), 3600), Items.NAUTILUS_SHELL);
        this.register(new PotionBrewEffect(MobEffects.DIG_SLOWDOWN, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.MiningFatigueCost, 0), 1800), Items.PRISMARINE_SHARD);
        this.register(new PotionBrewEffect(MobEffects.CONFUSION, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.NauseaCost, 0), 900), ModItems.REFUSE_BOTTLE.get());
        this.register(new PotionBrewEffect(MobEffects.NIGHT_VISION, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.NightVisionCost, 0), 3600), Items.GOLDEN_CARROT);
        this.register(new PotionBrewEffect(MobEffects.POISON, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PoisonCost, 0), 900), Items.SPIDER_EYE);
        this.register(new PotionBrewEffect(MobEffects.REGENERATION, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.RegenerationCost, 0), 900), Items.GHAST_TEAR);
        this.register(new PotionBrewEffect(MobEffects.DAMAGE_RESISTANCE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ResistanceCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ResistanceCapacity, 0), 1800), Items.TURTLE_SCUTE);
        this.register(new PotionBrewEffect(MobEffects.SLOW_FALLING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SlowFallingCost, 0), 1800), Items.PHANTOM_MEMBRANE);
        this.register(new PotionBrewEffect(MobEffects.MOVEMENT_SLOWDOWN, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SlownessCost, 0), 1800), Items.CHAIN);
        this.register(new PotionBrewEffect(MobEffects.MOVEMENT_SPEED, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SpeedCost, 0), 3600), Items.SUGAR);
        this.register(new PotionBrewEffect(MobEffects.DAMAGE_BOOST, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.StrengthCost, 0), 3600), Items.BLAZE_POWDER);
        this.register(new PotionBrewEffect(MobEffects.UNLUCK, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.UnluckCost, 0), 3600), Items.POISONOUS_POTATO);
        this.register(new PotionBrewEffect(MobEffects.WATER_BREATHING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.WaterBreathingCost, 0), 3600), Items.PUFFERFISH);
        this.register(new PotionBrewEffect(MobEffects.WEAKNESS, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.WeaknessCost, 0), 1800), Items.FERMENTED_SPIDER_EYE);
        this.register(new PotionBrewEffect(MobEffects.WITHER, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.WitherCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.WitherCapacity, 0), 900), Items.WITHER_ROSE);

        //Goety
        this.register(new PotionBrewEffect(GoetyEffects.ARROWMANTIC, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ArrowmanticCost, 0), 900), Items.TARGET);
        this.register(new PotionBrewEffect(GoetyEffects.BOTTLING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BottlingCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BottlingCapacity, 0), 3600), ModItems.WITCH_HAT.get());
        this.register(new PotionBrewEffect(GoetyEffects.BOTTLING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BottlingCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BottlingCapacity, 0), 3600), ModItems.WITCH_HAT_HEDGE.get());
        this.register(new PotionBrewEffect(GoetyEffects.CLIMBING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ClimbingCost, 0), 3600), ModItems.SPIDER_EGG.get());
        this.register(new PotionBrewEffect(GoetyEffects.CORPSE_EATER, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.CorpseEaterCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.CorpseEaterCapacity, 0), 3600), Items.ZOMBIE_HEAD);
        this.register(new PotionBrewEffect(GoetyEffects.CURSED, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.CursedCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.CursedCapacity, 0), 600), ModItems.OCCULT_FABRIC.get());
        this.register(new PotionBrewEffect(GoetyEffects.DEFLECTIVE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.DeflectiveCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.DeflectiveCapacity, 0), 1800), Items.SHULKER_BOX);
        this.register(new PotionBrewEffect(GoetyEffects.ENDER_GROUND, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.EnderGroundCost, 0), 900), Items.ENDER_PEARL);
        this.register(new PotionBrewEffect(GoetyEffects.EVIL_EYE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.EvilEyeCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.EvilEyeCapacity, 0), 3600), ModBlocks.FORBIDDEN_GRASS.get().asItem());
        this.register(new PotionBrewEffect(GoetyEffects.EXPLOSIVE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ExplosiveCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ExplosiveCapacity, 0), 900), Items.CREEPER_HEAD);
        this.register(new PotionBrewEffect(GoetyEffects.FIERY_AURA, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FieryAuraCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FieryAuraCapacity, 0), 1800), Items.MAGMA_BLOCK);
        this.register(new PotionBrewEffect(GoetyEffects.FIRE_TRAIL, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FireTrailCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FireTrailCapacity, 0), 900), Items.LAVA_BUCKET);
        this.register(new PotionBrewEffect(GoetyEffects.FLAME_HANDS, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FlameHandsCost, 0), 3600), Items.FLINT_AND_STEEL);
        this.register(new PotionBrewEffect(GoetyEffects.FLAMMABLE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FlammableCost, 0), 1800), Items.SHORT_GRASS);
        this.register(new PotionBrewEffect(GoetyEffects.FLIMSY, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FlimsyCost, 0), 1800), Items.FEATHER);
        this.register(new PotionBrewEffect(GoetyEffects.FORTUNATE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FortunateCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FortunateCapacity, 0), 1800), Items.DIAMOND);
        this.register(new PotionBrewEffect(GoetyEffects.FREEZING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FreezingCost, 0), 900), Items.POWDER_SNOW_BUCKET);
        this.register(new PotionBrewEffect(GoetyEffects.FROG_LEG, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SwiftSwimCost, 0), 3600), ModItems.FEET_OF_FROG.get());
        this.register(new PotionBrewEffect(GoetyEffects.FROSTY_AURA, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FrostyAuraCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FrostyAuraCapacity, 0), 1800), Items.BLUE_ICE);
        this.register(new PotionBrewEffect(GoetyEffects.GOLD_TOUCHED, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.GoldTouchedCost, 0), 1800), Items.GOLD_NUGGET);
        this.register(new PotionBrewEffect(GoetyEffects.GRAVITY_PULSE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.GravityPulseCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.GravityPulseCapacity, 0), 3600), Items.RAW_IRON_BLOCK);
        this.register(new PotionBrewEffect(GoetyEffects.INSIGHT, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.InsightCost, 0), 3600), Items.WRITABLE_BOOK);
        this.register(new PotionBrewEffect(GoetyEffects.LEECHING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.LeechingCost, 0), 3600), Items.BEETROOT);
        this.register(new PotionBrewEffect(GoetyEffects.NYCTOPHOBIA, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.NyctophobiaCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.NyctophobiaCapacity, 0), 1800), Items.SCULK_SHRIEKER);
        this.register(new PotionBrewEffect(GoetyEffects.PHOTOSYNTHESIS, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PhotosynthesisCost, 0), 1800), Items.SUNFLOWER);
        this.register(new PotionBrewEffect(GoetyEffects.PLUNGE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PlungeCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PlungeCapacity, 0), 600), Items.ANVIL);
        this.register(new PotionBrewEffect(GoetyEffects.PRESSURE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PressureCost, 0), 1800), Items.SPYGLASS);
        this.register(new PotionBrewEffect(GoetyEffects.RADIANCE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.RadianceCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.RadianceCapacity, 0), 3600), Items.JACK_O_LANTERN);
        this.register(new PotionBrewEffect(GoetyEffects.RALLYING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.RallyingCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.RallyingCapacity, 0), 3600), Items.GOAT_HORN);
        this.register(new PotionBrewEffect(GoetyEffects.REPULSIVE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.RepulsiveCost, 0), 1800), Items.PISTON);
        this.register(new PotionBrewEffect(GoetyEffects.SAPPED, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SappedCost, 0), 1800), ModItems.SAVAGE_TOOTH.get());
        this.register(new PotionBrewEffect(GoetyEffects.SAVE_EFFECTS, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SaveEffectsCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SaveEffectsCapacity, 0), 6000), Items.ECHO_SHARD);
        this.register(new PotionBrewEffect(GoetyEffects.SHIELDING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ShieldingCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ShieldingCapacity, 0), 3600), Items.SHIELD);
        this.register(new PotionBrewEffect(GoetyEffects.STORMS_WRATH, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.StormsWrathCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.StormsWrathCapacity, 0), 3600), Items.LIGHTNING_ROD);
        this.register(new PotionBrewEffect(GoetyEffects.SUN_ALLERGY, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SunAllergyCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SunAllergyCapacity, 0), 3600), Items.SKELETON_SKULL);
        this.register(new PotionBrewEffect(GoetyEffects.SWIFT_SWIM, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SwiftSwimCost, 0), 3600), ModItems.COOKED_FEET_OF_FROG.get());
        this.register(new PotionBrewEffect(GoetyEffects.SWIRLING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SwirlingCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SwirlingCapacity, 0), 1800), Items.IRON_SWORD);
        this.register(new PotionBrewEffect(GoetyEffects.TRIPPING, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.TrippingCost, 0), 1800), Items.CRACKED_STONE_BRICKS);
        this.register(new PotionBrewEffect(GoetyEffects.VENOMOUS_HANDS, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.VenomousHandsCost, 0), 3600), ModItems.VENOMOUS_FANG.get());
        this.register(new PotionBrewEffect(GoetyEffects.VOID_TOUCHED, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.VoidTouchedCost, 0), 1800), ModItems.VOID_BOTTLE.get());
        this.register(new PotionBrewEffect(GoetyEffects.WILD_RAGE, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.WildRageCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.WildRageCapacity, 0), 900), ModItems.RAGING_MATTER.get());

        //Brew
        this.register(new BatsBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BatBurstCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BatBurstCapacity, 0)), Items.BEETROOT_SOUP);
        this.register(new BeesBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BeesCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BeesCapacity, 0)), Items.BEE_NEST);
        this.register(new BlindJumpBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.BlindJumpCost, 0)), Items.CHORUS_FRUIT);
        this.register(new ChopTreeBlockEffect(), Items.STONE_AXE);
        this.register(new CombustBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.CombustCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.CombustCapacity, 0)), Items.FIRE_CHARGE);
        this.register(new CorrosionBlockEffect(), Items.LILY_OF_THE_VALLEY);
        this.register(new DroughtBlockEffect(), Items.SPONGE);
        this.register(new ExplodeBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ExplodeCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ExplodeCapacity, 0)), Items.TNT);
        this.register(new ExtinguishBlockEffect(), Items.SEAGRASS);
        this.register(new FertilityBrewEffect(), Items.EGG);
        this.register(new FlayingBrewEffect(), Items.LEATHER);
        this.register(new FloodBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FloodingCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FloodingCapacity, 0)), Items.WET_SPONGE);
        this.register(new FreezeBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.FreezeCost, 0)), Items.PACKED_ICE);
        this.register(new GrowBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.GrowthCost, 0)), Items.BONE_MEAL);
        this.register(new GrowCactusBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.GrowCactusCost, 0)), Items.CACTUS);
        this.register(new GrowCaveVinesBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.GrowCaveVinesCost, 0)), Items.GLOW_BERRIES);
        this.register(new HarvestBlockEffect(), Items.WOODEN_HOE);
        this.register(new LaunchBrewEffect(), Items.FIREWORK_ROCKET);
        this.register(new LeafShellBlockEffect(), Items.PEONY);
        this.register(new LoveBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.LoveCost, 0)), Items.COCOA_BEANS);
        this.register(new MossifyBlockEffect(), Items.MOSS_BLOCK);
        this.register(new PartLavaBlockEffect(), Items.CAULDRON);
        this.register(new PartWaterBlockEffect(), Items.BUCKET);
        this.register(new PulverizeBlockEffect(), Items.IRON_BLOCK);
        this.register(new PurifyBrewEffect("purify_debuff", com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PurifyDebuffsCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PurifyDebuffsCapacity, 0), MobEffectCategory.BENEFICIAL, 0x385858, true), ModItems.WARTFUL_EGG.get());
        this.register(new PurifyBrewEffect("purify_buff", com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PurifyBuffsCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PurifyBuffsCapacity, 0), MobEffectCategory.HARMFUL, 0x374a4a, false), ModItems.WARPED_WARTFUL_EGG.get());
        this.register(new PruningBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.PruningCost, 0)), Items.STONE_HOE);
        this.register(new RaiseDeadBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.RaiseDeadCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.RaiseDeadCapacity, 0)), ModItems.GRAVE_DUST.get());
        //Buffed version of Vanilla effect
        this.register(new SaturationBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SaturationCost, 0)), Items.RABBIT_STEW);
        this.register(new ShearBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ShearCost, 0)), Items.SHEARS);
        this.register(new SnowBlockEffect(), Items.SNOW_BLOCK);
        this.register(new StripBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.StripArmorCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.StripArmorCapacity, 0)), Items.MANGROVE_ROOTS);
        this.register(new SweetBerriedEffect(), Items.SWEET_BERRIES);
        this.register(new ThornTrapBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.ThornTrapCost, 0)), Items.ROSE_BUSH);
        this.register(new TransposeBrewEffect(), Items.POPPED_CHORUS_FRUIT);
        this.register(new WebbedBrewEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.WebbedCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.WebbedCapacity, 0)), ModBlocks.SPIDER_NEST.get().asItem());
        for (Item item : BuiltInRegistries.ITEM){
            if (item instanceof BlockItem blockItem){
                if (blockItem.getBlock() instanceof InfestedBlock){
                    this.register(new InfestBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.InfestCost, 0)), blockItem);
                } else if (blockItem.getBlock() instanceof SaplingBlock saplingBlock){
                    // this.register(new GrowTreeBlockEffect(blockItem.getBlock(), saplingBlock.treeGrower), blockItem);
                    // TODO: Fix protected access to treeGrower
                    this.register(new GrowTreeBlockEffect(blockItem.getBlock(), null), blockItem);
                }
            } else if (item instanceof DyeItem dyeItem){
                this.register(new BrewColorEffect(dyeItem), dyeItem);
            }
        }
        for (EntityType<?> entityType : BuiltInRegistries.ENTITY_TYPE){
            BrewEffect brewEffect = null;
            Item item = SpawnEggItem.byId(entityType);
            if (entityType.getDescriptionId().contains("endermite")){
                brewEffect = new PotionBrewEffect(GoetyEffects.ENDER_FLUX, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.EnderFluxCost, 0), 900);
            }
            if (entityType.getDescriptionId().contains("silverfish")){
                brewEffect = new InfestBlockEffect(com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.InfestCost, 0));
            }
            if (entityType == EntityType.SNOW_GOLEM){
                brewEffect = new PotionBrewEffect(GoetyEffects.SNOW_SKIN, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SnowSkinCost, 0), com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.SnowSkinCapacity, 0), 1800);
            }
            if (entityType.is(ModTags.EntityTypes.VILLAGERS)){
                brewEffect = new PotionBrewEffect(MobEffects.REGENERATION, com.Polarice3.Goety.utils.ConfigHelper.getInt(BrewConfig.RegenerationCost, 0), 1800);
            }
            if (brewEffect != null) {
                if (item != null) {
                    this.register(brewEffect, item);
                }
                this.register(brewEffect, entityType);
            }
        }
    }

    private void register(BrewEffect effect, Item ingredient) {
        if(!this.effectIDs.containsKey(effect.getEffectID())) {
            this.effectIDs.put(effect.getEffectID(), effect);
        }
        if (!this.catalyst.containsKey(ingredient)){
            this.catalyst.put(ingredient, effect);
        }
        if (!this.catalystInverted.containsKey(effect.getEffectID())){
            this.catalystInverted.put(effect.getEffectID(), new ItemStack(ingredient));
        }
    }

    private void register(BrewEffect effect, EntityType<?> sacrifice) {
        if(!this.effectIDs.containsKey(effect.getEffectID())) {
            this.effectIDs.put(effect.getEffectID(), effect);
        }
        if (!this.sacrifice.containsKey(sacrifice)){
            this.sacrifice.put(sacrifice, effect);
        }
        if (!this.sacrificeInverted.containsKey(effect.getEffectID())){
            this.sacrificeInverted.put(effect.getEffectID(), sacrifice);
        }
    }

    private void modifierRegister(BrewModifier modifier, Item ingredient){
        if (!this.modifiers.containsKey(ingredient)){
            this.modifiers.put(ingredient, modifier);
        }
    }

    public BrewEffect getEffectFromCatalyst(Item ingredient){
        return this.catalyst.get(ingredient);
    }

    public BrewEffect getEffectFromSacrifice(EntityType<?> sacrifice){
        return this.sacrifice.get(sacrifice);
    }

    public ItemStack getCatalystFromEffect(String string){
        return this.catalystInverted.get(string);
    }

    public EntityType<?> getSacrificeFromEffect(String string){
        return this.sacrificeInverted.get(string);
    }

    public BrewModifier getModifier(Item ingredient){
        return this.modifiers.get(ingredient);
    }

    @Nullable
    public BrewEffect getBrewEffect(String string){
        for (BrewEffect brewEffect : this.effectIDs.values()){
            if (brewEffect.getDescriptionId().equals(string)){
                return brewEffect;
            }
        }
        return this.effectIDs.get(string);
    }

    @Nullable
    public BrewEffect getBrewEffect(CompoundTag compoundTag){
        if (compoundTag.contains("BrewId")){
            return this.effectIDs.get(compoundTag.getString("BrewId"));
        } else {
            return null;
        }
    }
}