package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.metatype.BlockMetaType;
import gregapi.cover.CoverData;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class CoverDrain extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof IFluidHandler);}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && !aData.mStopped && aData.mTileEntity instanceof IFluidHandler) {
			if (SIDES_TOP_HORIZONTAL[aSide] && SERVER_TIME % 100 == 10 && aData.mTileEntity.getWorld().isRaining()) {
				BiomeGenBase tBiome = aData.mTileEntity.getBiome();
				if (tBiome.rainfall > 0 && tBiome.temperature >= 0.2) {
					Block tInFront = aData.mTileEntity.getBlockAtSide(aSide);
					if (!(tInFront instanceof BlockLiquid) && !(tInFront instanceof IFluidBlock) && !tInFront.isSideSolid(aData.mTileEntity.getWorld(), aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetY(aSide), aData.mTileEntity.getOffsetZ(aSide), FORGE_DIR_OPPOSITES[aSide]) && !tInFront.isSideSolid(aData.mTileEntity.getWorld(), aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetY(aSide), aData.mTileEntity.getOffsetZ(aSide), FORGE_DIR[SIDE_TOP])) {
						boolean temp = F;
						if (tInFront instanceof BlockMetaType || tInFront instanceof BlockSlab || tInFront instanceof BlockStairs) {
							temp = aData.mTileEntity.getRainOffset(OFFSETS_X[aSide], OFFSETS_Y[aSide]+1, OFFSETS_Z[aSide]);
						} else {
							temp = aData.mTileEntity.getRainOffset(OFFSETS_X[aSide], OFFSETS_Y[aSide]  , OFFSETS_Z[aSide]) && (SIDES_TOP[aSide] || aData.mTileEntity.getBlockOffset(OFFSETS_X[aSide], -1, OFFSETS_Z[aSide]).isSideSolid(aData.mTileEntity.getWorld(), aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getY()-1, aData.mTileEntity.getOffsetZ(aSide), FORGE_DIR[SIDE_TOP]));
						}
						if (temp) UT.Fluids.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Water.make((long)Math.max(1, tBiome.rainfall*100) * (aData.mTileEntity.getWorld().isThundering()?2:1)), T);
					}
				}
			}
			if (aReceivedBlockUpdate || SERVER_TIME % 20 == 5) {
				Block tBlock = aData.mTileEntity.getBlockAtSide(aSide);
				FluidStack tFluid = NF;
				if (tBlock == Blocks.water || tBlock == Blocks.flowing_water) {
					if (aData.mTileEntity.getMetaDataAtSide(aSide) == 0) {
						if (WD.infiniteWater(aData.mTileEntity.getWorld(), aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetY(aSide), aData.mTileEntity.getOffsetZ(aSide))) {
							UT.Fluids.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Water.make(16000), T);
						} else {
							tFluid = FL.Water.make(1000);
						}
					}
				} else
				if (tBlock == Blocks.lava || tBlock == Blocks.flowing_lava) {
					if (aData.mTileEntity.getMetaDataAtSide(aSide) == 0) tFluid = FL.Lava.make(1000);
				} else 
				if (tBlock == BlocksGT.Ocean){
					UT.Fluids.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Ocean.make(16000), T);
				} else
				if (tBlock == BlocksGT.Swamp){
					UT.Fluids.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Dirty_Water.make(16000), T);
				} else
				if (tBlock instanceof IFluidBlock) tFluid = ((IFluidBlock)tBlock).drain(aData.mTileEntity.getWorld(), aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetY(aSide), aData.mTileEntity.getOffsetZ(aSide), F);
				
				if (tFluid != null && (SIDES_HORIZONTAL[aSide] || UT.Fluids.gas(tFluid) || (tFluid.getFluid().getDensity(tFluid)<0?SIDES_BOTTOM:SIDES_TOP)[aSide])) {
					if (UT.Fluids.fillAll((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], tFluid, T)) {
						if (tBlock instanceof IFluidBlock) {
							((IFluidBlock)tBlock).drain(aData.mTileEntity.getWorld(), aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetY(aSide), aData.mTileEntity.getOffsetZ(aSide), T);
						} else {
							aData.mTileEntity.getWorld().setBlockToAir(aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetY(aSide), aData.mTileEntity.getOffsetZ(aSide));
						}
					}
				}
			}
		}
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.CYAN + "Collects Fluid Blocks (if not against Gravity)");
		aList.add(LH.Chat.CYAN + "Collects Rainwater (not in Dry or Cold Areas)");
		aList.add(LH.Chat.CYAN + "Will work infinitely in Rivers and Oceans");
	}
	
	@Override public boolean isOpaque(byte aSide, CoverData aData) {return T;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTextureFront;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? sTextureFront : aSide == OPPOSITES[aTextureSide] ? sTextureBack : sTextureSides;}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureSides;}
	
	public static final ITexture
	sTextureSides = BlockTextureDefault.get("machines/covers/drain/sides"),
	sTextureFront = BlockTextureDefault.get("machines/covers/drain/front"),
	sTextureBack = BlockTextureDefault.get("machines/covers/drain/back");
}