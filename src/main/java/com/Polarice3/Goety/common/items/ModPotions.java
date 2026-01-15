package com.Polarice3.Goety.common.items;

import com.Polarice3.Goety.Goety;
import com.Polarice3.Goety.common.effects.GoetyEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class ModPotions {
    public static DeferredRegister<Potion> POTIONS = DeferredRegister.create(NeoForgeRegistries.POTIONS, Goety.MOD_ID);

    public static void init() {
        ModPotions.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Potion> CLIMBING = POTIONS.register("climbing",
            () -> new Potion(new MobEffectInstance(GoetyEffects.CLIMBING.getHolder().get(), 3600)));
    public static final RegistryObject<Potion> LONG_CLIMBING = POTIONS.register("long_climbing",
            () -> new Potion("climbing", new MobEffectInstance(GoetyEffects.CLIMBING.getHolder().get(), 9600)));
}