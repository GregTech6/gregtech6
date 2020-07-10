package gregtech.asm.transformers;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Iterator;

public class CoFHCore_CrashFix implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if(!name.equals("cofh.CoFHCore") && !name.equals("cofh.core.util.FMLEventHandler")) return basicClass;

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, 0);

        for(MethodNode m: classNode.methods) {
            if(m.name.equals("serverStarting") || m.name.equals("handleIdMappingEvent")) {
                // TODO:  Look at the bytecode to see what to remove then remove it here
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
