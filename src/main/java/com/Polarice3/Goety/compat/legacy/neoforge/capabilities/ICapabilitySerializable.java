package com.Polarice3.Goety.compat.legacy.neoforge.capabilities;

/**
 * Compatibility shim for older capability code.
 * <p>
 * NeoForge 1.21+ revamped capabilities; this exists only to keep legacy providers compiling.
 */
public interface ICapabilitySerializable<T> {
    T serializeNBT();
    void deserializeNBT(T nbt);
}

