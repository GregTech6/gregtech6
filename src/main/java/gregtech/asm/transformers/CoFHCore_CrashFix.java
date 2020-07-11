package gregtech.asm.transformers;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class CoFHCore_CrashFix implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if(!name.equals("cofh.CoFHCore") && !name.equals("cofh.core.util.FMLEventHandler")) return basicClass;

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, 0);

        outer: for(MethodNode m: classNode.methods) {
            if(m.name.equals("serverStarting") || m.name.equals("handleIdMappingEvent")) { // Should check `desc` too but no ambiguity here anyway
                for(int i=0; i<m.instructions.size(); i++) {
                    AbstractInsnNode insn = m.instructions.get(i);
                    if(insn instanceof MethodInsnNode) {
                        MethodInsnNode methcall = (MethodInsnNode)insn;
                        if(methcall.owner.equals("cofh/core/util/oredict/OreDictionaryArbiter") && methcall.name.equals("initialize") && methcall.desc.equals("()V")) {
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
