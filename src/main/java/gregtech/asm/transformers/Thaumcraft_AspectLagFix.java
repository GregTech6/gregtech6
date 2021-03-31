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

import java.util.Arrays;
import java.util.HashMap;

import gregtech.asm.GT_ASM;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.util.IntHashMap;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;

/**
 * @author OvermindDL1
 */
public class Thaumcraft_AspectLagFix implements IClassTransformer {
	// TODO Probably do same thing to thaumcraft.common.lib.crafting.ThaumcraftCraftingManager.getObjectTags?
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (name.equals("thaumcraft.common.lib.research.ScanManager")) {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(basicClass);
			classReader.accept(classNode, 0);

			for (MethodNode m : classNode.methods) {
				if (m.name.equals("generateItemHash")) { // First most costly thing
					GT_ASM.logger.info("Transforming thaumcraft.common.lib.research.ScanManager.generateItemHash");
					LabelNode first = (LabelNode) m.instructions.get(0);
					m.instructions.insertBefore(first, new VarInsnNode(Opcodes.ALOAD, 0)); // Load item
					m.instructions.insertBefore(first, new VarInsnNode(Opcodes.ILOAD, 1)); // load meta
					m.instructions.insertBefore(first, new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/Thaumcraft_AspectLagFix", "getCachedItemHash", "(Lnet/minecraft/item/Item;I)I", false));
					m.instructions.insertBefore(first, new InsnNode(Opcodes.DUP)); // duplicate return
					m.instructions.insertBefore(first, new JumpInsnNode(Opcodes.IFEQ, first)); // If 0 jump to label0
					m.instructions.insertBefore(first, new InsnNode(Opcodes.IRETURN));
					m.instructions.insert(first, new InsnNode(Opcodes.POP)); // Pop the duplicate possible return value that's actually now 0

					for (AbstractInsnNode node = first.getNext(); node != null; node = node.getNext()) {
						if (node instanceof InsnNode) {
							InsnNode ret = (InsnNode) node;
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
		} else if (name.equals("thaumcraft.common.lib.crafting.ThaumcraftCraftingManager")) {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(basicClass);
			classReader.accept(classNode, 0);

			for (MethodNode m : classNode.methods) {
				if (m.name.equals("getObjectTags")) { // Second most costly thing
					GT_ASM.logger.info("Transforming thaumcraft.common.lib.crafting.ThaumcraftCraftingManager.getObjectTags");
					LabelNode first = (LabelNode) m.instructions.get(0);
					m.instructions.insertBefore(first, new VarInsnNode(Opcodes.ALOAD, 0)); // Load item
					m.instructions.insertBefore(first, new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/Thaumcraft_AspectLagFix", "getCachedAspectTags", "(Lnet/minecraft/item/ItemStack;)Lthaumcraft/api/aspects/AspectList;", false));
					m.instructions.insertBefore(first, new InsnNode(Opcodes.DUP)); // duplicate return
					m.instructions.insertBefore(first, new JumpInsnNode(Opcodes.IFNULL, first)); // If 0 jump to label0
					m.instructions.insertBefore(first, new InsnNode(Opcodes.ARETURN));
					m.instructions.insert(first, new InsnNode(Opcodes.POP)); // Pop the duplicate possible return value that's actually now 0

					for (AbstractInsnNode node = first.getNext(); node != null; node = node.getNext()) {
						if (node instanceof InsnNode) {
							InsnNode ret = (InsnNode) node;
							if (ret.getOpcode() == Opcodes.ARETURN) {
								m.instructions.insertBefore(ret, new VarInsnNode(Opcodes.ALOAD, 0)); // Load item, hash is already on the stack
								m.instructions.insertBefore(ret, new MethodInsnNode(Opcodes.INVOKESTATIC, "gregtech/asm/transformers/Thaumcraft_AspectLagFix", "setCachedAspectTags", "(Lthaumcraft/api/aspects/AspectList;Lnet/minecraft/item/ItemStack;)Lthaumcraft/api/aspects/AspectList;", false));
							}
						}
					}
				}
			}

			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(writer);
			return writer.toByteArray();
		} else {
			return basicClass;
		}
	}

	private static HashMap<Item, IntHashMap> cacheItemHash = new HashMap<>();

	public static int getCachedItemHash(Item item, int meta) {
		if (item == null) return -1;
		synchronized (cacheItemHash) {
			IntHashMap metaMap = cacheItemHash.get(item);
			if (metaMap != null) {
				Integer hash = (Integer)metaMap.lookup(meta);
				if (hash != null) return hash;
				hash = (Integer)metaMap.lookup(-1);
				if (hash != null) return hash;
				int[] grouped = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, meta));
				if (grouped != null) {
					hash = (Integer) metaMap.lookup(grouped[0]);
					if (hash != null) return hash;
				}
			}
		}
		return 0;
	}

	public static int setCachedItemHash(int hash, Item item, int meta) {
		synchronized (cacheItemHash) {
			IntHashMap metaMap = cacheItemHash.get(item);
			if (metaMap == null) cacheItemHash.put(item, metaMap = new IntHashMap());
			metaMap.addKey(meta, hash);
			return hash;
		}
	}

	private static HashMap<Item, IntHashMap> cacheAspectTags = new HashMap<>();

	public static AspectList getCachedAspectTags(ItemStack is) {
		if (is == null || is.getItem() == null) return null;
		synchronized (cacheAspectTags) {
			IntHashMap metaMap = cacheAspectTags.get(is.getItem());
			if (metaMap != null) {
				AspectList aspects = (AspectList)metaMap.lookup(is.getItemDamage());
				if (aspects != null) return aspects.copy(); // Ugh copy, why can't it just be immutable...
			}
		}
		return null;
	}

	public static AspectList setCachedAspectTags(AspectList aspects, ItemStack is) {
		synchronized (cacheAspectTags) {
			if (aspects == null || is == null || is.getItem() == null) return null;
			IntHashMap metaMap = cacheAspectTags.get(is.getItem());
			if (metaMap == null) cacheAspectTags.put(is.getItem(), metaMap = new IntHashMap());
			metaMap.addKey(is.getItemDamage(), aspects.copy());
			return aspects;
		}
	}
}
