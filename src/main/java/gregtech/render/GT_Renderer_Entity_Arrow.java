package gregtech.render;

import static gregapi.data.CS.*;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;

public class GT_Renderer_Entity_Arrow extends RenderArrow {
    private final ResourceLocation mTexture;
    
    public GT_Renderer_Entity_Arrow(Class aArrowClass, String aTextureName) {
    	mTexture = new ResourceLocation(RES_PATH_ENTITY+aTextureName+".png");
    	RenderingRegistry.registerEntityRenderingHandler(aArrowClass, this);
    }
    
    @Override
	protected ResourceLocation getEntityTexture(EntityArrow p_110775_1_) {
        return mTexture;
    }
}