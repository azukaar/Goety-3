package com.Polarice3.Goety.common.world.features.trees;

import com.Polarice3.Goety.common.world.features.ConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public final class HauntedTree {
    public static final TreeGrower GROWER = new TreeGrower(
            "haunted",
            Optional.of(ConfiguredFeatures.SAPLING_HAUNTED_TREE),
            Optional.empty(),
            Optional.empty()
    );

    private HauntedTree() {}
}