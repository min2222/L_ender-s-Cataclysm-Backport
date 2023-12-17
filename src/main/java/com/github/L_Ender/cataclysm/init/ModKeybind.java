package com.github.L_Ender.cataclysm.init;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeybind {

    public static final KeyMapping KEY_ABILITY = new KeyMapping("key.cataclysm.ability", KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, "key.categories.cataclysm");
}
