package net.minecraft.world.level.material;

/**
 * Compatibility shim for older code that referenced {@code net.minecraft.world.level.material.FluidType}.
 * <p>
 * Minecraft 1.21 removed this type from vanilla; NeoForge uses its own fluid type APIs. This class
 * exists only to keep legacy signatures compiling during the port.
 */
public class FluidType {
}

