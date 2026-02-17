package za.co.infernos.goety.init;

import za.co.infernos.goety.Goety;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import za.co.infernos.goety.compat.fml.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(Registries.PAINTING_VARIANT, Goety.MOD_ID);

    public static final DeferredHolder<PaintingVariant, PaintingVariant> APOSTLE = PAINTING_VARIANTS.register(
            "apostle", () -> new PaintingVariant(16, 32, Goety.location("apostle"))
    );

    public static void init() {
        PAINTING_VARIANTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
