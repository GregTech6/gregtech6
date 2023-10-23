/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregapi.block.multitileentity.example;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.gui.ContainerClientChest;
import gregapi.gui.ContainerCommonChest;
import gregapi.item.IItemColorableRGB;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi.tileentity.ITileEntityDecolorable;
import gregapi.tileentity.base.TileEntityBase05Inventories;
import gregapi.tileentity.data.ITileEntitySurface;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gregapi.data.CS.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_RESCALE_NORMAL;

/**
 * @author Gregorius Techneticies
 * 
 * An example implementation of a Chest with my MultiTileEntity System.
 */
public class MultiTileEntityChest extends TileEntityBase05Inventories implements IMTE_IsProvidingWeakPower, IMTE_IsProvidingStrongPower, IItemColorableRGB, ITileEntityDecolorable, ITileEntitySurface, IMTE_OnRegistrationClient, IMTE_OnRegistrationFirstClient, IMTE_SyncDataByte, IMTE_AddToolTips, IMTE_SetBlockBoundsBasedOnState, IMTE_GetSubItems, IMTE_SyncDataByteArray, IMTE_GetExplosionResistance, IMTE_GetBlockHardness, IMTE_GetComparatorInputOverride, IMTE_GetSelectedBoundingBoxFromPool, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnPlaced, IMTE_OnToolClick {
	protected boolean mIsPainted = F, mIsTrapped = F;
	protected int mRGBa = UNCOLORED;
	protected byte mFacing = 3, mUsingPlayers = 0, oUsingPlayers = 0;
	protected float mLidAngle = 0, oLidAngle = 0, mHardness = 6, mResistance = 3;
	protected OreDictMaterial mMaterial = MT.NULL;
	
	/** Gets supplied via Default NBT. */
	public String mTextureName = "", mDungeonLootName = "";
	
	public MultiTileEntityChest() {/**/}
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_COLOR)) mRGBa = aNBT.getInteger(NBT_COLOR);
		if (aNBT.hasKey(NBT_FACING)) mFacing = aNBT.getByte(NBT_FACING);
		if (aNBT.hasKey(NBT_PAINTED)) mIsPainted = aNBT.getBoolean(NBT_PAINTED);
		if (aNBT.hasKey(NBT_TRAPPED)) mIsTrapped = aNBT.getBoolean(NBT_TRAPPED);
		if (aNBT.hasKey(NBT_TEXTURE)) mTextureName = aNBT.getString(NBT_TEXTURE);
		if (aNBT.hasKey("gt.dungeonloot")) mDungeonLootName = aNBT.getString("gt.dungeonloot");
		if (aNBT.hasKey(NBT_HARDNESS)) mHardness = aNBT.getFloat(NBT_HARDNESS);
		if (aNBT.hasKey(NBT_RESISTANCE)) mResistance = aNBT.getFloat(NBT_RESISTANCE);
		if (aNBT.hasKey(NBT_MATERIAL)) mMaterial = OreDictMaterial.get(aNBT.getString(NBT_MATERIAL));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_FACING, mFacing);
		UT.NBT.setBoolean(aNBT, NBT_TRAPPED, mIsTrapped);
		if (UT.Code.stringValid(mDungeonLootName)) aNBT.setString("gt.dungeonloot", mDungeonLootName);
	}
	
	@Override
	public NBTTagCompound writeItemNBT(NBTTagCompound aNBT) {
		aNBT = super.writeItemNBT(aNBT);
		if (UT.Code.stringValid(mDungeonLootName)) aNBT.setString("gt.dungeonloot", mDungeonLootName);
		return aNBT;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(aSendAll, mFacing, mUsingPlayers, (byte)getSizeInventory(), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa));
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		mFacing = UT.Code.getSideForPlayerPlacing(aPlayer, mFacing, SIDES_HORIZONTAL);
		return T;
	}
	
	@Override
	public void onTick(long aTimer, boolean aIsServerSide) {
		super.onTick(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (mInventoryChanged) {
				for (byte tSide : ALL_SIDES_VALID) {
					DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
					if (tDelegator.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) {
						((ITileEntityAdjacentInventoryUpdatable)tDelegator.mTileEntity).adjacentInventoryUpdated(tDelegator.mSideOfTileEntity, this);
					}
				}
			}
			if (mUsingPlayers > 0 && aTimer % 1200 == 0) {
				mUsingPlayers = UT.Code.bind7(getOpenGUIs());
			}
		} else {
			oLidAngle = mLidAngle;
			if (mUsingPlayers > 0) {
				mLidAngle = Math.min(1, mLidAngle+0.1F);
				if (mLidAngle > 0.1F && oLidAngle <= 0.1F) UT.Sounds.play("random.chestopen"  , 10, 0.5F, RNGSUS.nextFloat() * 0.1F + 0.9F, getCoords());
			} else {
				mLidAngle = Math.max(0, mLidAngle-0.1F);
				if (mLidAngle < 0.5F && oLidAngle >= 0.5F) UT.Sounds.play("random.chestclosed", 10, 0.5F, RNGSUS.nextFloat() * 0.1F + 0.9F, getCoords());
			}
		}
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mUsingPlayers != oUsingPlayers || super.onTickCheck(aTimer);
	}
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oUsingPlayers = mUsingPlayers;
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return 0;
		if (aTool.equals(TOOL_wrench)) {
			byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			if (aTargetSide > 1) {
				mFacing = aTargetSide;
				updateClientData();
				causeBlockUpdate();
				return 10000;
			}
		}
		if (aTool.equals(TOOL_pincers) && aPlayerInventory != null) {
			long rCount = 0;
			for (int i = 0; i < invsize(); i++) if (slotHas(i)) {
				// Check for Achievements so those won't get skipped.
				if (aPlayer instanceof EntityPlayer) UT.Inventories.checkAchievements((EntityPlayer)aPlayer, slot(i));
				// Merge Stacks first when applicable.
				for (int j = 0; j < 36; j++) {
					if (ST.equal(slot(i), aPlayerInventory.getStackInSlot(j))) {
						rCount += ST.move(this, aPlayerInventory, i, j);
						if (!slotHas(i)) break;
					}
				}
			}
			// Stackable NBT-less Items second.
			for (int i = 0; i < invsize(); i++) if (slotHas(i) && ST.maxsize(slot(i)) > 1 && ST.nbt(slot(i)) == null) {
				for (int j = 9; j < 36; j++) {
					rCount += ST.move(this, aPlayerInventory, i, j);
					if (!slotHas(i)) break;
				}
			}
			// Stackable NBT-containing Items third.
			if (rCount <= 0) for (int i = 0; i < invsize(); i++) if (slotHas(i) && ST.maxsize(slot(i)) > 1) {
				for (int j = 9; j < 36; j++) {
					rCount += ST.move(this, aPlayerInventory, i, j);
					if (!slotHas(i)) break;
				}
			}
			// Unstackable NBT-containing Items fourth.
			if (rCount <= 0) for (int i = 0; i < invsize(); i++) if (slotHas(i) && ST.nbt(slot(i)) != null) {
				for (int j = 9; j < 36; j++) {
					rCount += ST.move(this, aPlayerInventory, i, j);
					if (!slotHas(i)) break;
				}
			}
			// Unstackable NBT-less Items fifth.
			if (rCount <= 0) for (int i = 0; i < invsize(); i++) if (slotHas(i)) {
				for (int j = 9; j < 36; j++) {
					rCount += ST.move(this, aPlayerInventory, i, j);
					if (!slotHas(i)) break;
				}
			}
			// Nothing was done.
			if (rCount <= 0) return 1;
			// Make Sound and update Player Inventory if Items got transferred.
			UT.Sounds.send(SFX.MC_COLLECT, this);
			ST.update(aPlayer);
			return rCount;
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && !worldObj.isSideSolid(xCoord, yCoord + 1, zCoord, FORGE_DIR[SIDE_BOTTOM]) && isUseableByPlayerGUI(aPlayer)) {
			generateDungeonLoot();
			openGUI(aPlayer);
		}
		return T;
	}
	
	// No longer generate Loot when harvested, instead pick up the Chest including the Loot it contains!
	//@Override
	//public boolean breakBlock() {
	//  // Only auto-generate Loot if a second has passed since its original placement. Prevents Item spillage during Worldgen in most cases.
	//  if (mTimer > 20) generateDungeonLoot();
	//  return super.breakBlock();
	//}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public String getTileEntityName() {return "gt.multitileentity.chest";}
	@Override public void openInventoryGUI () {mUsingPlayers++; if (mIsTrapped) causeBlockUpdate();}
	@Override public void closeInventoryGUI() {mUsingPlayers--; if (mIsTrapped) causeBlockUpdate();}
	@Override public float getExplosionResistance2() {return mResistance;}
	@Override public float getBlockHardness() {return mHardness;}
	@Override public int getComparatorInputOverride(byte aSide) {return Container.calcRedstoneFromInventory(this);}
	@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return null;}
	@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 0;}
	@Override public boolean renderBlock(Block aBlock, RenderBlocks aRenderer, IBlockAccess aWorld, int aX, int aY, int aZ) {return T;}
	
	protected void generateDungeonLoot() {
		if (isServerSide() && UT.Code.stringValid(mDungeonLootName) && ST.generateLoot(RNGSUS, mDungeonLootName, this)) {
			worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, xCoord+0.4, yCoord+1.25, zCoord+0.4, 5+RNGSUS.nextInt(5)+RNGSUS.nextInt(5)));
			worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, xCoord+0.4, yCoord+1.25, zCoord+0.6, 5+RNGSUS.nextInt(5)+RNGSUS.nextInt(5)));
			worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, xCoord+0.5, yCoord+1.35, zCoord+0.5, 5+RNGSUS.nextInt(5)+RNGSUS.nextInt(5)));
			worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, xCoord+0.6, yCoord+1.25, zCoord+0.4, 5+RNGSUS.nextInt(5)+RNGSUS.nextInt(5)));
			worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, xCoord+0.6, yCoord+1.25, zCoord+0.6, 5+RNGSUS.nextInt(5)+RNGSUS.nextInt(5)));
			mDungeonLootName = "";
		}
	}
	
	@Override
	public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem, CreativeTabs aTab, List<ItemStack> aList, short aID) {
		if (!SHOW_HIDDEN_MATERIALS && mMaterial.mHidden) return F;
		if (D1 || "lootchest".equalsIgnoreCase(mTextureName)) for (String tLoot : ST.LOOT_TABLES) aList.add(aBlock.mMultiTileEntityRegistry.getItem(aID, UT.NBT.makeString("gt.dungeonloot", tLoot)));
		return T;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (UT.Code.stringValid(mDungeonLootName)) aList.add(LH.Chat.BLINKING_CYAN + "Contains Loot of " + LH.Chat.WHITE + LH.get("loot." + mDungeonLootName));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TAKE_PINCERS));
	}
	
	@Override public boolean receiveDataByte(byte aData, INetworkHandler aNetworkHandler) {mUsingPlayers = aData; return T;}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mFacing = (byte)(aData[0] & 7);
		mUsingPlayers = aData[1];
		if (UT.Code.unsignB(aData[2]) != getSizeInventory()) setInventory(new ItemStack[UT.Code.unsignB(aData[2])]);
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4]), UT.Code.unsignB(aData[5])});
		return T;
	}
	
	@Override public boolean unpaint() {if (mIsPainted) {mIsPainted=F; mRGBa=UT.Code.getRGBInt(mMaterial.fRGBaSolid); updateClientData(); return T;} return F;}
	@Override public boolean isPainted() {return mIsPainted || (worldObj != null && isClientSide() && UT.Code.getRGBInt(mMaterial.fRGBaSolid) != mRGBa);}
	@Override public boolean paint(int aRGB) {if (aRGB!=mRGBa) {mRGBa=aRGB; mIsPainted=T; return T;} return F;}
	@Override public int getPaint() {return mRGBa;}
	@Override public boolean canRecolorItem(ItemStack aStack) {return T;}
	@Override public boolean canDecolorItem(ItemStack aStack) {return mIsPainted;}
	@Override public boolean recolorItem(ItemStack aStack, int aRGB) {if (paint((isPainted() ? UT.Code.mixRGBInt(aRGB, getPaint()) : aRGB) & ALL_NON_ALPHA_COLOR)) {UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make())); return T;} return F;}
	@Override public boolean decolorItem(ItemStack aStack) {if (unpaint()) {UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make())); return T;} return F;}
	
	@Override public int isProvidingWeakPower  (byte aOppositeSide) {return mIsTrapped && mUsingPlayers > 0 ? 15 : 0;}
	@Override public int isProvidingStrongPower(byte aOppositeSide) {return mIsTrapped && mUsingPlayers > 0 ? 15 : 0;}
	
	private static final float minX = 0.0625F, minY = 0F, minZ = 0.0625F, maxX = 0.9375F, maxY = 0.875F, maxZ = 0.9375F;
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(minX, minY, minZ, maxX, maxY, maxZ);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(minX, minY, minZ, maxX, maxY, maxZ);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, minX, minY, minZ, maxX, maxY, maxZ);}
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {box(aBlock, minX, minY, minZ, maxX, maxY, maxZ); return true;}
	@Override public float getSurfaceSize           (byte aSide) {return 0.875F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 0.875F;}
	@Override public float getSurfaceDistance       (byte aSide) {return aSide > 1 ? 0.0625F : aSide == 1 ? 0.125F : 0;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque        (byte aSide) {return F;}
	
	@Override public Object getGUIClient(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientChest(aPlayer.inventory, this, aGUIID);}
	@Override public Object getGUIServer(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonChest(aPlayer.inventory, this, aGUIID);}
	
	@Override
	public boolean renderItem(Block aBlock, RenderBlocks aRenderer) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(this, 0, 0, 0, 0);
		return T;
	}
	
	@SideOnly(Side.CLIENT)
	private static MultiTileEntityRendererChest RENDERER;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onRegistrationFirstClient(MultiTileEntityRegistry aRegistry, short aID) {
		ClientRegistry.bindTileEntitySpecialRenderer(getClass(), RENDERER = new MultiTileEntityRendererChest());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onRegistrationClient(MultiTileEntityRegistry aRegistry, short aID) {
		RENDERER.mResources.put(mTextureName, new ResourceLocation[] {new ResourceLocation(MD.GT.mID, TEX_DIR_MODEL + aRegistry.mNameInternal + "/" + mTextureName + ".colored.png"), new ResourceLocation(MD.GT.mID, TEX_DIR_MODEL + aRegistry.mNameInternal + "/" + mTextureName + ".plain.png")});
	}
	
	@SideOnly(Side.CLIENT)
	public static class MultiTileEntityRendererChest extends TileEntitySpecialRenderer {
		private static final MultiTileEntityModelChest sModel = new MultiTileEntityModelChest();
		public final Map<String, ResourceLocation[]> mResources = new HashMap<>();
		
		@Override
		public void renderTileEntityAt(TileEntity aTileEntity, double aX, double aY, double aZ, float aPartialTick) {
			if (aTileEntity instanceof MultiTileEntityChest) {
				double tLidAngle = 1 - (((MultiTileEntityChest)aTileEntity).oLidAngle + (((MultiTileEntityChest)aTileEntity).mLidAngle - ((MultiTileEntityChest)aTileEntity).oLidAngle) * aPartialTick); tLidAngle = -(((1 - tLidAngle*tLidAngle*tLidAngle) * Math.PI) / 2);
				ResourceLocation[] tLocation = mResources.get(((MultiTileEntityChest)aTileEntity).mTextureName);
				bindTexture(tLocation[0]);
				glPushMatrix();
				glEnable(GL_BLEND);
				glEnable(GL_LIGHTING);
				glEnable(GL_ALPHA_TEST);
				glEnable(GL_RESCALE_NORMAL);
				glAlphaFunc(GL_GREATER, 0.1F);
				OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
				short[] tRGBa = UT.Code.getRGBaArray(((MultiTileEntityChest)aTileEntity).mRGBa);
				glColor4f(tRGBa[0] / 255.0F, tRGBa[1] / 255.0F, tRGBa[2] / 255.0F, 1);
				glTranslated(aX, aY + 1, aZ + 1);
				glScalef(1, -1, -1);
				glTranslated(0.5, 0.5, 0.5);
				glRotatef(COMPASS_FROM_SIDE[((MultiTileEntityChest)aTileEntity).mFacing] * 90 - 180, 0, 1, 0);
				glTranslated(-0.5, -0.5, -0.5);
				sModel.render(tLidAngle);
				glDisable(GL_RESCALE_NORMAL);
				glPopMatrix();
				glEnable(GL_RESCALE_NORMAL);
				glColor4f(1, 1, 1, 1);
				
				bindTexture(tLocation[1]);
				glPushMatrix();
				glTranslated(aX, aY + 1, aZ + 1);
				glScalef(1, -1, -1);
				glTranslated(0.5, 0.5, 0.5);
				glRotatef(COMPASS_FROM_SIDE[((MultiTileEntityChest)aTileEntity).mFacing] * 90 - 180, 0, 1, 0);
				glTranslated(-0.5, -0.5, -0.5);
				sModel.render(tLidAngle);
				glDisable(GL_RESCALE_NORMAL);
				glPopMatrix();
				glEnable(GL_RESCALE_NORMAL);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static class MultiTileEntityModelChest extends ModelBase {
		private final ModelRenderer mLid, mBottom, mKnob;
		
		public MultiTileEntityModelChest() {
			mLid = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
			mLid.addBox(0, -5, -14, 14, 5, 14, 0);
			mLid.rotationPointX =  1;
			mLid.rotationPointY =  7;
			mLid.rotationPointZ = 15;
			mKnob = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
			mKnob.addBox(-1, -2, -15, 2, 4, 1, 0);
			mKnob.rotationPointX =  8;
			mKnob.rotationPointY =  7;
			mKnob.rotationPointZ = 15;
			mBottom = (new ModelRenderer(this, 0, 19)).setTextureSize(64, 64);
			mBottom.addBox(0, 0, 0, 14, 10, 14, 0);
			mBottom.rotationPointX = 1;
			mBottom.rotationPointY = 6;
			mBottom.rotationPointZ = 1;
		}
		
		public void render(double aLidAngle) {
			mKnob.rotateAngleX = mLid.rotateAngleX = (float)aLidAngle;
			mLid.render(0.0625F);
			mKnob.render(0.0625F);
			mBottom.render(0.0625F);
		}
	}
}
