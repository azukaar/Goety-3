package com.Polarice3.Goety.init;

import com.Polarice3.Goety.Goety;
import com.Polarice3.Goety.common.capabilities.lichdom.ILichdom;
import com.Polarice3.Goety.common.capabilities.lichdom.LichImp;
import com.Polarice3.Goety.common.capabilities.misc.IMisc;
import com.Polarice3.Goety.common.capabilities.misc.MiscImp;
import com.Polarice3.Goety.common.capabilities.soulenergy.ISoulEnergy;
import com.Polarice3.Goety.common.capabilities.soulenergy.SEImp;
import com.Polarice3.Goety.common.capabilities.witchbarter.IWitchBarter;
import com.Polarice3.Goety.common.capabilities.witchbarter.WitchBarterImp;
import com.Polarice3.Goety.utils.LichdomHelper;
import com.Polarice3.Goety.utils.MiscCapHelper;
import com.Polarice3.Goety.utils.SEHelper;
import com.Polarice3.Goety.common.capabilities.witchbarter.WitchBarterProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * Registry for data attachments (replacement for the old Capability system in NeoForge 1.21+)
 */
public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = 
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Goety.MOD_ID);

    public static final Supplier<AttachmentType<ILichdom>> LICHDOM = ATTACHMENT_TYPES.register(
            "lichdom", () -> AttachmentType.builder(() -> (ILichdom) new LichImp())
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<ISoulEnergy>> SOUL_ENERGY = ATTACHMENT_TYPES.register(
            "soul_energy", () -> AttachmentType.builder(() -> (ISoulEnergy) new SEImp())
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<IMisc>> MISC = ATTACHMENT_TYPES.register(
            "misc", () -> AttachmentType.builder(() -> (IMisc) new MiscImp())
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<IWitchBarter>> WITCH_BARTER = ATTACHMENT_TYPES.register(
            "witch_barter", () -> AttachmentType.builder(() -> (IWitchBarter) new WitchBarterImp())
                    .build()
    );

    public static void init(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
