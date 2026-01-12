package top.theillusivec4.curios.api;

import net.neoforged.neoforge.capabilities.Capability;
import top.theillusivec4.curios.api.type.capability.ICurio;

/**
 * Minimal stub for Curios API to allow compilation when Curios is not present.
 * This is NOT a functional replacement.
 */
public final class CuriosCapability {
    private CuriosCapability() {
    }

    public static final Capability<ICurio> ITEM = null;
}

