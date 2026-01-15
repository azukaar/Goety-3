package com.Polarice3.Goety.init;

import com.Polarice3.Goety.Goety;
import com.Polarice3.Goety.common.blocks.ModBlocks;
import com.Polarice3.Goety.common.items.ModItems;
import com.Polarice3.Goety.common.items.ModSpawnEggs;
import com.Polarice3.Goety.common.items.ServantSpawnEggs;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryObject;

import java.util.Comparator;
import java.util.function.Predicate;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, Goety.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register(Goety.MOD_ID,
            () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.TOTEM_OF_SOULS.get().getDefaultInstance())
                    .title(Component.translatable("itemGroup.goety"))
                    .withSearchBar()
                    .displayItems((parameters, output) -> {
                        // Patchouli integration disabled for now (no 1.21.1 dependency pinned in build
                        // yet).
                        output.accept(ModItems.TOTEM_OF_SOULS.get().getEmptyTotem());
                        output.accept(ModItems.TOTEM_OF_SOULS.get().getFilledTotem());
                        output.accept(ModItems.TOTEM_OF_ROOTS.get().getEmptyTotem());
                        output.accept(ModItems.TOTEM_OF_ROOTS.get().getFilledTotem());
                        ModItems.ITEMS.getEntries().forEach(i -> {
                            if (i.isPresent()) {
                                if (!ModItems.shouldSkipCreativeModTab(i.get()) && !(i.get() instanceof BlockItem)
                                        && !ModItems.isFocus(i.get())) {
                                    output.accept(i.get());
                                }
                            }
                        });
                        parameters.holders().lookup(Registries.PAINTING_VARIANT).ifPresent((p_270026_) -> {
                            generatePresetPaintings(output, p_270026_, (p_270037_) -> {
                                return p_270037_.is(ModTags.Paintings.MODDED_PAINTINGS);
                            }, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        });
                        ModSpawnEggs.ITEMS.getEntries().forEach(i -> {
                            if (i.isPresent()) {
                                output.accept(i.get());
                            }
                        });
                    }).build());

    public static final RegistryObject<CreativeModeTab> BLOCK_TAB = CREATIVE_MODE_TABS.register(Goety.MOD_ID + "_block",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModBlocks.SHADE_STONE_CHISELED_BLOCK.get().asItem().getDefaultInstance())
                    .title(Component.translatable("itemGroup.goety.block"))
                    .withSearchBar()
                    .displayItems((parameters, output) -> {
                        ModItems.ITEMS.getEntries().forEach(i -> {
                            if (i.isPresent()) {
                                if (i.get() instanceof BlockItem) {
                                    output.accept(i.get());
                                }
                            }
                        });
                    }).build());

    public static final RegistryObject<CreativeModeTab> FOCUS_TAB = CREATIVE_MODE_TABS.register(Goety.MOD_ID + "_focus",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.FOCUS_BAG.get().getDefaultInstance())
                    .title(Component.translatable("itemGroup.goety.focus"))
                    .displayItems((parameters, output) -> {
                        ModItems.ITEMS.getEntries().forEach(i -> {
                            if (i.isPresent()) {
                                if (ModItems.isFocus(i.get())) {
                                    output.accept(i.get());
                                }
                            }
                        });
                    }).build());

    public static final RegistryObject<CreativeModeTab> SERVANT_TAB = CREATIVE_MODE_TABS
            .register(Goety.MOD_ID + "_servants", () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.SOUL_JAR.get().getDefaultInstance())
                    .title(Component.translatable("itemGroup.goety.servant"))
                    .displayItems((parameters, output) -> {
                        ServantSpawnEggs.ITEMS.getEntries().forEach(i -> {
                            if (i.isPresent()) {
                                output.accept(i.get());
                            }
                        });
                    }).build());

    private static final Comparator<Holder<PaintingVariant>> PAINTING_COMPARATOR = Comparator.comparing(Holder::value,
            Comparator.<PaintingVariant>comparingInt((p_270004_) -> {
                return p_270004_.value().height() * p_270004_.value().width();
            }).thenComparing(p -> p.value().width()));

    private static void generatePresetPaintings(CreativeModeTab.Output p_271007_,
            HolderLookup.RegistryLookup<PaintingVariant> p_270618_, Predicate<Holder<PaintingVariant>> p_270878_,
            CreativeModeTab.TabVisibility p_270261_) {
        p_270618_.listElements().filter(p_270878_).forEach((p_269979_) -> {
            ItemStack itemstack = new ItemStack(ModItems.HAUNTED_PAINTING.get());
            CompoundTag compoundtag = itemstack.getOrCreateTagElement("EntityTag");
            Painting.storeVariant(compoundtag, p_269979_);
            p_271007_.accept(itemstack, p_270261_);
        });
    }

}