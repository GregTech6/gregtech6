/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregtech.tileentity.energy.transformers;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetOreDictItemData;
import gregapi.code.TagData;
import gregapi.data.CS.SFX;
import gregapi.data.CS.ToolsGT;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGearBox extends TileEntityBase07Paintable implements ITileEntityEnergy, ITileEntityRunningActively, ITileEntitySwitchableOnOff, IMTE_GetOreDictItemData, IMTE_AddToolTips {
	public boolean mJammed = F, mUsedGear = F, mGearsWork = F, mIgnorePower = F;
	public long mMaxThroughPut = 64, mCurrentSpeed = 0, mCurrentPower = 0, mTransferredLast = 0;
	public short mAxleGear = 0, mRotationData = 0, oRotationData = 0;
	public byte mInputtedSides = 0, mOrder = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STOPPED)) mJammed = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ACTIVE)) mIgnorePower = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_CONNECTION)) mAxleGear = UT.Code.unsignB(aNBT.getByte(NBT_CONNECTION));
		if (aNBT.hasKey(NBT_INPUT)) mMaxThroughPut = aNBT.getLong(NBT_INPUT);
		mGearsWork = checkGears();
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mJammed);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mIgnorePower);
		aNBT.setByte(NBT_CONNECTION, (byte)mAxleGear);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		super.writeItemNBT2(aNBT);
		aNBT.setByte(NBT_CONNECTION, (byte)mAxleGear);
		return aNBT;
	}
	
	static {
		LH.add("gt.tooltip.gearbox.custom.1", "Gears are interlocked wrongly!");
		LH.add("gt.tooltip.gearbox.custom.2", "Use Wrench to mount Gears from your Inventory");
		LH.add("gt.tooltip.gearbox.custom.3", "Use Monkeywrench to change Axle Direction");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (!mGearsWork)
		aList.add(Chat.BLINKING_RED + LH.get("gt.tooltip.gearbox.custom.1"));
		aList.add(Chat.CYAN + LH.get(LH.AXLE_STATS_SPEED) + mMaxThroughPut + " " + TD.Energy.RU.getLocalisedNameShort());
		aList.add(Chat.DGRAY + LH.get("gt.tooltip.gearbox.custom.2"));
		aList.add(Chat.DGRAY + LH.get("gt.tooltip.gearbox.custom.3"));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SOFT_HAMMER));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return 0;
		if (aTool.equals(TOOL_wrench)) {
			if (SIDES_INVALID[aSide]) return 0;
			byte tSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			if (FACE_CONNECTED[tSide][mAxleGear & 63]) {
				mAxleGear &= ~B[tSide];
				ST.place(getWorld(), getOffset(tSide, 1), OP.gearGt.mat(mMaterial, 1));
				mGearsWork = checkGears();
				updateClientData();
				causeBlockUpdate();
				return 10000;
			}
			if (UT.Entities.hasInfiniteItems(aPlayer)) {
				mAxleGear |= B[tSide];
				mGearsWork = checkGears();
				updateClientData();
				causeBlockUpdate();
				return 10000;
			}
			if (aPlayerInventory != null) for (int i = 0, j = aPlayerInventory.getSizeInventory(); i < j; i++) {
				OreDictItemData tData = OM.data(aPlayerInventory.getStackInSlot(i));
				if (tData != null && tData.mPrefix == OP.gearGt && (tData.mMaterial.mMaterial == mMaterial || mMaterial.mToThis.contains(tData.mMaterial.mMaterial))) {
					aPlayerInventory.decrStackSize(i, 1);
					mAxleGear |= B[tSide];
					mGearsWork = checkGears();
					updateClientData();
					causeBlockUpdate();
					return 10000;
				}
			}
			UT.Entities.chat(aPlayer, "You dont have a Gear of the same Material in your Inventory!");
			return 0;
		}
		if (aTool.equals(TOOL_monkeywrench)) {
			if (SIDES_AXIS_X[aSide]) if (((mAxleGear >>> 6) & 3) != 1) mAxleGear = (byte)((mAxleGear & 63) | (1 << 6)); else mAxleGear &= 63;
			if (SIDES_AXIS_Y[aSide]) if (((mAxleGear >>> 6) & 3) != 2) mAxleGear = (byte)((mAxleGear & 63) | (2 << 6)); else mAxleGear &= 63;
			if (SIDES_AXIS_Z[aSide]) if (((mAxleGear >>> 6) & 3) != 3) mAxleGear = (byte)((mAxleGear & 63) | (3 << 6)); else mAxleGear &= 63;
			mGearsWork = checkGears();
			updateClientData();
			causeBlockUpdate();
			return 10000;
		}
		if (aTool.equals(TOOL_softhammer)) {
			mJammed = !mJammed;
			mGearsWork = checkGears();
			updateClientData();
			causeBlockUpdate();
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			mGearsWork = checkGears();
			if (aChatReturn != null) aChatReturn.add(mGearsWork ? "Gears interlocked properly." : "Gears interlocked improperly!");
			return 1;
		}
		return 0;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			mTransferredLast = Math.abs(mCurrentPower * mCurrentSpeed);
			if (mUsedGear && mCurrentPower > 0) {
				boolean temp = T;
				while (temp) {
					temp = F;
					for (byte i = 0; i < 6; i++) {
						byte tSide = (byte)((mOrder+i)%6);
						if (!FACE_CONNECTED[tSide][mInputtedSides] && (FACE_CONNECTED[tSide][mAxleGear & 63] || (AXIS_XYZ[(mAxleGear >>> 6) & 3][tSide] && FACE_CONNECTED[OPPOSITES[tSide]][mAxleGear & 63]))) {
							if (ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.RU, (mRotationData & B[tSide]) != 0 ? +mCurrentSpeed : -mCurrentSpeed, 1, this, getAdjacentTileEntity(tSide)) > 0) {
								if (--mCurrentPower <= 0) {temp = F; break;}
								temp = T;
							}
						}
					}
					if (++mOrder >= 6) mOrder = 0;
				}
			}
			mTransferredLast -= Math.abs(mCurrentPower * mCurrentSpeed);
			if (!mUsedGear) mRotationData = 0;
			mUsedGear = F;
		}
	}
	
	public byte getRotations(byte aSide, boolean aNegative) {
		if (!mGearsWork) return 0;
		byte rRotationData = (byte)(aNegative ? B[aSide] : 0);
		if (AXIS_XYZ[(mAxleGear >>> 6) & 3][aSide] && FACE_CONNECTED[OPPOSITES[aSide]][mAxleGear & 63]) {
			rRotationData |= (aNegative ? B[6] : B[6]|B[OPPOSITES[aSide]]);
			for (byte tSide : ALL_SIDES_VALID_BUT_AXIS[aSide]) if (FACE_CONNECTED[tSide][mAxleGear & 63]) rRotationData |= (aNegative ? B[tSide] : 0);
		}
		if (FACE_CONNECTED[aSide][mAxleGear & 63]) {
			rRotationData |= (aNegative && FACE_CONNECTED[OPPOSITES[aSide]][mAxleGear & 63] && FACE_CONNECTION_COUNT[mAxleGear & 63] > 2 ? B[6]|B[OPPOSITES[aSide]] : B[6]);
			for (byte tSide : ALL_SIDES_VALID_BUT_AXIS[aSide]) if (FACE_CONNECTED[tSide][mAxleGear & 63]) rRotationData |= (aNegative ? 0 : B[tSide]);
		}
		return rRotationData;
	}
	
	public boolean checkGears() {
		switch(FACE_CONNECTION_COUNT[mAxleGear & 63]) {
		case 0:
			// Just prevents the Error Tooltip from popping up.
			return T;
		case 1:
			// Always Rotates when connected, nothing stops it from doing so.
			return T;
		case 2:
			// Corner Gears will always work.
			if ((mAxleGear & 48) != 48 && (mAxleGear & 3) != 3 && (mAxleGear & 12) != 12) return T;
			// But also Rotate when both Gears are on the same Axle, as dumb and useless as that is, it is what would happen.
			switch((mAxleGear >>> 6) & 3) {
			case  1: return (mAxleGear & 48) != 0;
			case  2: return (mAxleGear &  3) != 0;
			case  3: return (mAxleGear & 12) != 0;
			}
			return F;
		case 3: case 4:
			// Make sure the Axle wont screw the Setup over by forming a D or Omikron Shape.
			switch((mAxleGear >>> 6) & 3) {
			case  1: if ((mAxleGear & 48) == 48) return F; break;
			case  2: if ((mAxleGear &  3) ==  3) return F; break;
			case  3: if ((mAxleGear & 12) == 12) return F; break;
			}
			// Triangle interlocked Gears do ofcourse not work!
			byte tAxisUsed = 0;
			if ((mAxleGear & 48) != 0) tAxisUsed++;
			if ((mAxleGear &  3) != 0) tAxisUsed++;
			if ((mAxleGear & 12) != 0) tAxisUsed++;
			return tAxisUsed < 3;
		}
		// 5 Gears never work, same as 6 Gears
		return F;
	}
	
	@Override public boolean onTickCheck(long aTimer) {return mRotationData != oRotationData || super.onTickCheck(aTimer);}
	@Override public void onTickResetChecks(long aTimer, boolean aIsServerSide) {super.onTickResetChecks(aTimer, aIsServerSide); oRotationData = mRotationData;}
	@Override public void setVisualData(byte aData) {mRotationData = aData;}
	@Override public byte getVisualData() {return (byte)mRotationData;}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return aSendAll ? getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getVisualData(), (byte)mAxleGear) : getClientDataPacketByte(aSendAll, getVisualData());
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mAxleGear = UT.Code.unsignB(aData[4]);
		return super.receiveDataByteArray(aData, aNetworkHandler);
	}
	
	public ITexture mTextureGearA, mTextureAxleGearA, mTextureGearB, mTextureAxleGearB, mTexture, mTextureAxle;
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTexture          = BlockTextureDefault.get(Textures.BlockIcons.GEARBOX     , mRGBa);
		mTextureAxle      = BlockTextureDefault.get(Textures.BlockIcons.GEARBOX_AXLE, mRGBa);
		mTextureGearA     = BlockTextureMulti.get(mTexture    , BlockTextureDefault.get((mRotationData & B[6]) == 0 ? Textures.BlockIcons.GEAR : Textures.BlockIcons.GEAR_CLOCKWISE       , mRGBa));
		mTextureAxleGearA = BlockTextureMulti.get(mTextureAxle, BlockTextureDefault.get((mRotationData & B[6]) == 0 ? Textures.BlockIcons.GEAR : Textures.BlockIcons.GEAR_CLOCKWISE       , mRGBa));
		mTextureGearB     = BlockTextureMulti.get(mTexture    , BlockTextureDefault.get((mRotationData & B[6]) == 0 ? Textures.BlockIcons.GEAR : Textures.BlockIcons.GEAR_COUNTERCLOCKWISE, mRGBa));
		mTextureAxleGearB = BlockTextureMulti.get(mTextureAxle, BlockTextureDefault.get((mRotationData & B[6]) == 0 ? Textures.BlockIcons.GEAR : Textures.BlockIcons.GEAR_COUNTERCLOCKWISE, mRGBa));
		return 1;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? FACE_CONNECTED[aSide][mAxleGear & 63] ? FACE_CONNECTED[aSide][mRotationData & 63] ? AXIS_XYZ[(mAxleGear >>> 6) & 3][aSide]?mTextureAxleGearA:mTextureGearA : AXIS_XYZ[(mAxleGear >>> 6) & 3][aSide]?mTextureAxleGearB:mTextureGearB : AXIS_XYZ[(mAxleGear >>> 6) & 3][aSide]?mTextureAxle:mTexture : null;
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSpeed, long aPower, boolean aDoInject) {
		if (!isEnergyType(aEnergyType, aSide, F)) return 0;
		if (!aDoInject) return mIgnorePower ? 0 : aPower;
		
		if (Math.abs(aSpeed) > mMaxThroughPut) {
			if (mTimer < 10) return aPower;
			UT.Sounds.send(SFX.MC_BREAK, this);
			byte tCount = FACE_CONNECTION_COUNT[mAxleGear & 63];
			if (tCount > 0) {
				ST.drop(getWorld(), getCoords(), OP.scrapGt.mat(mMaterial, 9+rng(27)));
				if (tCount > 1)
				ST.drop(getWorld(), getCoords(), OP.gearGt.mat(mMaterial, tCount-1));
			}
			mAxleGear = 0;
			updateClientData();
			mGearsWork = checkGears();
			return aPower;
		}
		
		mInputtedSides |= B[aSide];
		
		if (mUsedGear) {
			byte tRotationData = getRotations(aSide, aSpeed < 0);
			if (tRotationData == 0) {
				// Why even bother calculating that, if it goes through the Axle anyways and the Axle has no Gears on it.
				if (AXIS_XYZ[(mAxleGear >>> 6) & 3][aSide]) return ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.RU, aSpeed, aPower, this, getAdjacentTileEntity(OPPOSITES[aSide]));
			}
			if (tRotationData != mRotationData) {
				// Gears are jamming!
				mRotationData = 0;
				mJammed = T;
				return aPower;
			}
			if (Math.abs(aSpeed) != mCurrentSpeed) {
				// Gears are not accepting the Power!
				return aPower;
			}
			if (mIgnorePower) return 0;
			mCurrentPower += aPower;
			return aPower;
		}
		if ((mRotationData = getRotations(aSide, aSpeed < 0)) != 0) {
			mCurrentSpeed = Math.abs(aSpeed);
			mIgnorePower = (mCurrentPower > 0);
			mUsedGear = T;
			if (mIgnorePower) return 0;
			mCurrentPower = aPower;
			return aPower;
		}
		return 0;
	}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return TD.Energy.RU == aEnergyType;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mJammed) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return                               super.isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeOutputMin            (TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mMaxThroughPut/2;}
	@Override public long getEnergySizeOutputMax            (TagData aEnergyType, byte aSide) {return mMaxThroughPut;}
	@Override public long getEnergySizeInputMin             (TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mMaxThroughPut/2;}
	@Override public long getEnergySizeInputMax             (TagData aEnergyType, byte aSide) {return mMaxThroughPut;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.RU.AS_LIST;}
	
	@Override public boolean isUsingWrenchingOverlay(ItemStack aStack, byte aSide) {return super.isUsingWrenchingOverlay(aStack, aSide) || ToolsGT.contains(TOOL_wrench, aStack) || ToolsGT.contains(TOOL_monkeywrench, aStack);}
	@Override public boolean isConnectedWrenchingOverlay(ItemStack aStack, byte aSide) {return FACE_CONNECTED[aSide][mAxleGear & 63];}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override public boolean getStateRunningPossible () {return mGearsWork;}
	@Override public boolean getStateRunningPassively() {return (mRotationData & B[6]) != 0 || (oRotationData & B[6]) != 0;}
	@Override public boolean getStateRunningActively () {return (mRotationData & B[6]) != 0 || (oRotationData & B[6]) != 0;}
	@Override public boolean setStateOnOff(boolean aOnOff) {mJammed = !aOnOff; return !mJammed;}
	@Override public boolean getStateOnOff() {return !mJammed;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.gearbox.custom";}
	
	@Override
	public List<OreDictItemData> getOreDictItemData(List<OreDictItemData> aList) {
		if (FACE_CONNECTION_COUNT[mAxleGear & 63] > 0) aList.add(new OreDictItemData(mMaterial, OP.gearGt.mAmount * FACE_CONNECTION_COUNT[mAxleGear & 63]));
		return aList;
	}
}
