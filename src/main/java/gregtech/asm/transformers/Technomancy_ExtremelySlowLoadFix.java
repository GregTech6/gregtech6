/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregtech.asm.transformers;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import gregtech.asm.GT_ASM;
import net.minecraft.launchwrapper.IClassTransformer;

/* Technomancy's ore dict processing step takes like 20 minutes on my computer to load with my modpack because it
 * exponentially goes through the oredict, just to do stuff that GT6 already does but better and faster, so disable that
 * to load a whole lot faster.  With just GT6, Thaumcraft, and Technomancy it still drops loading time from 5m to 1m20s.
 *
 * TODO This probably breaks technomancy's ore processing, could be fixed by manually populating it with GT6 data.
 * To populate it would just involve calling this with all ore/ingot material combos:
 * * `theflogat.technomancy.util.Ore.newOre(String oreDictName, ItemStack ingot, String nameNoPrefix)`
 * Where `oreDictName` is `"oreMaterial"` for every given material and nameNoPrefix is the corresponding `"Material" and
 * `ingot` is the ItemStack output when that `"oreMaterial"` is smelted in a vanilla(ish?) furnace, plus whatever else
 * might be appropriate.
 */
/**
 * @author OvermindDL1
 */
public class Technomancy_ExtremelySlowLoadFix implements IClassTransformer {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!name.equals("theflogat.technomancy.util.Ore")) return basicClass;
		ClassNode classNode = GT_ASM.makeNodes(basicClass);
		
		for (MethodNode m: classNode.methods) {
			if (m.name.equals("init") && m.desc.equals("()V")) {
				GT_ASM.logger.info("Transforming theflogat.technomancy.util.Ore.init");
				m.instructions.clear();
				m.instructions.add(new InsnNode(Opcodes.RETURN));
			}
		}
		
		return GT_ASM.writeByteArray(classNode);
	}
}
