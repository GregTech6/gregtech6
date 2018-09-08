package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.cover.ITileEntityCoverable;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class CoverTextureMulti extends AbstractCoverDefault {
	public final ITexture[] mTextures;
	public final boolean mHasCollide;
	public final String mSound;
	
	public CoverTextureMulti(boolean aHasCollide, String aSound, String aFolder, int aAmount) {mSound = aSound; mHasCollide = aHasCollide; mTextures = new ITexture[aAmount]; for (int i = 0; i < mTextures.length; i++) mTextures[i] = BlockTextureDefault.get(aFolder+i);}
	public CoverTextureMulti(boolean aHasCollide, String aSound, ITexture... aTextures) {mSound = aSound; mHasCollide = aHasCollide; mTextures = aTextures;}
	public CoverTextureMulti(String aFolder, String aSound, int aAmount) {this(T, aSound, aFolder, aAmount);}
	public CoverTextureMulti(String aSound, ITexture... aTextures) {this(T, aSound, aTextures);}
	public CoverTextureMulti(boolean aHasCollide, String aFolder, int aAmount) {this(aHasCollide, null, aFolder, aAmount);}
	public CoverTextureMulti(boolean aHasCollide, ITexture... aTextures) {this(aHasCollide, null, aTextures);}
	public CoverTextureMulti(String aFolder, int aAmount) {this(T, aFolder, aAmount);}
	public CoverTextureMulti(ITexture... aTextures) {this(T, aTextures);}
	
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_chisel) && mTextures.length > 1) {
			aData.visual(aSide, (short)((aData.mVisuals[aSide] + 1) % mTextures.length));
			return 100;
		}
		return 0;
	}
	
	@Override public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {if (aPlayer != null) UT.Sounds.send(aData.mTileEntity.getWorld(), mSound == null ? SFX.IC_WRENCH : mSound, 1.0F, -1.0F, aData.mTileEntity.getCoords());}
	@Override public void onAfterCrowbar(ITileEntityCoverable aTileEntity) {UT.Sounds.send(aTileEntity.getWorld(), mSound == null ? SFX.MC_BREAK : mSound, 1.0F, -1.0F, aTileEntity.getCoords());}
	@Override public void getCollisions(byte aCoverSide, CoverData aData, AxisAlignedBB aAABB, List aList, Entity aEntity) {if (mHasCollide) super.getCollisions(aCoverSide, aData, aAABB, aList, aEntity);}
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return mTextures[aData.mVisuals[aSide]%mTextures.length];}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	@Override public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {super.addToolTips(aList, aStack, aF3_H); if (mTextures.length > 1) aList.add(LH.get(LH.TOOL_TO_CHANGE_DESIGN_CHISEL));}
}