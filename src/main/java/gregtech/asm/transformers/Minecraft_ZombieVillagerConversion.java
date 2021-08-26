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
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

/**
 * @author Gregorius Techneticies
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

				// Delete to the next label (this is deleting the `(this.worldObj.difficultySetting == EnumDifficulty.NORMAL || this.worldObj.difficultySetting == EnumDifficulty.HARD) &&` bit)
				cur = cur.getNext();
				while(!(cur instanceof LabelNode)) {
					cur = cur.getNext();
					m.instructions.remove(cur.getPrevious());
				}

				// Now in the instanceof EntityVillager check label, skip to the next label
				cur = cur.getNext();
				while(!(cur instanceof LabelNode)) cur = cur.getNext();

				// Now in the `if (this.worldObj.difficultySetting != EnumDifficulty.HARD && this.rand.nextBoolean())` test, remove this block
				cur = cur.getNext();
				while(!(cur instanceof LabelNode)) {
					cur = cur.getNext();
					m.instructions.remove(cur.getPrevious());
				}

				// And the `return` of if it was successful
				cur = cur.getNext();
				while(!(cur instanceof LabelNode)) {
					cur = cur.getNext();
					m.instructions.remove(cur.getPrevious());
				}

				// And now the villager zombification is always run if the target was a villager, no difficulty checks, done
			}
		}

		// GT_ASM.writePrettyPrintedOpCodesToFile(classNode, "zombie-post.ops");
		
		return GT_ASM.writeByteArraySelfReferenceFixup(classNode);
	}
}
