package com.Polarice3.Goety.utils;

import com.Polarice3.Goety.mixin.ClientModLoaderAccessor;
import net.neoforged.fml.ModLoadingException;

public class ClientUtils {
    public static boolean noLoadingExceptions() {
        System.out.println();
        ModLoadingException error = ClientModLoaderAccessor.getError();
        return error == null;
    }
}