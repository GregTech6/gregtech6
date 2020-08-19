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

package gregtech.asm.transformers;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodNode;

import net.minecraft.launchwrapper.IClassTransformer;

/**
 * @author OvermindDL1
 */
public class Minecraft_IceHarvestMissingHookFix implements IClassTransformer  {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!name.equals("alp") && !name.equals("net.minecraft.block.BlockIce")) return basicClass;

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);

		for (MethodNode m: classNode.methods) {
			if (m.name.equals("harvestBlock")) {
				AbstractInsnNode end = m.instructions.getLast();
				while(end.getOpcode() != Opcodes.ACONST_NULL) end = end.getPrevious();
				end = end.getNext(); // Include the actual harvesters.set(null) call

				AbstractInsnNode start = end.getPrevious();
				while(!(start instanceof FieldInsnNode && ((FieldInsnNode)start).name.equals("harvesters"))) start = start.getPrevious();
				start = start.getPrevious(); // Skip the second harvesters call to get the first one
				while(!(start instanceof FieldInsnNode && ((FieldInsnNode)start).name.equals("harvesters"))) start = start.getPrevious();
				start = start.getPrevious(); // Include the player argument passed to the harvesters.set(...) call

				AbstractInsnNode label = m.instructions.getLast().getPrevious(); // Skip last-most LabelNode
				while(!(label instanceof LabelNode)) label = label.getPrevious();

				while(start != end) {
					AbstractInsnNode next = start.getNext();
					m.instructions.remove(start);
					m.instructions.insertBefore(label, start);
					start = next;
				}
				m.instructions.remove(end);
				m.instructions.insertBefore(label, end);
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
