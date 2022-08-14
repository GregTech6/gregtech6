/**
 * Copyright (c) 2022 GregTech-6 Team
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

import gregtech.asm.GT_ASM;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

/**
 * @author OvermindDL1
 */
public class Minecraft_Feature_CreeperSwellToward implements IClassTransformer  {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!transformedName.equals("net.minecraft.entity.ai.EntityAICreeperSwell")) return basicClass;
		ClassNode classNode = GT_ASM.makeNodes(basicClass);

		// GT_ASM.writePrettyPrintedOpCodesToFile(classNode, "EntityAICreeperSwell.ops");
		
		for (MethodNode m: classNode.methods) {
			if (m.name.equals("shouldExecute") || (m.name.equals("a") && m.desc.equals("()Z"))) {
				GT_ASM.logger.info("Transforming net.minecraft.entity.ai.EntityAICreeperSwell.shouldExecute");
				AbstractInsnNode at = m.instructions.getFirst();
				// Skip to the `swellingCreeper` load first
				for(;at != null && at.getOpcode() != Opcodes.GETFIELD;at = at.getNext()) {
					GT_ASM.logger.info("Skipping "+at.getOpcode());
				}
				if (at == null) {
					GT_ASM.logger.warn("Could not find the swellingCreeper field instruction, was this transformed by something else?");
					return basicClass;
				}
				// Then delete rest
				while(at.getNext() != null) m.instructions.remove(at.getNext());
				// `swellingCreper` is left on the stack, so now call our replacement function
				m.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/minecraft/Replacements", "EntityAICreeperSwell_shouldExecute", "(Lnet/minecraft/entity/monster/EntityCreeper;)Z", false));
				// And return the result that we returned
				m.instructions.add(new InsnNode(Opcodes.IRETURN));
			}
		}
		
		return GT_ASM.writeByteArray(classNode);
	}
}
