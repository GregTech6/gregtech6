/**
 * Copyright (c) 2020 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import gregtech.asm.transformers.CoFHCore_CrashFix;

import java.io.File;
import java.util.Map;

@Name("GT6_ASM")
@MCVersion("1.7.10")
@SortingIndex(1000) // Sorting index with other coremods, for example DragonAPI is 1001
@TransformerExclusions({"gregtech.asm"}) // Array of strings of package or class names to ignore for this coremod
public class GT6_ASM_Main implements IFMLLoadingPlugin {
    public static File location; // Useful to get the path to the coremod to grab other files if needed

    public GT6_ASM_Main() {
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                // These are run in the order specified.
                // Put the full class names of the transformers to run, can just `getName` on the classname if it can be
                // classloaded inline (doesn't require really anything else but ASM calls) such as:
                CoFHCore_CrashFix.class.getName()
        };
    }

    @Override
    public String getModContainerClass() {
        return GT6_ASM_Mod.class.getName();
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        location = (File)data.get("coremodLocation");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
