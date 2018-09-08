package gregapi.render;

import static gregapi.data.CS.*;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/** 
 * @author Gregorius Techneticies
 */
public class IconContainerCopied implements IIconContainer {
	private final Block mBlock;
	private final byte mSide, mMeta;
	public short[] mRGBa;
	
	public IconContainerCopied(Block aBlock, long aMeta, long aSide, short[] aRGBa) {
		mBlock = aBlock; mMeta = (byte)aMeta; mSide = (byte)aSide; mRGBa = aRGBa;
	}
	public IconContainerCopied(Block aBlock, long aMeta, long aSide) {
		mBlock = aBlock; mMeta = (byte)aMeta; mSide = (byte)aSide; mRGBa = UNCOLOURED;
	}
	
	@Override
	public IIcon getIcon(int aRenderPass) {
		return mBlock.getIcon(mSide, mMeta);
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
		return TextureMap.locationBlocksTexture;
	}
	
	@Override
	public void registerIcons(IIconRegister aIconRegister) {
		//
	}
}