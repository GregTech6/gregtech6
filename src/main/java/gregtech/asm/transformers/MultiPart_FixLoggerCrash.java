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

import gregapi.log.LoggerFML;
import gregtech.asm.GT_ASM;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * @author Gregorius Techneticies
 */
public class MultiPart_FixLoggerCrash implements IClassTransformer  {
	public static Logger FAKE_LOGGER = new LoggerFML("FMP");
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!transformedName.equals("codechicken.multipart.handler.MultipartProxy_serverImpl")) return basicClass;
		ClassNode classNode = GT_ASM.makeNodes(basicClass);
		
		for (MethodNode m: classNode.methods) {
			if (m.name.equals("logger")) {
				GT_ASM.logger.info("Transforming codechicken.multipart.handler.MultipartProxy_serverImpl.logger");
				m.instructions.clear();
				m.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, "gregtech/asm/transformers/MultiPart_FixLoggerCrash", "FAKE_LOGGER", "Lorg/apache/logging/log4j/Logger;"));
				m.instructions.add(new InsnNode(Opcodes.ARETURN));
			}
		}
		
		return GT_ASM.writeByteArray(classNode);
	}
}
