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

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import gregtech.asm.GT_ASM;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * @author OvermindDL1
 */
public class CoFHCore_CrashFix implements IClassTransformer {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!name.equals("cofh.CoFHCore") && !name.equals("cofh.core.util.FMLEventHandler")) return basicClass;
		
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);
		
		outer: for (MethodNode m: classNode.methods) {
//          if (m.name.equals("serverStarting") || m.name.equals("handleIdMappingEvent")) {
			if (m.name.equals("handleIdMappingEvent")) {
				GT_ASM.logger.info("Transforming " + transformedName + "." + m.name);
				for (int i=0; i<m.instructions.size(); i++) {
					AbstractInsnNode insn = m.instructions.get(i);
					if (insn instanceof MethodInsnNode) {
						MethodInsnNode methcall = (MethodInsnNode)insn;
						if (methcall.owner.equals("cofh/core/util/oredict/OreDictionaryArbiter") && methcall.name.equals("initialize") && methcall.desc.equals("()V")) {
							m.instructions.remove(methcall);
							break outer;
						}
					}
				}
			}
		}
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
