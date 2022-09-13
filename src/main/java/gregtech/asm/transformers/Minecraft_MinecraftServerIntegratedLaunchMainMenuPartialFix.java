/**
 * Copyright (c) 2022 GregTech-6 Team
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
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

/**
 * @author OvermindDL1
 */
public class Minecraft_MinecraftServerIntegratedLaunchMainMenuPartialFix implements IClassTransformer {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		
		// TODO This Transformer is currently commented out in GT_ASM!
		
		if (!transformedName.equals("net.minecraft.server.MinecraftServer")) return basicClass;
		ClassNode classNode = GT_ASM.makeNodes(basicClass);
		
		for (MethodNode m: classNode.methods) {
			if (m.name.equals("run")) { // Not obfuscated, nicely enough
				GT_ASM.logger.info("Transforming net.minecraft.server.MinecraftServer.run");
				
				AbstractInsnNode node = m.instructions.getFirst();
				// Line 391 is obfuscated, line 449 is not/in-dev, there's no overlap in their ranges so this is fine
				while (node != null && (!(node instanceof LineNumberNode) || (((LineNumberNode) node).line != 391 && ((LineNumberNode) node).line != 449))) {
					node = node.getNext();
				}

				// Something really screwed up the MinecraftServer class file, bail!
				if (node == null) {
					GT_ASM.logger.info("net.minecraft.server.MinecraftServer.run appears to be corrupt!  Ignoring and not touching!");
					return basicClass;
				}
				
				AbstractInsnNode constNode = node.getNext(); // Skip LineNumberNode
				if (!(constNode instanceof InsnNode) || ((InsnNode)constNode).getOpcode() != Opcodes.LCONST_0) {
					GT_ASM.logger.warn("Node is apparently already altered?");
					return basicClass;
				}
				
				m.instructions.remove(constNode);
				// I'd almost prefer 2001 so we could get a message of if its actually working or not...
				m.instructions.insert(node, new LdcInsnNode(new Long(2001)));
				
				// TODO: Figure out if it might be better to just zero out the initial `getSystemTimeMillis` call, the ASM is:
				/*
				   L30
					LINENUMBER 390 L30
					INVOKESTATIC net/minecraft/server/MinecraftServer.ar ()J // Maybe its better to replace this with LCONST_0 so clocks can't fluctuate to screw it up?
					LSTORE 1
				   L31
					LINENUMBER 391 L31
					LCONST_0 // We are currently replacing this with the above new node.
					LSTORE 3
				 */
			}
		}
		
		// TODO FIX THIS, see https://github.com/GregTech6/gregtech6/issues/95#issuecomment-1245004015
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES) {
			// Have to override this method because of Forge's classloader stuff, this one grabs the wrong one..
			// And can't even use the correct classloader here because the forge remapping hadn't been done yet.
			// Forge's classloader doesn't seem to like loading and transforming a type while transforming another...
			// Which wouldn't be an issue if ItemStack loaded first, but meh...
			@Override
			protected String getCommonSuperClass(String type1, String type2) {
				Class<?> c, d;
				ClassLoader classLoader = GT_ASM.classLoader;
				try {
					c = Class.forName(type1.replace('/', '.'), false, classLoader);
					d = Class.forName(type2.replace('/', '.'), false, classLoader);
				} catch (Exception e) {
					// We can't really unify this at this point because it's not loaded yet,
					// but for this class its fine to return `"java/lang/Object"` for anything that is not loadable.
					//throw new RuntimeException(e.toString());
					return "java/lang/Object";
				}
				if (c.isAssignableFrom(d)) {
					return type1;
				}
				if (d.isAssignableFrom(c)) {
					return type2;
				}
				if (c.isInterface() || d.isInterface()) {
					return "java/lang/Object";
				}
				do {
					c = c.getSuperclass();
				} while (!c.isAssignableFrom(d));
				return c.getName().replace('.', '/');
			}
		};
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
