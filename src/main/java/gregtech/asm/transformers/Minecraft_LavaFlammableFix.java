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
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import gregtech.asm.GT_ASM;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * @author OvermindDL1
 */
public class Minecraft_LavaFlammableFix implements IClassTransformer  {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!transformedName.equals("net.minecraft.block.BlockStaticLiquid")) return basicClass;
		ClassNode classNode = GT_ASM.makeNodes(basicClass);
		
		for (MethodNode m: classNode.methods) {
			if (m.name.equals("isFlammable") || m.name.equals("o")) {
				GT_ASM.logger.info("Transforming net.minecraft.block.BlockStaticLiquid.isFlammable");
				m.instructions.clear();
				m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1)); // Load world
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 2)); // Load x
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 3)); // Load y
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 4)); // Load z
				m.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/minecraft/Replacements", "BlockStaticLiquid_isFlammable", "(Lnet/minecraft/world/World;III)Z", false));
				m.instructions.add(new InsnNode(Opcodes.IRETURN));
			} else if (m.name.equals("updateTick") || (m.name.equals("a") && m.desc.equals("(Lahb;IIILjava/util/Random;)V"))) {
				GT_ASM.logger.info("Transforming net.minecraft.block.BlockStaticLiquid.updateTick");
				m.instructions.clear();
				m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load this
				m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1)); // Load world
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 2)); // Load x
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 3)); // Load y
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 4)); // Load z
				m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 5)); // Load Random
				m.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/minecraft/Replacements", "BlockStaticLiquid_updateTick", "(Lnet/minecraft/block/BlockStaticLiquid;Lnet/minecraft/world/World;IIILjava/util/Random;)V", false));
				m.instructions.add(new InsnNode(Opcodes.RETURN));
			}
		}
		
		return GT_ASM.writeByteArray(classNode);
	}
}
