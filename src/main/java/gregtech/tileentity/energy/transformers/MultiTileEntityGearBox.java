/**
 * Copyright (c) 2021 GregTech-6 Team
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGearBox extends TileEntityBase07Paintable implements ITileEntityEnergy, ITileEntityRunningActively, ITileEntitySwitchableOnOff, IMTE_GetOreDictItemData, IMTE_AddToolTips {
	public boolean mJammed = F, mUsedGear = F, mGearsWork = F;
	public long mMaxThroughPut = 64, mCurrentSpeed = 0, mCurrentPower = 0, mTransferredLast = 0;
	public short mAxleGear = 0;
	public byte mInputtedSides = 0, mOrder = 0, mRotationData = 0, oRotationData = 0, mIgnorePower = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STOPPED)) mJammed = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_CONNECTION)) mAxleGear = UT.Code.unsignB(aNBT.getByte(NBT_CONNECTION));
		if (aNBT.hasKey(NBT_INPUT)) mMaxThroughPut = aNBT.getLong(NBT_INPUT);
		mGearsWork = checkGears();
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mJammed);
		aNBT.setByte(NBT_CONNECTION, (byte)mAxleGear);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		aNBT.setByte(NBT_CONNECTION, (byte)mAxleGear);
		return super.writeItemNBT2(aNBT);
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
				ItemStack tGear = OP.gearGt.mat(mMaterial, 1);
				if (!(aPlayer instanceof EntityPlayer) || !UT.Inventories.addStackToPlayerInventory((EntityPlayer)aPlayer, tGear)) ST.place(getWorld(), getOffset(tSide, 1), tGear);
				mJammed = F;
				mGearsWork = checkGears();
				updateClientData();
				causeBlockUpdate();
				return 10000;
			}
			if (UT.Entities.hasInfiniteItems(aPlayer)) {
				mAxleGear |= B[tSide];
				mJammed = F;
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
					mJammed = F;
					mGearsWork = checkGears();
					updateClientData();
					causeBlockUpdate();
					return 10000;
				}
			}
			if (aChatReturn != null) aChatReturn.add("You dont have a Gear of the corresponding Material in your Inventory!");
			return 0;
		}
		if (aTool.equals(TOOL_monkeywrench)) {
			if (SIDES_INVALID[aSide]) return 0;
			byte tSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			if (SIDES_AXIS_X[tSide]) if (((mAxleGear >>> 6) & 3) != 1) mAxleGear = (byte)((mAxleGear & 63) | (1 << 6)); else mAxleGear &= 63;
			if (SIDES_AXIS_Y[tSide]) if (((mAxleGear >>> 6) & 3) != 2) mAxleGear = (byte)((mAxleGear & 63) | (2 << 6)); else mAxleGear &= 63;
			if (SIDES_AXIS_Z[tSide]) if (((mAxleGear >>> 6) & 3) != 3) mAxleGear = (byte)((mAxleGear & 63) | (3 << 6)); else mAxleGear &= 63;
			mJammed = F;
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
			if (aChatReturn != null) aChatReturn.add(mGearsWork ? mJammed ? "Gears interlocked properly, but they are jammed!" : "Gears interlocked properly." : "Gears interlocked improperly!");
			return 1;
		}
		return 0;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mJammed || !mGearsWork) mCurrentPower = 0;
			
			mTransferredLast = Math.abs(mCurrentPower * mCurrentSpeed);
			
			if (mUsedGear && mCurrentPower > 0) {
				boolean temp = T;
				while (temp) {
					temp = F;
					// Due to Geometry, there can only ever be up to 3 Output Sides at once.
					long tUsable = Math.max(1, mCurrentPower/3);
					for (byte i = 0; i < 6; i++) {
						byte tSide = (byte)((mOrder+i)%6);
						if (!FACE_CONNECTED[tSide][mInputtedSides]) {
							if (FACE_CONNECTED[tSide][mAxleGear & 63]) {
								long tUsed = ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.RU, (mRotationData & B[          tSide ]) != 0 ? +mCurrentSpeed : -mCurrentSpeed, tUsable, this, getAdjacentTileEntity(tSide));
								if (tUsed > 0) {
									mCurrentPower -= tUsed;
									if (mCurrentPower <= 0) {temp = F; break;}
									temp = T;
								}
							} else if (AXIS_XYZ[(mAxleGear >>> 6) & 3][tSide] && FACE_CONNECTED[OPPOSITES[tSide]][mAxleGear & 63]) {
								long tUsed = ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.RU, (mRotationData & B[OPPOSITES[tSide]]) == 0 ? +mCurrentSpeed : -mCurrentSpeed, tUsable, this, getAdjacentTileEntity(tSide));
								if (tUsed > 0) {
									mCurrentPower -= tUsed;
									if (mCurrentPower <= 0) {temp = F; break;}
									temp = T;
								}
							}
						}
					}
					if (++mOrder >= 6) mOrder = 0;
				}
			}
			mTransferredLast -= Math.abs(mCurrentPower * mCurrentSpeed);
			if (!mUsedGear) mRotationData &= ~B[6];
			mInputtedSides = 0;
			mUsedGear = F;
		}
	}
	
	public byte getRotations(byte aSide, boolean aNegative) {
		// Nothing is interlocked properly so no functionality here.
		if (!mGearsWork) return 0;
		// The Gear on the Input Side needs the correct direction set.
		byte rRotationData = (byte)(aNegative ? B[aSide] : 0);
		// There is an Axle along this Axis.
		if (AXIS_XYZ[(mAxleGear >>> 6) & 3][aSide]) {
			// Make whatever is on the other side of the Axle rotate the same direction the Axle does.
			if (!aNegative) rRotationData |= B[OPPOSITES[aSide]];
			// Gear on Input Side.
			if (FACE_CONNECTED[          aSide ][mAxleGear & 63]) {
				// All adjacent Gears need to rotate the opposite direction of this Gear.
				if (!aNegative) for (byte tSide : ALL_SIDES_VALID_BUT_AXIS[aSide]) if (FACE_CONNECTED[tSide][mAxleGear & 63]) rRotationData |= B[tSide];
				// Clear unused Values to make sure that it can be compared properly.
				return (byte)((rRotationData & mAxleGear & 63) | B[6]);
			}
			// Gear on Throughput Side.
			if (FACE_CONNECTED[OPPOSITES[aSide]][mAxleGear & 63]) {
				// Make adjacent Gears rotate according to the Gear on the opposite Side.
				if ( aNegative) for (byte tSide : ALL_SIDES_VALID_BUT_AXIS[aSide]) if (FACE_CONNECTED[tSide][mAxleGear & 63]) rRotationData |= B[tSide];
				// Clear unused Values to make sure that it can be compared properly.
				return (byte)((rRotationData & mAxleGear & 63) | B[6]);
			}
			// There is no Gears on that Axle, this should actually not get this far, because the Passthrough takes over, before this gets called.
			ERR.print("Something went wrong with the free Axle inside the Gearbox at " + getCoords() + " receiving power from Side: " + aSide);
			// Returning the current Rotation Data to make sure nothing breaks too badly.
			return mRotationData;
		}
		// Axle not involved.
		if (FACE_CONNECTED[aSide][mAxleGear & 63]) {
			// The Gear on opposite Sides of the Gearbox rotates the opposite direction.
			if ( aNegative) rRotationData |= B[OPPOSITES[aSide]];
			// All adjacent Gears need to rotate the opposite direction of this Gear.
			if (!aNegative) for (byte tSide : ALL_SIDES_VALID_BUT_AXIS[aSide]) if (FACE_CONNECTED[tSide][mAxleGear & 63]) rRotationData |= B[tSide];
			// Clear unused Values to make sure that it can be compared properly.
			return (byte)((rRotationData & mAxleGear & 63) | B[6]);
		}
		// This Facing is not even connected so nothing to do here. This should not get this far either!
		ERR.print("Something went wrong with the Gearbox at " + getCoords() + " receiving power from Side: " + aSide + " even though there is neither a Gear nor an Axle at that Side");
		// Returning the current Rotation Data to make sure nothing breaks too badly.
		return mRotationData;
	}
	
	/** This is only ever called whenever the Axle or Gears change or when the Gearbox TileEntity is loaded. */
	public boolean checkGears() {
		// Just in case something broke during setting up the Gearbox.
		mIgnorePower = 0;
		// Current Power and Speed need to be 0.
		mCurrentSpeed = mCurrentPower = 0;
		// Check if the Gearbox actually works properly.
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
		// 5 Gears never work, same for 6 Gears
		return F;
	}
	
	@Override public boolean onTickCheck(long aTimer) {return mRotationData != oRotationData || super.onTickCheck(aTimer);}
	@Override public void onTickResetChecks(long aTimer, boolean aIsServerSide) {super.onTickResetChecks(aTimer, aIsServerSide); oRotationData = mRotationData;}
	@Override public void setVisualData(byte aData) {mRotationData = aData;}
	@Override public byte getVisualData() {return mRotationData;}
	
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
		if (!AXIS_XYZ[(mAxleGear >>> 6) & 3][aSide] && !FACE_CONNECTED[aSide][mAxleGear & 63]) return 0;
		if (!aDoInject) return mIgnorePower == 0 ? aPower : 0;
		
		// Received Input from this Side.
		mInputtedSides |= B[aSide];
		
		long tSpeed = Math.abs(aSpeed);
		
		if (tSpeed > mMaxThroughPut) {
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
		
		// Free Axle means it is always a Passthrough.
		if (AXIS_XYZ[(mAxleGear >>> 6) & 3][aSide] && !FACE_CONNECTED[aSide][mAxleGear & 63] && !FACE_CONNECTED[OPPOSITES[aSide]][mAxleGear & 63]) {
			return ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.RU, aSpeed, aPower, this, getAdjacentTileEntity(OPPOSITES[aSide]));
		}
		
		// Just void all power if the Gearbox is not set up properly.
		if (!mGearsWork) return aPower;
		
		// There already has been at least one Input during this Tick. Add more Power.
		if (mUsedGear) {
			byte tRotationData = getRotations(aSide, aSpeed < 0);
			if (tRotationData != mRotationData) {
				// Gears are jamming!
				UT.Sounds.send(SFX.MC_BREAK, this);
				mRotationData = 0;
				mJammed = T;
				return aPower;
			}
			// If ignoring further Inputs, keep the old values.
			if (mIgnorePower != 0) return 0;
			// Just take the lowest Speed available. Gives a different Type of Loss Mechanic that somewhat makes sense.
			mCurrentSpeed = Math.min(tSpeed, mCurrentSpeed);
			mCurrentPower += aPower;
			return aPower;
		}
		// There was no Input during this Tick yet.
		if ((mRotationData = getRotations(aSide, aSpeed < 0)) != 0) {
			mUsedGear = T;
			// Still had leftover Power from last time. Start ignoring Input in order to not waste Power.
			if (mCurrentPower > 0) mIgnorePower++; else mIgnorePower = 0;
			// If ignoring further Inputs, keep the old values.
			if (mIgnorePower != 0) return 0;
			// Set Maximum Speed and current Power.
			mCurrentSpeed = tSpeed;
			mCurrentPower = aPower;
			return aPower;
		}
		return 0;
	}
	
	@Override public boolean isEnergyType               (TagData aEnergyType, byte aSide, boolean aEmitting) {return TD.Energy.RU == aEnergyType;}
	@Override public boolean isEnergyAcceptingFrom      (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mJammed) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public boolean isEnergyEmittingTo         (TagData aEnergyType, byte aSide, boolean aTheoretical) {return                               super.isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeOutputMin        (TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mMaxThroughPut/2;}
	@Override public long getEnergySizeOutputMax        (TagData aEnergyType, byte aSide) {return mMaxThroughPut;}
	@Override public long getEnergySizeInputMin         (TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeInputRecommended (TagData aEnergyType, byte aSide) {return mMaxThroughPut/2;}
	@Override public long getEnergySizeInputMax         (TagData aEnergyType, byte aSide) {return mMaxThroughPut;}
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
