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
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import gregtech.asm.GT_ASM;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * @author Gregorius Techneticies
 * 
 * I am very aware what the original Speed Cap is for,
 * but the Rails themselves already have a Speed Cap,
 * so I will leave the cap to the Rails and not the Carts.
 * This does not work with Railcraft installed by the way.
 */
public class Minecraft_RemoveCartSpeedCap implements IClassTransformer  {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!transformedName.equals("net.minecraft.entity.item.EntityMinecart")) return basicClass;
		ClassNode classNode = GT_ASM.makeNodes(basicClass);
		
		for (MethodNode m: classNode.methods) {
			if (m.name.equals("getMaxCartSpeedOnRail")) {
				GT_ASM.logger.info("Transforming net.minecraft.entity.item.EntityMinecart.getMaxCartSpeedOnRail");
				m.instructions.clear();
				m.instructions.add(new LdcInsnNode(4.0F));
				m.instructions.add(new InsnNode(Opcodes.FRETURN));
			}
		}
		
		return GT_ASM.writeByteArray(classNode);
	}
}
