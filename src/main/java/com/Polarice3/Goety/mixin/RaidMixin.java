package com.Polarice3.Goety.mixin;

import com.Polarice3.Goety.Goety;
import com.Polarice3.Goety.common.entities.ModEntityType;
import com.Polarice3.Goety.config.MobsConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Raid.class)
public abstract class RaidMixin {
    @Shadow
    @Final
    private ServerLevel level;
    @Unique
    private int goety$lastAugmentedWave = -1;

    @Shadow
    public abstract int getGroupsSpawned();

    @Shadow
    public abstract void addWaveMob(int p_37712_, Raider p_37713_, boolean p_37714_);

    @ModifyVariable(at = @At(value = "STORE", ordinal = 0), method = "spawnGroup")
    private Raider spawnCustomRaider(Raider raider, BlockPos blockPos) {
        if (com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.ArmoredRavagerRaid, false)){
            if (this.level.random.nextFloat() < (0.25F + this.level.getCurrentDifficultyAt(raider.blockPosition()).getSpecialMultiplier())) {
                if (raider.getType() == EntityType.RAVAGER){
                    raider = ModEntityType.ARMORED_RAVAGER.get().create(this.level);
                    if (raider != null){
                        return raider;
                    }
                }
            }
        }
        return raider;
    }

    @Inject(method = "spawnGroup", at = @At("TAIL"))
    private void goety$spawnConfiguredRaiders(BlockPos blockPos, CallbackInfo ci) {
        int wave = this.getGroupsSpawned();
        if (wave <= 0 || this.goety$lastAugmentedWave == wave) {
            return;
        }
        this.goety$lastAugmentedWave = wave;
        int injected = 0;

        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.WARLOCK.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.WarlockRaid, true), MobsConfig.WarlockRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.MAVERICK.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.MaverickRaid, true), MobsConfig.MaverickRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.HERETIC.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.HereticRaid, true), MobsConfig.HereticRaidCount.get(), wave);

        if (!com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.IllagerRaid, false)) {
            if (com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.RaidAugmentDebug, false) && injected > 0) {
                Goety.LOGGER.info("Goety raid augment: wave {} injected {} custom raiders", wave, injected);
            }
            return;
        }

        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.PIKER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.PikerRaid, true), MobsConfig.PikerRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.RIPPER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.RipperRaid, true), MobsConfig.RipperRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.CRUSHER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.CrusherRaid, true), MobsConfig.CrusherRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.STORM_CASTER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.StormCasterRaid, true), MobsConfig.StormCasterRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.CRYOLOGER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.CryologerRaid, true), MobsConfig.CryologerRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.PREACHER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.PreacherRaid, true), MobsConfig.PreacherRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.CONQUILLAGER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.ConquillagerRaid, true), MobsConfig.ConquillagerRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.INQUILLAGER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.InquillagerRaid, true), MobsConfig.InquillagerRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.ENVIOKER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.EnviokerRaid, true), MobsConfig.EnviokerRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.SORCERER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.SorcererRaid, true), MobsConfig.SorcererRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.MINISTER.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.MinisterRaid, true), MobsConfig.MinisterRaidCount.get(), wave);
        injected += this.goety$spawnWaveRaiders(blockPos, ModEntityType.HOSTILE_REDSTONE_GOLEM.get(), com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.HostileRedstoneGolemRaid, true), MobsConfig.HostileRedstoneGolemRaidCount.get(), wave);

        EntityType<? extends Raider> hrmType = ModEntityType.HOSTILE_REDSTONE_MONSTROSITY.get();
        if (com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.HRMSpawnNoRaiders, false)) {
            hrmType = ModEntityType.RAID_BOSS_SUMMON.get();
        }
        injected += this.goety$spawnWaveRaiders(blockPos, hrmType, com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.HostileRedstoneMonstrosityRaid, true), MobsConfig.HostileRedstoneMonstrosityRaidCount.get(), wave);

        if (com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MobsConfig.RaidAugmentDebug, false) && injected > 0) {
            Goety.LOGGER.info("Goety raid augment: wave {} injected {} custom raiders", wave, injected);
        }
    }

    private int goety$spawnWaveRaiders(BlockPos center, EntityType<? extends Raider> type, boolean enabled, List<? extends Integer> waveCounts, int wave) {
        if (!enabled || waveCounts == null || waveCounts.isEmpty()) {
            return 0;
        }

        int index = Math.max(0, Math.min(waveCounts.size() - 1, wave - 1));
        int count = Math.max(0, waveCounts.get(index));
        int injected = 0;
        Raid raid = (Raid) (Object) this;
        for (int i = 0; i < count; i++) {
            Raider raider = type.create(this.level);
            if (raider == null) {
                continue;
            }
            BlockPos spawnPos = center.offset(this.level.random.nextInt(7) - 3, 0, this.level.random.nextInt(7) - 3);
            raider.finalizeSpawn(this.level, this.level.getCurrentDifficultyAt(spawnPos), MobSpawnType.EVENT, null);
            raider.setCanJoinRaid(true);
            raider.setCurrentRaid(raid);
            raider.setWave(wave);
            raider.setTicksOutsideRaid(0);
            raider.setPos(spawnPos.getX() + 0.5D, spawnPos.getY(), spawnPos.getZ() + 0.5D);
            this.addWaveMob(wave, raider, false);
            this.level.addFreshEntity(raider);
            injected++;
        }
        return injected;
    }
}
