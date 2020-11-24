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
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import net.minecraft.launchwrapper.IClassTransformer;

/**
 * @author Gregorius Techneticies
 */
public class CoFHLib_HashFix implements IClassTransformer {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!name.equals("cofh.lib.util.ComparableItem") && !name.equals("cofh.lib.util.ItemWrapper")) return basicClass;
		
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);
		
		// Screw that improperly coded Hashcode Function that violates Java Standards and crashes the Game.
		// I'm gonna return 0 for it no matter what. It was broken before anyways, so I don't hurt anything.
		// Also here have some comedy right from the Javadocs of the Class I am fixing with this ASM.
		
		// Description of the cofh.lib.util.ComparableItem Class:
		// "Wrapper for an Item/Metadata combination post 1.7. Quick and dirty, allows for Integer-based Hashes without collisions."
		
		// What actually is inside the hashcode Function:
		// "todo: this hash conflicts a lot"
		// return (metadata & 65535) | getId() << 16;
		
		// And yes the getId() part violates the Hash Rule of Java since Item IDs constantly change when you load Worlds or join Servers.
		
		for (MethodNode m: classNode.methods) if (m.name.equals("hashcode")) {
			// TODO Put an Identity Hash for the Item instance, but DO NOT combine it with the metadata due to Wildcard Issues!
			m.instructions.clear();
			m.instructions.insert(new InsnNode(Opcodes.ICONST_0));
			m.instructions.insert(new InsnNode(Opcodes.IRETURN));
			break;
		}
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
