package za.co.infernos.goety.common.world;

import za.co.infernos.goety.common.entities.ModEntityType;
import za.co.infernos.goety.config.MobsConfig;
import za.co.infernos.goety.init.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.neoforged.neoforge.common.world.ModifiableStructureInfo;

public class ModLevelRegistry {

    public static void addBiomeSpawns(Holder<Biome> biome, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (!biome.is(ModTags.Biomes.COMMON_BLACKLIST) && !biome.is(biomeResourceKey -> biomeResourceKey.registry().getNamespace().contains("alexscaves"))){
            if (biome.is(ModTags.Biomes.REAPER_SPAWN) && !biome.is(ModTags.Biomes.REAPER_EXCLUDE_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.ReaperSpawnWeight, 0) > 0){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.REAPER.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.ReaperSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.ReaperSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.ReaperSpawnMaxCount, 0)));
            }
            if (biome.is(ModTags.Biomes.WRAITH_SPAWN) && !biome.is(ModTags.Biomes.WRAITH_EXCLUDE_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WraithSpawnWeight, 0) > 0){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.WRAITH.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WraithSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WraithSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WraithSpawnMaxCount, 0)));
            }
            if (biome.is(ModTags.Biomes.MUCK_WRAITH_SPAWN) && !biome.is(ModTags.Biomes.MUCK_WRAITH_EXCLUDE_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.MuckWraithSpawnWeight, 0) > 0){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.MUCK_WRAITH.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.MuckWraithSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.MuckWraithSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.MuckWraithSpawnMaxCount, 0)));
            }
            if (biome.is(ModTags.Biomes.WEB_SPIDER_SPAWN) && !biome.is(ModTags.Biomes.WEB_SPIDER_EXCLUDE_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WebSpiderSpawnWeight, 0) > 0){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.WEB_SPIDER.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WebSpiderSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WebSpiderSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WebSpiderSpawnMaxCount, 0)));
            }
            if (biome.is(ModTags.Biomes.ICY_SPIDER_SPAWN) && !biome.is(ModTags.Biomes.ICY_SPIDER_EXCLUDE_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.IcySpiderSpawnWeight, 0) > 0){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.ICY_SPIDER.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.IcySpiderSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.IcySpiderSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.IcySpiderSpawnMaxCount, 0)));
            }
            if (biome.is(ModTags.Biomes.NECROMANCER_SPAWN) && !biome.is(ModTags.Biomes.NECROMANCER_EXCLUDE_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.NecromancerSpawnWeight, 0) > 0){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.NECROMANCER.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.NecromancerSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.NecromancerSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.NecromancerSpawnMaxCount, 0)));
            }
            if (biome.is(ModTags.Biomes.WARLOCK_SPAWN) && !biome.is(ModTags.Biomes.WARLOCK_EXCLUDE_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WarlockSpawnWeight, 0) > 0){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.WARLOCK.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WarlockSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WarlockSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WarlockSpawnMaxCount, 0)));
            }
            if (biome.is(ModTags.Biomes.HERETIC_SPAWN) && !biome.is(ModTags.Biomes.HERETIC_EXCLUDE_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.HereticSpawnWeight, 0) > 0){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.HERETIC.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.HereticSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.HereticSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.HereticSpawnMaxCount, 0)));
            }
            if (biome.is(ModTags.Biomes.MAVERICK_SPAWN) && !biome.is(ModTags.Biomes.MAVERICK_EXCLUDE_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.MaverickSpawnWeight, 0) > 0){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.MAVERICK.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.MaverickSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.MaverickSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.MaverickSpawnMaxCount, 0)));
            }
        }
        if (biome.is(Biomes.SOUL_SAND_VALLEY)){
            if (za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.ReaperSpawnWeight, 0) > 0) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.REAPER.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.ReaperSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.ReaperSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.ReaperSpawnMaxCount, 0)));
                builder.getMobSpawnSettings().addMobCharge(ModEntityType.REAPER.get(), 0.7D, 0.15D);
            }
            if (za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WraithSpawnWeight, 0) > 0) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityType.WRAITH.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WraithSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WraithSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.WraithSpawnMaxCount, 0)));
                builder.getMobSpawnSettings().addMobCharge(ModEntityType.WRAITH.get(), 0.7D, 0.15D);
            }
        }
    }

    public static boolean startName(ResourceKey<Biome> biomeResourceKey, String string){
        return biomeResourceKey.registry().getNamespace().startsWith(string);
    }

    public static boolean containsName(ResourceKey<Biome> biomeResourceKey, String string){
        return biomeResourceKey.registry().getNamespace().contains(string);
    }

    public static void addStructureSpawns(Holder<Structure> structure, ModifiableStructureInfo.StructureInfo.Builder builder) {
        if (za.co.infernos.goety.utils.ConfigHelper.getBoolean(MobsConfig.NecromancerSpawnStructure, false) && structure.is(ModTags.Structures.NECROMANCER_SPAWN) && za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.NecromancerSpawnWeight, 0) > 0) {
            builder.getStructureSettings().getOrAddSpawnOverrides(MobCategory.MONSTER).addSpawn(new MobSpawnSettings.SpawnerData(ModEntityType.NECROMANCER.get(), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.NecromancerSpawnWeight, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.NecromancerSpawnMinCount, 0), za.co.infernos.goety.utils.ConfigHelper.getInt(MobsConfig.NecromancerSpawnMaxCount, 0)));
        }
    }
}