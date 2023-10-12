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

import gregtech.asm.GT_ASM;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

/**
 * @author Gregorius Techneticies
 */
public class CoFHLib_HashFix implements IClassTransformer {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!name.equals("cofh.lib.util.ComparableItem")) return basicClass;
		ClassNode classNode = GT_ASM.makeNodes(basicClass);

		// Screw that improperly coded Hashcode Function that violates Java Standards and crashes the Game.
		// Here have some comedy right from the Javadocs of the Class I am fixing with this ASM.

		// Description of the cofh.lib.util.ComparableItem Class:
		// "Wrapper for an Item/Metadata combination post 1.7. Quick and dirty, allows for Integer-based Hashes without collisions."

		// What actually is inside the hashcode Function:
		// "todo: this hash conflicts a lot"
		// return (metadata & 65535) | getId() << 16;

		// And yes the getId() part violates the Hash Rule of Java since Item IDs constantly change when you load Worlds or join Servers.

		for (MethodNode m: classNode.methods) if (m.name.equals("hashCode")) {
			GT_ASM.logger.info("Transforming " + transformedName + ".hashCode");
			// Just use the system identityHashCode, but DO NOT combine it with the metadata due to Wildcard Issues!
			m.instructions.clear();
			m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load `this`
			m.instructions.add(new VarInsnNode(Opcodes.GETFIELD, 20)); // replace 'this' on the stack with 'this.item' by popping off 'this' and looking up 'item' from it by class index
			m.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "identityHashCode", "(Ljava/lang/Object;)I", false)); // This use the identity hash of the item itself as it's a singleton, thus stable.
			m.instructions.add(new InsnNode(Opcodes.IRETURN));
			break;
		}

		return GT_ASM.writeByteArray(classNode);
	}
}
