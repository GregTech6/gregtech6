/**
 * Copyright (c) 2023 GregTech-6 Team
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
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import gregtech.asm.GT_ASM;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * @author Gregorius Techneticies, OvermindDL1
 */
public class Minecraft_ZombieVillagerConversion implements IClassTransformer  {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!transformedName.equals("net.minecraft.entity.monster.EntityZombie")) return basicClass;
		ClassNode classNode = GT_ASM.makeNodes(basicClass);

		//GT_ASM.writePrettyPrintedOpCodesToFile(classNode, "zombie.ops");
		
		for (MethodNode m: classNode.methods) {
			if (m.name.equals("onKillEntity") || (m.name.equals("a") && m.desc.equals("(Lsv;)V"))) {
				GT_ASM.logger.info("Transforming net.minecraft.entity.monster.EntityZombie.onKillEntity");

				AbstractInsnNode cur = m.instructions.getFirst();

				// Get to first label
				while(!(cur instanceof LabelNode)) cur = cur.getNext();

				// Skip the super call to the next label
				cur = cur.getNext();
				while(!(cur instanceof LabelNode)) cur = cur.getNext();

				// Skip the line number label to the next label
				cur = cur.getNext();

				// Now wipe the rest of the function and replace with a static call to the replacement
				cur = cur.getNext();
				while(cur != null) {
					AbstractInsnNode prior = cur;
					cur = cur.getNext();
					m.instructions.remove(prior);
				}

				// The static replacement call
				m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load this
				m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1)); // Load victim
				m.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/minecraft/Replacements", "EntityZombie_onKillEntity", "(Ljava/lang/Object;Ljava/lang/Object;)V", false));
				m.instructions.add(new InsnNode(Opcodes.RETURN));
			}
		}

		// GT_ASM.writePrettyPrintedOpCodesToFile(classNode, "zombie-post.ops");
		
		return GT_ASM.writeByteArraySelfReferenceFixup(classNode);
	}
}
