package gregapi.render;

import static gregapi.data.CS.*;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class IconContainerDefault implements IIconContainer {
	public final ResourceLocation mTextureFile;
	public final IIcon mIcon;
	public final short[] mRGBa;
	
	public IconContainerDefault(IIcon aIcon, short[] aRGBa, ResourceLocation aTextureFile) {
    	mIcon = aIcon; mRGBa = aRGBa; mTextureFile = aTextureFile;
	}
	
	public IconContainerDefault(IIcon aIcon, short[] aRGBa, boolean aIsBlockTexture) {
    	mIcon = aIcon; mRGBa = aRGBa; mTextureFile = (aIsBlockTexture ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);
	}
	
	public IconContainerDefault(IIcon aIcon, short[] aRGBa) {
    	mIcon = aIcon; mRGBa = aRGBa; mTextureFile = TextureMap.locationBlocksTexture;
	}
	
	public IconContainerDefault(IIcon aIcon, ResourceLocation aTextureFile) {
    	mIcon = aIcon; mRGBa = UNCOLOURED; mTextureFile = aTextureFile;
	}
	
	public IconContainerDefault(IIcon aIcon, boolean aIsBlockTexture) {
    	mIcon = aIcon; mRGBa = UNCOLOURED; mTextureFile = (aIsBlockTexture ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);
	}
	
	public IconContainerDefault(IIcon aIcon) {
    	mIcon = aIcon; mRGBa = UNCOLOURED; mTextureFile = TextureMap.locationBlocksTexture;
	}
	
	@Override
	public IIcon getIcon(int aRenderPass) {
		return mIcon;
	}
	
	@Override
	public boolean isUsingColorModulation(int aRenderPass) {
		return mRGBa == UNCOLOURED;
	}
	
	@Override
	public short[] getIconColor(int aRenderPass) {
		return mRGBa;
	}
	
	@Override
	public int getIconPasses() {
		return 1;
	}
	
	@Override
	public ResourceLocation getTextureFile() {
		return mTextureFile;
	}
	
	@Override
	public void registerIcons(IIconRegister aIconRegister) {
		//
	}
}