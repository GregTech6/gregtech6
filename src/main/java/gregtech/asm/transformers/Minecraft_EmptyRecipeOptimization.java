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
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import gregtech.asm.GT_ASM;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * @author OvermindDL1
 */
public class Minecraft_EmptyRecipeOptimization implements IClassTransformer  {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!transformedName.equals("net.minecraft.item.crafting.CraftingManager")) return basicClass;
		ClassNode classNode = GT_ASM.makeNodes(basicClass);
		
		for (MethodNode m: classNode.methods) {
			if (m.name.equals("findMatchingRecipe") || (m.name.equals("a") && m.desc.equals("(Laae;Lahb;)Ladd;"))) {
				GT_ASM.logger.info("Transforming net.minecraft.item.crafting.CraftingManager.findMatchingRecipe");
				AbstractInsnNode at = m.instructions.getFirst();
				// Skip to the ICONST_2
				for(;at != null && at.getOpcode() != Opcodes.ICONST_2; at = at.getNext());
				if(at == null) {
					GT_ASM.logger.warn("Reached `null` in `at` too soon!  No changes made, bailing!");
					return basicClass;
				}
				// Then back up once.
				at = at.getPrevious();
				// Now build the instructions to insert:
				InsnList insert = new InsnList();
				// Need to load `i` to the stack
				insert.add(new VarInsnNode(Opcodes.ILOAD, 3));
				// Need a label to jump to if it is not zero
				LabelNode after = new LabelNode();
				// And test if it is not zero and jump to that label if so
				insert.add(new JumpInsnNode(Opcodes.IFNE, after));
				// If we didn't jump then it is zero, so return null by first pushing a null onto the stack:
				insert.add(new InsnNode(Opcodes.ACONST_NULL));
				// Then returning...
				insert.add(new InsnNode(Opcodes.ARETURN));
				// And now put in the LabelNode that we jump to if it was not zero to continue the rest of the function after this:
				insert.add(after);
				// And lastly insert the new instructions right before the next `if` conditional
				m.instructions.insertBefore(at, insert);
				// This basically turned this:
				//   if (i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && itemstack.getItem().isRepairable())
				// Into this:
				//    if(i==0) return null; if (i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && itemstack.getItem().isRepairable())
			}
		}
		
		return GT_ASM.writeByteArray(classNode);
	}
}
