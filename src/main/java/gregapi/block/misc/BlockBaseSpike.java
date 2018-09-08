package gregapi.block.misc;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.BlockBaseSealable;
import gregapi.block.IBlockOnWalkOver;
import gregapi.block.IBlockToolable;
import gregapi.block.ToolCompat;
import gregapi.data.CS.SFX;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IRenderedBlock;
import gregapi.render.IRenderedBlockObject;
import gregapi.render.ITexture;
import gregapi.render.RendererBlockTextured;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class BlockBaseSpike extends BlockBaseSealable implements IBlockOnWalkOver, IBlockToolable, IRenderedBlock {
	public final OreDictMaterial mMat1, mMat2;
	
	public BlockBaseSpike(String aNameInternal, OreDictMaterial aMat1, OreDictMaterial aMat2) {
		super(null, aNameInternal, Material.iron, Block.soundTypeMetal);
		mMat1 = aMat1; mMat2 = aMat2;
		
		CR.shaped(ST.make(this, 1, 0), CR.DEF_NCC, "BTB", "TPT", "BTB", 'B', OP.toolHeadSword.dat(mMat1), 'P', OP.plate.dat(mMat1), 'T', OP.screw.dat(mMat1));
		CR.shaped(ST.make(this, 1, 6), CR.DEF_NCC, "TBT", "BPB", "TBT", 'B', OP.toolHeadSword.dat(mMat1), 'P', OP.plate.dat(mMat1), 'T', OP.screw.dat(mMat1));
		CR.shaped(ST.make(this, 1, 8), CR.DEF_NCC, "BTB", "TPT", "BTB", 'B', OP.toolHeadSword.dat(mMat2), 'P', OP.plate.dat(mMat2), 'T', OP.screw.dat(mMat2));
		CR.shaped(ST.make(this, 1,14), CR.DEF_NCC, "TBT", "BPB", "TBT", 'B', OP.toolHeadSword.dat(mMat2), 'P', OP.plate.dat(mMat2), 'T', OP.screw.dat(mMat2));
		
		CR.shapeless(ST.make(this, 1, 7), CR.DEF_NCC, new Object[] {ST.make(this, 1, 6)});
		CR.shapeless(ST.make(this, 1,15), CR.DEF_NCC, new Object[] {ST.make(this, 1,14)});
		
		CR.shapeless(ST.make(this, 1, 6), CR.DEF_NCC, new Object[] {ST.make(this, 1, 7)});
		CR.shapeless(ST.make(this, 1,14), CR.DEF_NCC, new Object[] {ST.make(this, 1,15)});
		
		OM.data(ST.make(this, 1, 0), aMat1, U*9);
		OM.data(ST.make(this, 1, 6), aMat1, U*9);
		OM.data(ST.make(this, 1, 7), aMat1, U*9);
		OM.data(ST.make(this, 1, 8), aMat2, U*9);
		OM.data(ST.make(this, 1,14), aMat2, U*9);
		OM.data(ST.make(this, 1,15), aMat2, U*9);
	}
	
	@Override public void onWalkOver(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {if ((aWorld.getBlockMetadata(aX, aY, aZ) & 7) != SIDE_UP) {aEntity.motionX *= 0.1; aEntity.motionZ *= 0.1;}}
	@Override public int onBlockPlaced(World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ, int aMeta) {return (aMeta & 7) < 6 ? (aMeta & 8) | OPPOSITES[aSide] : aMeta;}
	@Override public void onBlockAdded2(World aWorld, int aX, int aY, int aZ) {if (useGravity(aWorld.getBlockMetadata(aX, aY, aZ))) UT.Sounds.send(aWorld, SFX.MC_ANVIL_LAND, 1, 2, aX, aY, aZ);}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_pickaxe;}
	@Override public int getHarvestLevel(int aMeta) {return 2;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public int damageDropped(int aMeta) {return (aMeta & 7) < 6 ? aMeta & 8 : aMeta;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return 30;}
	@Override public float getExplosionResistance(int aMeta) {return 5;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return (aMeta & 7) < 6 && aMeta == aSide;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean useGravity(int aMeta) {return (aMeta & 7) == 7;}
	@Override public boolean doesWalkSpeed(short aMeta) {return T;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return F;}
	@Override public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return T;}
	@Override public void getSubBlocks(Item aItem, CreativeTabs aTab, List aList) {aList.add(ST.make(aItem, 1, 0)); aList.add(ST.make(aItem, 1, 6)); aList.add(ST.make(aItem, 1, 7)); aList.add(ST.make(aItem, 1, 8)); aList.add(ST.make(aItem, 1, 14)); aList.add(ST.make(aItem, 1, 15));}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition aTarget, World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer) {
		int aMeta = aWorld.getBlockMetadata(aX, aY, aZ);
		return ST.make(this, 1, (aMeta & 7) < 6 ? aMeta & 8 : aMeta);
	}
	
	@Override
	public boolean rotateBlock(World aWorld, int aX, int aY, int aZ, ForgeDirection aAxis) {
		int aMeta = aWorld.getBlockMetadata(aX, aY, aZ);
		return (aMeta & 7) < 6 && aWorld.setBlock(aX, aY, aZ, this, (aMeta & 8) | (((aMeta & 7) + 1) % 6), 3);
	}
	
	@Override
	public ForgeDirection[] getValidRotations(World aWorld, int aX, int aY, int aZ) {
		return (aWorld.getBlockMetadata(aX, aY, aZ) & 7) < 6 ? ForgeDirection.VALID_DIRECTIONS : null;
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_wrench) || aTool.equals(TOOL_rotator)) {
			if (aWorld.isRemote) return 0;
			int aMeta = aWorld.getBlockMetadata(aX, aY, aZ);
			if ((aMeta & 7) >= 6) return 0;
			byte tSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			return (aMeta & 7) != tSide && aWorld.setBlock(aX, aY, aZ, this, (aMeta & 8) | tSide, 3) ? 2000 : 0;
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {
		switch(aWorld.getBlockMetadata(aX, aY, aZ) & 7) {
		case SIDE_X_POS: return AxisAlignedBB.getBoundingBox(aX+0.4, aY    , aZ    , aX+1  , aY+1  , aZ+1  );
		case SIDE_Y_POS: return AxisAlignedBB.getBoundingBox(aX    , aY+0.4, aZ    , aX+1  , aY+1  , aZ+1  );
		case SIDE_Z_POS: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ+0.4, aX+1  , aY+1  , aZ+1  );
		case SIDE_X_NEG: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ    , aX+0.6, aY+1  , aZ+1  );
		case SIDE_Y_NEG: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ    , aX+1  , aY+0.6, aZ+1  );
		case SIDE_Z_NEG: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ    , aX+1  , aY+1  , aZ+0.6);
		default: return AxisAlignedBB.getBoundingBox(aX+0.125, aY+0.125, aZ+0.125, aX+0.875, aY+0.875, aZ+0.875);
		}
	}
	
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1, aZ+1);}
	@Override public int getRenderType() {return RendererBlockTextured.INSTANCE==null?23:RendererBlockTextured.INSTANCE.mRenderID;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return Blocks.iron_bars.getIcon(2, 0);}
	@Override public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack) {return null;}
	@Override public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ) {return null;}
	@Override public boolean usesRenderPass(int aRenderPass, ItemStack aStack) {return F;}
	@Override public boolean usesRenderPass(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return F;}
	@Override public boolean setBlockBounds(int aRenderPass, ItemStack aStack) {return F;}
	@Override public boolean setBlockBounds(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return F;}
	@Override public int getRenderPasses(ItemStack aStack) {return 0;}
	@Override public int getRenderPasses(IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return 0;}
	@Override public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {return new SpikeRenderer(ST.meta(aStack), mMat1, mMat2);}
	@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {return new SpikeRenderer(aWorld.getBlockMetadata(aX, aY, aZ), mMat1, mMat2);}
	
	public static class SpikeRenderer implements IRenderedBlockObject {
		public final byte mMeta;
		public ITexture mTexture;
		
		public SpikeRenderer(int aMeta, OreDictMaterial aMat1, OreDictMaterial aMat2) {mMeta = UT.Code.bind4(aMeta); mTexture = BlockTextureDefault.get(mMeta < 8 ? aMat1 : aMat2, OP.blockSolid, F);}
		
		@Override
		public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
			switch(mMeta & 7) {
			case SIDE_X_POS: switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 4], PX_P[ 4], PX_N[ 1], PX_P[ 5], PX_P[ 5]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 4], PX_P[11], PX_N[ 1], PX_P[ 5], PX_P[12]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 1], PX_P[11], PX_P[ 4], PX_N[ 1], PX_P[12], PX_P[ 5]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 1], PX_P[11], PX_P[11], PX_N[ 1], PX_P[12], PX_P[12]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 3], PX_P[ 3], PX_N[ 1], PX_P[ 6], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 3], PX_P[10], PX_N[ 1], PX_P[ 6], PX_P[13]); return T;
			case  7: aBlock.setBlockBounds(PX_P[ 5], PX_P[10], PX_P[ 3], PX_N[ 1], PX_P[13], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[ 5], PX_P[10], PX_P[10], PX_N[ 1], PX_P[13], PX_P[13]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 2], PX_N[ 1], PX_P[ 7], PX_P[ 7]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 9], PX_N[ 1], PX_P[ 7], PX_P[14]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 2], PX_N[ 1], PX_P[14], PX_P[ 7]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 9], PX_N[ 1], PX_P[14], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
			}
			case SIDE_Y_POS: switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 1], PX_P[ 4], PX_P[ 5], PX_N[ 1], PX_P[ 5]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 1], PX_P[11], PX_P[ 5], PX_N[ 1], PX_P[12]); return T;
			case  3: aBlock.setBlockBounds(PX_P[11], PX_P[ 1], PX_P[ 4], PX_P[12], PX_N[ 1], PX_P[ 5]); return T;
			case  4: aBlock.setBlockBounds(PX_P[11], PX_P[ 1], PX_P[11], PX_P[12], PX_N[ 1], PX_P[12]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 5], PX_P[ 3], PX_P[ 6], PX_N[ 1], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 5], PX_P[10], PX_P[ 6], PX_N[ 1], PX_P[13]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 5], PX_P[ 3], PX_P[13], PX_N[ 1], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[ 5], PX_P[10], PX_P[13], PX_N[ 1], PX_P[13]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 2], PX_P[ 7], PX_N[ 1], PX_P[ 7]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 9], PX_P[ 7], PX_N[ 1], PX_P[14]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 2], PX_P[14], PX_N[ 1], PX_P[ 7]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 9], PX_P[14], PX_N[ 1], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[14], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
			}
			case SIDE_Z_POS: switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 4], PX_P[ 1], PX_P[ 5], PX_P[ 5], PX_N[ 1]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 4], PX_P[11], PX_P[ 1], PX_P[ 5], PX_P[12], PX_N[ 1]); return T;
			case  3: aBlock.setBlockBounds(PX_P[11], PX_P[ 4], PX_P[ 1], PX_P[12], PX_P[ 5], PX_N[ 1]); return T;
			case  4: aBlock.setBlockBounds(PX_P[11], PX_P[11], PX_P[ 1], PX_P[12], PX_P[12], PX_N[ 1]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 3], PX_P[ 5], PX_P[ 6], PX_P[ 6], PX_N[ 1]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 3], PX_P[10], PX_P[ 5], PX_P[ 6], PX_P[13], PX_N[ 1]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 3], PX_P[ 5], PX_P[13], PX_P[ 6], PX_N[ 1]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[10], PX_P[ 5], PX_P[13], PX_P[13], PX_N[ 1]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 2], PX_P[ 9], PX_P[ 7], PX_P[ 7], PX_N[ 1]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 9], PX_P[ 7], PX_P[14], PX_N[ 1]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 9], PX_P[14], PX_P[ 7], PX_N[ 1]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 9], PX_P[14], PX_P[14], PX_N[ 1]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
			}
			case SIDE_X_NEG: switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 4], PX_P[ 4], PX_N[ 1], PX_P[ 5], PX_P[ 5]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 4], PX_P[11], PX_N[ 1], PX_P[ 5], PX_P[12]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 1], PX_P[11], PX_P[ 4], PX_N[ 1], PX_P[12], PX_P[ 5]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 1], PX_P[11], PX_P[11], PX_N[ 1], PX_P[12], PX_P[12]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 3], PX_P[ 3], PX_N[ 5], PX_P[ 6], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 3], PX_P[10], PX_N[ 5], PX_P[ 6], PX_P[13]); return T;
			case  7: aBlock.setBlockBounds(PX_P[ 1], PX_P[10], PX_P[ 3], PX_N[ 5], PX_P[13], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[ 1], PX_P[10], PX_P[10], PX_N[ 5], PX_P[13], PX_P[13]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 2], PX_P[ 2], PX_N[ 9], PX_P[ 7], PX_P[ 7]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 2], PX_P[ 9], PX_N[ 9], PX_P[ 7], PX_P[14]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 9], PX_P[ 2], PX_N[ 9], PX_P[14], PX_P[ 7]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 9], PX_P[ 9], PX_N[ 9], PX_P[14], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 0], PX_N[ 0]); return T;
			}
			case SIDE_Y_NEG: switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 1], PX_P[ 4], PX_P[ 5], PX_N[ 1], PX_P[ 5]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 1], PX_P[11], PX_P[ 5], PX_N[ 1], PX_P[12]); return T;
			case  3: aBlock.setBlockBounds(PX_P[11], PX_P[ 1], PX_P[ 4], PX_P[12], PX_N[ 1], PX_P[ 5]); return T;
			case  4: aBlock.setBlockBounds(PX_P[11], PX_P[ 1], PX_P[11], PX_P[12], PX_N[ 1], PX_P[12]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 1], PX_P[ 3], PX_P[ 6], PX_N[ 5], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 1], PX_P[10], PX_P[ 6], PX_N[ 5], PX_P[13]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 1], PX_P[ 3], PX_P[13], PX_N[ 5], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[ 1], PX_P[10], PX_P[13], PX_N[ 5], PX_P[13]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 1], PX_P[ 2], PX_P[ 7], PX_N[ 9], PX_P[ 7]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 1], PX_P[ 9], PX_P[ 7], PX_N[ 9], PX_P[14]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 1], PX_P[ 2], PX_P[14], PX_N[ 9], PX_P[ 7]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 1], PX_P[ 9], PX_P[14], PX_N[ 9], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]); return T;
			}
			case SIDE_Z_NEG: switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 4], PX_P[ 1], PX_P[ 5], PX_P[ 5], PX_N[ 1]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 4], PX_P[11], PX_P[ 1], PX_P[ 5], PX_P[12], PX_N[ 1]); return T;
			case  3: aBlock.setBlockBounds(PX_P[11], PX_P[ 4], PX_P[ 1], PX_P[12], PX_P[ 5], PX_N[ 1]); return T;
			case  4: aBlock.setBlockBounds(PX_P[11], PX_P[11], PX_P[ 1], PX_P[12], PX_P[12], PX_N[ 1]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 3], PX_P[ 1], PX_P[ 6], PX_P[ 6], PX_N[ 5]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 3], PX_P[10], PX_P[ 1], PX_P[ 6], PX_P[13], PX_N[ 5]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 3], PX_P[ 1], PX_P[13], PX_P[ 6], PX_N[ 5]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[10], PX_P[ 1], PX_P[13], PX_P[13], PX_N[ 5]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 2], PX_P[ 1], PX_P[ 7], PX_P[ 7], PX_N[ 9]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 1], PX_P[ 7], PX_P[14], PX_N[ 9]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 1], PX_P[14], PX_P[ 7], PX_N[ 9]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 1], PX_P[14], PX_P[14], PX_N[ 9]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[14]); return T;
			}
			default: switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 5], PX_P[ 5], PX_N[ 0], PX_P[ 6], PX_P[ 6]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 5], PX_P[10], PX_N[ 0], PX_P[ 6], PX_P[11]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 0], PX_P[10], PX_P[ 5], PX_N[ 0], PX_P[11], PX_P[ 6]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 0], PX_P[10], PX_P[10], PX_N[ 0], PX_P[11], PX_P[11]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_P[ 6], PX_N[ 0], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 0], PX_P[10], PX_P[ 6], PX_N[ 0], PX_P[11]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 0], PX_P[ 5], PX_P[11], PX_N[ 0], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[ 0], PX_P[10], PX_P[11], PX_N[ 0], PX_P[11]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 5], PX_P[ 0], PX_P[ 6], PX_P[ 6], PX_N[ 0]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 5], PX_P[10], PX_P[ 0], PX_P[ 6], PX_P[11], PX_N[ 0]); return T;
			case 11: aBlock.setBlockBounds(PX_P[10], PX_P[ 5], PX_P[ 0], PX_P[11], PX_P[ 6], PX_N[ 0]); return T;
			case 12: aBlock.setBlockBounds(PX_P[10], PX_P[10], PX_P[ 0], PX_P[11], PX_P[11], PX_N[ 0]); return T;
			default: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 4], PX_P[ 4], PX_N[ 4], PX_N[ 4], PX_N[ 4]); return T;
			}
			}
		}
		
		@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 13;}
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return (mMeta & 7) < 6 && (mMeta & 7) == aSide && (aRenderPass != 0 || !aShouldSideBeRendered[aSide]) ? null : mTexture;}
		@Override public boolean usesRenderPass(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
		@Override public boolean renderItem(Block aBlock, RenderBlocks aRenderer) {return F;}
		@Override public boolean renderBlock(Block aBlock, RenderBlocks aRenderer) {return F;}
		@Override public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {return this;}
		@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {return this;}
	}
}