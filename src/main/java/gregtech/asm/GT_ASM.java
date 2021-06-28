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

package gregtech.asm;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

import cpw.mods.fml.relauncher.FMLRelaunchLog;
import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import gregtech.asm.transformers.CoFHCore_CrashFix;
import gregtech.asm.transformers.CoFHLib_HashFix;
import gregtech.asm.transformers.Minecraft_EmptyRecipeOptimization;
import gregtech.asm.transformers.Minecraft_IceHarvestMissingHookFix;
import gregtech.asm.transformers.Minecraft_LavaFlammableFix;
import gregtech.asm.transformers.Minecraft_MinecraftServerIntegratedLaunchMainMenuPartialFix;
import gregtech.asm.transformers.Railcraft_RemoveBoreSpam;
import gregtech.asm.transformers.Technomancy_ExtremelySlowLoadFix;
import gregtech.asm.transformers.Thaumcraft_AspectLagFix;
import net.minecraft.launchwrapper.LaunchClassLoader;

@Name("Greg-ASM®")
@MCVersion("1.7.10")
@SortingIndex(1000) // Sorting index with other coremods, for example DragonAPI is 1001
@TransformerExclusions({"gregtech.asm"}) // Array of strings of package or class names to ignore for this coremod
public class GT_ASM implements IFMLLoadingPlugin {
	public static File location; // Useful to get the path to the coremod to grab other files if needed
	public static ClassLoader classLoader;
	public static final Logger logger = LogManager.getLogger(GT_ASM.class.getSimpleName());
	
	public GT_ASM() {}
	
	@Override @SuppressWarnings("resource")
	public void injectData(Map<String, Object> data) {
		location = (File)data.get("coremodLocation"); // Location of the gt6 jar
		ASMConfig config = new ASMConfig((File)data.get("mcLocation"));
		// If it's not LaunchClassLoader then a lot of other things will already be dying too
		final LaunchClassLoader tClassLoader = (LaunchClassLoader)Thread.currentThread().getContextClassLoader();
		
		for (Map.Entry<String, Boolean> entry : config.transformers.entrySet()) {
			if (entry.getValue()) {
				String transformer = entry.getKey();
				FMLRelaunchLog.finer("Registering transformer %s", transformer);
				tClassLoader.registerTransformer(transformer);
			}
		}
	}
	
	@Override public String[] getASMTransformerClass() {return null;}
	@Override public String getModContainerClass() {return GT_ASM_Dummy.class.getName();}
	@Override public String getSetupClass() {return GT_ASM.Setup.class.getName();}
	@Override public String getAccessTransformerClass() {return null;}

	public static class Setup implements IFMLCallHook {
		@Override
		public void injectData(Map<String, Object> data) {
			GT_ASM.classLoader = (ClassLoader)data.get("classLoader");
		}

		@Override
		public Void call() throws Exception {
			return null;
		}
	}

	private static class ASMConfig {
		private boolean dirty;
		public LinkedHashMap<String, Boolean> transformers = new LinkedHashMap<>(); // To keep insertion order

		public ASMConfig(File mclocation) {
			dirty = false;
			// Shouldn't happen, but sanity, and Java can't enforce this unlike decent programming languages...
			if (mclocation == null) throw new RuntimeException("Failed to acquire `location` in GT6 CoreMod");
			
			transformers.put(CoFHLib_HashFix.class.getName(), true);
			transformers.put(CoFHCore_CrashFix.class.getName(), true);
			transformers.put(Railcraft_RemoveBoreSpam.class.getName(), true);
			transformers.put(Minecraft_EmptyRecipeOptimization.class.getName(), true);
			transformers.put(Minecraft_IceHarvestMissingHookFix.class.getName(), true);
			transformers.put(Minecraft_LavaFlammableFix.class.getName(), true);
			transformers.put(Minecraft_MinecraftServerIntegratedLaunchMainMenuPartialFix.class.getName(), true);
			transformers.put(Technomancy_ExtremelySlowLoadFix.class.getName(), true);
			transformers.put(Thaumcraft_AspectLagFix.class.getName(), true);

			mclocation = new File(mclocation, "/config/gregtech");
			mclocation.mkdirs();
			mclocation = new File(mclocation, "/asm.ini");
			if (!mclocation.exists()) {
				try {
					PrintWriter out = new PrintWriter(mclocation);
					outputConfig(out);
					out.close();
				} catch (FileNotFoundException e) {
					throw new RuntimeException("Unable to write GT6 ASM config file at: " + mclocation, e);
				} catch (IOException e) {
					throw new RuntimeException("Unable to write to GT6 ASM config file at: " + mclocation, e);
				}
			} else {
				try {
					BufferedReader in = new BufferedReader(new FileReader(mclocation));
					loadConfig(in);
					in.close();
					if (dirty) {
						PrintWriter out = new PrintWriter(mclocation);
						outputConfig(out);
						out.close();
					}
				} catch (IOException e) {
					throw new RuntimeException("Error reading GT6 ASM config file at: " + mclocation, e);
				}
			}
		}

		private void outputConfig(Writer out) throws IOException {
			out.write("# ASM Transformers, `true` to enable, `false` to disable\n");
			for (Map.Entry<String, Boolean> entry : transformers.entrySet()) {
				out.write("transformer:" + entry.getKey() + " = " + entry.getValue() + "\n");
			}
		}

		private void loadConfig(BufferedReader in) throws IOException {
			int lineno = 0;
			int transformersInserted = 0;
			String line;
			while ( (line = in.readLine()) != null) {
				line = line.trim();
				lineno += 1;
				if (line.startsWith("transformer:")) {
					String kw = line.substring(12);
					String[] kwa = kw.split("=");
					if (kwa.length != 2) throw new RuntimeException("Invalid Configuration entry in GT6 ASM configuration file, line " + lineno + ": " + line);
					String classname = kwa[0].trim();
					boolean enabled = kwa[1].trim() != "false";
					if (transformers.containsKey(classname)) {
						transformers.put(classname, enabled);
						transformersInserted += 1;
					} else {
						FMLRelaunchLog.warning("Invalid configuration entry classname of %s at line %s", classname, Integer.toString(lineno));
						dirty = true;
					}
				} else if (line.startsWith("#")) {
					// skip
				} else if (line.trim().equals("")) {
					// skip
				} else {
					dirty = true;
				}
			}
			if (transformersInserted != transformers.size()) dirty = true;
		}
	}

	static class PrinterClassVisitor extends ClassVisitor {
		public StringWriter out_writer = new StringWriter();
		public PrinterClassVisitor() {
			super(Opcodes.ASM5);
		}

		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
			//MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
			out_writer.write("Method: " + name + desc + "\n");
			Printer p = new Textifier(Opcodes.ASM5) {
				@Override
				public void visitMethodEnd() {
					print(new PrintWriter(out_writer)); // print it after it has been visited
				}
			};
			//return new TraceMethodVisitor(mv, p);
			return new TraceMethodVisitor(p);
		}
	}

	public static String getPrettyPrintedOpCodes(ClassNode classNode) {
		PrinterClassVisitor printer = new PrinterClassVisitor();
		classNode.accept(printer);
		return printer.out_writer.toString();
	}

	@SuppressWarnings("resource")
	public static void writePrettyPrintedOpCodesToFile(ClassNode classNode, String fileName) {
		try {
			(new BufferedWriter(new FileWriter(fileName, true))).append(getPrettyPrintedOpCodes(classNode)).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] writeByteArray(ClassNode aClassNode) {
		ClassWriter rWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		aClassNode.accept(rWriter);
		return rWriter.toByteArray();
	}
	
	public static ClassNode makeNodes(byte[] aBasicClass) {
		ClassNode rClassNode = new ClassNode();
		ClassReader classReader = new ClassReader(aBasicClass);
		classReader.accept(rClassNode, 0);
		return rClassNode;
	}
}
