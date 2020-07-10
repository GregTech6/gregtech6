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

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

import java.util.Arrays;

// ASM Mods will get loaded before all other mods, regardless of any ordering specified.
public class GT6_ASM_Mod extends DummyModContainer {
    public GT6_ASM_Mod() {
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = "GT6_ASM_Mod";
        meta.name = "GT6_ASM_Mod";
        meta.version = "6.13.02";
        meta.credits = "";
        meta.authorList = Arrays.asList("GregoriusTechneticies", "OvermindDL1");
        meta.description = "";
        meta.url = "";
        meta.updateUrl = "";
        meta.screenshots = new String[0];
        meta.logoFile = "";
    }

    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }
}
