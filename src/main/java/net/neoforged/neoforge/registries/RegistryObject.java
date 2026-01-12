package net.neoforged.neoforge.registries;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Compatibility shim for older Forge/NeoForge code that still uses {@code RegistryObject}.
 * <p>
 * NeoForge 1.21+ migrated to {@link DeferredHolder}. This wrapper keeps existing code compiling by
 * delegating to a {@link DeferredHolder}.
 */
public final class RegistryObject<T> implements Supplier<T> {
    private final DeferredHolder<?, T> delegate;

    private RegistryObject(DeferredHolder<?, T> delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
    }

    public static <R, T extends R> RegistryObject<T> of(DeferredHolder<R, T> delegate) {
        return new RegistryObject<>(delegate);
    }

    @Override
    public T get() {
        return delegate.get();
    }

    public ResourceLocation getId() {
        return delegate.getId();
    }

    public boolean isPresent() {
        return delegate.isBound();
    }

    public Optional<T> asOptional() {
        return delegate.asOptional();
    }

    /**
     * Compatibility helper for 1.21+ APIs that now take {@link Holder}s instead of raw registry values.
     * <p>
     * {@link DeferredHolder} implements {@link Holder}, so we can safely expose it here for callers.
     */
    @SuppressWarnings("unchecked")
    public Holder<T> getHolder() {
        return (Holder<T>) delegate;
    }

    @Override
    public String toString() {
        return "RegistryObject[" + delegate + "]";
    }
}

