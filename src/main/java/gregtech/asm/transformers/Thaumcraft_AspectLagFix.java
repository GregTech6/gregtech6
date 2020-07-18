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

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.Tuple;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import net.minecraft.launchwrapper.IClassTransformer;
import scala.Int;
import scala.Predef;
import scala.Tuple2;

import java.util.HashMap;

public class Thaumcraft_AspectLagFix implements IClassTransformer {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!name.equals("thaumcraft.common.lib.research.ScanManager")) return basicClass;

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);

		for (MethodNode m: classNode.methods) {
			if (m.name.equals("generateItemHash")) { // Should check `desc` too but no ambiguity here anyway
				LabelNode first = (LabelNode)m.instructions.get(0);
				m.instructions.insertBefore(first, new VarInsnNode(Opcodes.ALOAD, 0)); // Load item
				m.instructions.insertBefore(first, new VarInsnNode(Opcodes.ILOAD, 1)); // load meta
				m.instructions.insertBefore(first, new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/Thaumcraft_AspectLagFix", "getCachedItemHash", "(Lnet/minecraft/item/Item;I)I", false));
				m.instructions.insertBefore(first, new InsnNode(Opcodes.DUP)); // duplicate return
				m.instructions.insertBefore(first, new JumpInsnNode(Opcodes.IFEQ, first)); // If 0 jump to label0
				m.instructions.insertBefore(first, new InsnNode(Opcodes.IRETURN));
				m.instructions.insert(first, new InsnNode(Opcodes.POP)); // Pop the duplicate possible return value that's actually now 0

				for (AbstractInsnNode node = first.getNext(); node != null; node = node.getNext()) {
					if (node instanceof InsnNode) {
						InsnNode ret = (InsnNode)node;
						if (ret.getOpcode() == Opcodes.IRETURN) {
							m.instructions.insertBefore(ret, new VarInsnNode(Opcodes.ALOAD, 0)); // Load item, hash is already on the stack
							m.instructions.insertBefore(ret, new VarInsnNode(Opcodes.ILOAD, 1)); // load meta
							m.instructions.insertBefore(ret, new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/Thaumcraft_AspectLagFix", "setCachedItemHash", "(ILnet/minecraft/item/Item;I)I", false));
						}
					}
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}

	private static HashMap<Item, IntHashMap> cacheItemHash = new HashMap<>();

	public static int getCachedItemHash(Item item, int meta) {
		IntHashMap metaMap = cacheItemHash.get(item);
		if (metaMap != null) {
			Integer hash = (Integer)metaMap.lookup(meta);
			if (hash != null) return hash;
		}
		return 0;
	}

	public static int setCachedItemHash(int hash, Item item, int meta) {
		IntHashMap metaMap = cacheItemHash.get(item);
		if (metaMap == null) metaMap = cacheItemHash.put(item, new IntHashMap());
		metaMap.addKey(meta, hash);
		return hash;
	}
}
