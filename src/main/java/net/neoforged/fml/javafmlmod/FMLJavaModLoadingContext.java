package net.neoforged.fml.javafmlmod;

import net.neoforged.bus.api.IEventBus;

/**
 * Compatibility shim for older code that used {@code FMLJavaModLoadingContext}.
 * <p>
 * NeoForge 1.21+ prefers constructor injection for the mod event bus. This exists only to keep the
 * project compiling while the migration is in progress.
 */
public final class FMLJavaModLoadingContext {
    private static final FMLJavaModLoadingContext INSTANCE = new FMLJavaModLoadingContext();

    private FMLJavaModLoadingContext() {}

    public static FMLJavaModLoadingContext get() {
        return INSTANCE;
    }

    public IEventBus getModEventBus() {
        return null;
    }
}

