package net.minecraft.world.entity;

/**
 * Compatibility shim for older code that referenced {@code MobType}.
 * <p>
 * Minecraft 1.21 removed the MobType system; most logic should be migrated to {@code EntityTypeTags}
 * checks instead. This enum exists only to keep legacy code compiling during the port.
 */
public enum MobType {
    UNDEAD,
    ARTHROPOD,
    ILLAGER,
    WATER
}

