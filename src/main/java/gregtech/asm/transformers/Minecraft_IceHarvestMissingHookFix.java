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

import gregtech.asm.GT_ASM;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import net.minecraft.launchwrapper.IClassTransformer;

/**
 * @author OvermindDL1
 */
public class Minecraft_IceHarvestMissingHookFix implements IClassTransformer  {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!transformedName.equals("net.minecraft.block.BlockIce")) return basicClass;

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);

		for (MethodNode m: classNode.methods) {
			if (m.name.equals("harvestBlock") || (m.name.equals("a") && m.desc.equals("(Lahb;Lyz;IIII)V"))) {
				GT_ASM.logger.info("Transforming net.minecraft.block.BlockIce.harvestBlock");
				m.instructions.clear();
				m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load this
				m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1)); // Load world
				m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 2)); // Load player
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 3)); // Load x
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 4)); // Load y
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 5)); // Load z
				m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 6)); // Load metadata
				m.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/minecraft/Replacements", "BlockIce_harvestBlock", "(Lnet/minecraft/block/BlockIce;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;IIII)V", false));
				m.instructions.add(new InsnNode(Opcodes.RETURN));
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
