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

package gregtech.tileentity.tools;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.gui.ContainerClient;
import gregapi.gui.ContainerCommon;
import gregapi.gui.Slot_Normal;
import gregapi.item.bumble.IItemBumbleBee;
import gregapi.item.bumble.IItemBumbleBee.Util;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.tileentity.machines.ITileEntityRunningSuccessfully;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBumbliary extends TileEntityBase07Paintable implements IMTE_AddToolTips, ITileEntityRunningSuccessfully {
	public boolean mSky = F, mEndedQueen = F;
	public long mLife = 0, mBreedingCountDown = 1200, mTemperature = DEFAULT_ENVIRONMENT_TEMPERATURE;
	public float mHumidity = 1.0F;
	public ItemStack[] mOffSpring = ZL_IS;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_PROGRESS)) mLife = aNBT.getLong(NBT_PROGRESS);
		
		if (aNBT.hasKey(NBT_INV_OUT)) {
			mOffSpring = new ItemStack[aNBT.getInteger(NBT_INV_OUT)];
			for (int i = 0; i < mOffSpring.length; i++) mOffSpring[i] = ST.load(aNBT, NBT_INV_OUT+"."+i);
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_PROGRESS, mLife);
		if (mOffSpring.length > 0) {
			UT.NBT.setNumber(aNBT, NBT_INV_OUT, mOffSpring.length);
			for (int i = 0; i < mOffSpring.length; i++) ST.save(aNBT, NBT_INV_OUT+"."+i, mOffSpring[i]);
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_ACCESS_SCOOP));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_MEASURE_THERMOMETER));
	}
	
	@Override
	public void onTickFirst2(boolean aIsServerSide) {
		if (aIsServerSide) {
			for (byte tSide : ALL_SIDES_BUT_BOTTOM) if (getRainAtSide(tSide)) {mSky = T; break;}
			mTemperature = WD.envTemp(worldObj, xCoord, yCoord, zCoord);
			mHumidity = getBiome().rainfall;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			mEndedQueen = F;
			if (SERVER_TIME % 1200 == 0) {
				for (byte tSide : ALL_SIDES_BUT_BOTTOM) if (getRainAtSide(tSide)) {mSky = T; break;}
				mTemperature = WD.envTemp(worldObj, xCoord, yCoord, zCoord);
				mHumidity = getBiome().rainfall;
			}
			if (slotHas(SLOT_ROYAL) && slot(SLOT_ROYAL).getItem() instanceof IItemBumbleBee) {
				ItemStack tRoyalStack = slot(SLOT_ROYAL);
				IItemBumbleBee tRoyalItem = (IItemBumbleBee)tRoyalStack.getItem();
				NBTTagCompound tRoyalTag = Util.getBumbleTag(tRoyalStack);
				short tRoyalMeta = ST.meta_(tRoyalStack);
				if (mLife > 0 && tRoyalItem.bumbleType(tRoyalStack) % 5 == 2) {
					mBreedingCountDown = 1200;
					if (checkEnvironment(tRoyalTag)) {
						if (--mLife <= 0) {
							mEndedQueen = T;
							
							for (int tDeadSlot : SLOTS_DEAD) if (slotHas(tDeadSlot) && slot(tDeadSlot).getItem() instanceof IItemBumbleBee) slotKill(tDeadSlot);
							
							ItemStack tDead = tRoyalItem.bumbleKill(tRoyalStack);
							slotKill(SLOT_ROYAL);
							for (int tDeadSlot : SLOTS_DEAD) if (addStackToSlot(tDeadSlot, tDead)) break;
							
							for (int tDroneSlot : SLOTS_DRONE) if (slotHas(tDroneSlot) && slot(tDroneSlot).getItem() instanceof IItemBumbleBee) {
								tDead = ((IItemBumbleBee)slot(tDroneSlot).getItem()).bumbleKill(slotTake(tDroneSlot));
								for (int tDeadSlot : SLOTS_DEAD) if (addStackToSlot(tDeadSlot, tDead)) break;
							}
							
							for (ItemStack tOffSpring : mOffSpring) if (ST.valid(tOffSpring) && tOffSpring.getItem() instanceof IItemBumbleBee) {
								if (checkEnvironment(Util.getBumbleTag(tOffSpring))) {
									if (!slotHas(SLOT_DRONE) || !addStackToSlot(SLOT_DRONE, tOffSpring))
									for (int tDroneSlot : SLOTS_DRONE) if (addStackToSlot(tDroneSlot, tOffSpring)) break;
								} else {
									tDead = ((IItemBumbleBee)tOffSpring.getItem()).bumbleKill(tOffSpring);
									for (int tDeadSlot : SLOTS_DEAD) if (addStackToSlot(tDeadSlot, tDead)) break;
								}
							}
							
							mOffSpring = ZL_IS;
							
							int tPrincessSlot = -1;
							
							for (int tDroneSlot : SLOTS_DRONE) if (slotHas(tDroneSlot) && slot(tDroneSlot).getItem() instanceof IItemBumbleBee) {
								if (((IItemBumbleBee)slot(tDroneSlot).getItem()).bumbleType(slot(tDroneSlot)) % 5 == 1) {
									if (tPrincessSlot < 0) {
										tPrincessSlot = tDroneSlot;
									} else {
										if (Util.getAggressiveness(Util.getBumbleTag(slot(tDroneSlot))) > Util.getAggressiveness(Util.getBumbleTag(slot(tPrincessSlot)))) {
											tPrincessSlot = tDroneSlot;
										}
									}
								}
							}
							if (tPrincessSlot >= 0) {
								slot(SLOT_ROYAL, ST.amount(1, slot(tPrincessSlot)));
								decrStackSize(tPrincessSlot, 1);
							}
						} else {
							if (mLife %  300 == 150 && rng(10000) < Util.getAggressiveness(tRoyalTag)) {
								try {for (EntityLivingBase tEntity : (ArrayList<EntityLivingBase>)worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box(-4, -4, -4, +5, +5, +5))) attackEntity(tEntity);} catch(Throwable e) {e.printStackTrace(ERR);}
							}
							if (mLife % 1200 == 600 && rng(10000) < Util.getWorkForce(tRoyalTag) && checkWork(tRoyalTag)) {
								if (null != tRoyalItem.bumbleCanProduce(worldObj, xCoord, yCoord, zCoord, tRoyalStack, tRoyalMeta, 3)) {
									for (int i = 0, j = tRoyalItem.bumbleProductCount(tRoyalStack, tRoyalMeta); i < j; i++) {
										if (rng(10000) < tRoyalItem.bumbleProductChance(tRoyalStack, tRoyalMeta, i)) {
											if (null != tRoyalItem.bumbleCanProduct(worldObj, xCoord, yCoord, zCoord, tRoyalStack, tRoyalMeta, i)) {
												ItemStack tProduct = tRoyalItem.bumbleProductStack(tRoyalStack, tRoyalMeta, 1, i);
												if (ST.valid(tProduct)) for (int tSlot : SLOTS_COMBS) if (ST.equal(tProduct, slot(tSlot)) && addStackToSlot(tSlot, tProduct)) {
													tProduct = NI;
													break;
												}
												if (ST.valid(tProduct)) for (int tSlot : SLOTS_COMBS) if (addStackToSlot(tSlot, tProduct)) {
													tProduct = NI;
													break;
												}
											}
										}
									}
								}
							}
						}
					} else {
						ItemStack tDead = tRoyalItem.bumbleKill(tRoyalStack);
						slotKill(SLOT_ROYAL);
						for (int tDeadSlot : SLOTS_DEAD) if (addStackToSlot(tDeadSlot, tDead)) break;
					}
				} else {
					mLife = 0;
					mOffSpring = ZL_IS;
					
					if (tRoyalItem.bumbleType(tRoyalStack) % 5 == 1) {
						if (--mBreedingCountDown <= 0) {
							mBreedingCountDown = 600;
							ItemStack tBreedStack = null;
							int tBreedSlot = SLOT_DRONE;
							if (slotHas(SLOT_DRONE) && slot(SLOT_DRONE).getItem() instanceof IItemBumbleBee && ((IItemBumbleBee)slot(SLOT_DRONE).getItem()).bumbleType(slot(SLOT_DRONE)) % 5 == 0) {
								tBreedStack = slot(SLOT_DRONE);
							} else {
								for (int tDroneSlot : SLOTS_DRONE) if (slotHas(tDroneSlot) && slot(tDroneSlot).getItem() instanceof IItemBumbleBee) {
									ItemStack tDroneStack = slot(tDroneSlot);
									if (((IItemBumbleBee)tDroneStack.getItem()).bumbleType(tDroneStack) % 5 == 0) {
										tBreedStack = tDroneStack;
										tBreedSlot = tDroneSlot;
										if (tRoyalItem.bumbleEqual(tRoyalStack, tRoyalMeta, tDroneStack, ST.meta_(tDroneStack))) break;
									}
								}
							}
							if (tBreedStack != null) {
								mBreedingCountDown = 1200;
								IItemBumbleBee tBreedItem = (IItemBumbleBee)tBreedStack.getItem();
								short tBreedMeta = ST.meta_(tBreedStack);
								int tPrincessCount = 1 + rng(5) / 2;
								
								mOffSpring = new ItemStack[(int)(Util.getOffspring(tRoyalTag)) + tPrincessCount];
								
								if (tRoyalItem.bumbleEqual(tRoyalStack, tRoyalMeta, tBreedStack, tBreedMeta)) {
									for (int i = 0; i < mOffSpring.length; i++) {
										mOffSpring[i] = (i < tPrincessCount ? tRoyalItem.bumblePrincess(tRoyalStack, tRoyalMeta) : tRoyalItem.bumbleDrone(tRoyalStack, tRoyalMeta));
										if (ST.valid(mOffSpring[i]) && mOffSpring[i].getItem() instanceof IItemBumbleBee) {
											IItemBumbleBee tOffSpringItem = (IItemBumbleBee)mOffSpring[i].getItem();
											if (rng(10000) < tOffSpringItem.bumbleMutateChance(mOffSpring[i], ST.meta_(mOffSpring[i]))) {
												mOffSpring[i] = tOffSpringItem.bumbleMutate(mOffSpring[i], ST.meta_(mOffSpring[i]), RNGSUS);
											}
										}
									}
								} else {
									for (int i = 0; i < mOffSpring.length; i++) {
										if (i < tPrincessCount) {
											switch(rng(4)) {
											case 0: mOffSpring[i] = tRoyalItem.bumblePrincess(tRoyalStack, tRoyalMeta); break;
											case 1: mOffSpring[i] = tBreedItem.bumblePrincess(tBreedStack, tBreedMeta); break;
											case 2: mOffSpring[i] = tRoyalItem.bumbleCombine(tRoyalStack, tRoyalMeta, tBreedStack, tBreedMeta, (byte)1, RNGSUS); break;
											case 3: mOffSpring[i] = tBreedItem.bumbleCombine(tBreedStack, tBreedMeta, tRoyalStack, tRoyalMeta, (byte)1, RNGSUS); break;
											}
										} else {
											switch(rng(4)) {
											case 0: mOffSpring[i] = tRoyalItem.bumbleDrone(tRoyalStack, tRoyalMeta); break;
											case 1: mOffSpring[i] = tBreedItem.bumbleDrone(tBreedStack, tBreedMeta); break;
											case 2: mOffSpring[i] = tRoyalItem.bumbleCombine(tRoyalStack, tRoyalMeta, tBreedStack, tBreedMeta, (byte)0, RNGSUS); break;
											case 3: mOffSpring[i] = tBreedItem.bumbleCombine(tBreedStack, tBreedMeta, tRoyalStack, tRoyalMeta, (byte)0, RNGSUS); break;
											}
										}
									}
								}
								
								for (ItemStack tOffSpring : mOffSpring) if (ST.valid(tOffSpring)) Util.setBumbleTag(tOffSpring, Util.getBumbleGenes(tRoyalStack, tBreedStack, RNGSUS));
								
								mLife = Util.getLifeSpan(tRoyalTag);
								
								int tLoss = (tBreedSlot == SLOT_DRONE && tBreedStack.stackSize > 1 ? 2 : 1);
								ItemStack tDead = tBreedItem.bumbleKill(ST.amount(tLoss, tBreedStack));
								for (int tDeadSlot : SLOTS_DEAD) if (addStackToSlot(tDeadSlot, tDead)) break;
								decrStackSize(tBreedSlot, tLoss);
								
								slot(SLOT_ROYAL, ST.amount(1, tRoyalItem.bumbleCrown(tRoyalStack)));
								
								for (int tDroneSlot : SLOTS_DRONE) if (slotHas(tDroneSlot) && slot(tDroneSlot).getItem() instanceof IItemBumbleBee && ((IItemBumbleBee)slot(tDroneSlot).getItem()).bumbleType(slot(tDroneSlot)) % 5 != 0) {
									tDead = ((IItemBumbleBee)slot(tDroneSlot).getItem()).bumbleKill(slotTake(tDroneSlot));
									for (int tDeadSlot : SLOTS_DEAD) if (addStackToSlot(tDeadSlot, tDead)) break;
								}
							}
						}
					} else {
						mBreedingCountDown = 1200;
					}
				}
			} else {
				mLife = 0;
				mOffSpring = ZL_IS;
				mBreedingCountDown = 1200;
			}
		}
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (SIDES_TOP[aSide]) {
			if (aPlayer != null && isServerSide()) {
				if (UT.Entities.isCreative(aPlayer)) {
					openGUI(aPlayer, 1);
					return T;
				}
				mBreedingCountDown = 1200;
				attackEntity(aPlayer);
				openGUI(aPlayer, 0);
			}
			return T;
		}
		return F;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_thermometer)) {
			if (aChatReturn != null) aChatReturn.add("Temperature: " + mTemperature + "K - Humidity: " + mHumidity);
			return 1000;
		}
		if (aTool.equals(TOOL_scoop)) {
			if (SIDES_TOP[aSide]) {
				mBreedingCountDown = 1200;
				if (aPlayer instanceof EntityLivingBase) attackEntity((EntityLivingBase)aPlayer);
				if (aPlayer instanceof EntityPlayer) openGUI((EntityPlayer)aPlayer, 1);
				return 10000;
			}
			return 0;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tools/bumbliary/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tools/bumbliary/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tools/bumbliary/colored/sides")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tools/bumbliary/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tools/bumbliary/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tools/bumbliary/overlay/sides")
	};
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;}
	
	@SideOnly(Side.CLIENT)
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return aGUIID == 1 ? new MultiTileEntityGUIClientBumbliaryScoop(aPlayer.inventory, this, aGUIID) : new MultiTileEntityGUIClientBumbliary(aPlayer.inventory, this, aGUIID);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return aGUIID == 1 ? new MultiTileEntityGUICommonBumbliaryScoop(aPlayer.inventory, this, aGUIID) : new MultiTileEntityGUICommonBumbliary(aPlayer.inventory, this, aGUIID);}
	
	public static final int SLOT_ROYAL = 13, SLOT_DRONE = 22
	, SLOTS_COMBS[] = {0, 1, 2, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26}
	, SLOTS_DRONE[] = {3, 4, 5, 12, 14, 21, 23}
	, SLOTS_DEAD[] = {27, 28, 29, 30, 31, 32, 33, 34, 35}
	;
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[36];}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return SLOTS_DEAD;}
	@Override public int getInventoryStackLimitGUI(int aSlot) {return aSlot == SLOT_ROYAL ? 1 : 64;}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return aSlot >= 27;}
	@Override public boolean allowCovers(byte aSide) {return SIDES_BOTTOM_HORIZONTAL[aSide];}
	@Override public boolean canDrop(int aSlot) {return T;}
	@Override public boolean breakDrop(int aSlot) {if (mLife > 0 && slot(aSlot).getItem() instanceof IItemBumbleBee) slot(aSlot, ((IItemBumbleBee)slot(aSlot).getItem()).bumbleKill(slot(aSlot))); return T;}
	
	@Override public boolean getStateRunningActively()      {return mLife > 0;}
	@Override public boolean getStateRunningPassively()     {return mLife > 0 || mBreedingCountDown < 1200;}
	@Override public boolean getStateRunningPossible()      {return mLife > 0 || mBreedingCountDown < 1200;}
	@Override public boolean getStateRunningSuccessfully()  {return mEndedQueen;}
	
	@Override
	public boolean isItemValidForSlotGUI(int aSlot, ItemStack aStack) {
		if (ST.invalid(aStack) || !(aStack.getItem() instanceof IItemBumbleBee)) return F;
		switch(aSlot) {
		case SLOT_DRONE: return ((IItemBumbleBee)aStack.getItem()).bumbleType(aStack) % 5 == 0;
		case SLOT_ROYAL: return ((IItemBumbleBee)aStack.getItem()).bumbleType(aStack) % 5 == 1;
		default: return F;
		}
	}
	
	@Override
	public boolean canTakeOutOfSlotGUI(int aSlot) {
		return aSlot != SLOT_ROYAL || !slotHas(aSlot) || !(slot(aSlot).getItem() instanceof IItemBumbleBee) || ((IItemBumbleBee)slot(aSlot).getItem()).bumbleType(slot(aSlot)) % 5 != 2;
	}
	
	private boolean attackEntity(EntityLivingBase aEntity) {
		return slotHas(SLOT_ROYAL) && slot(SLOT_ROYAL).getItem() instanceof IItemBumbleBee && ((IItemBumbleBee)slot(SLOT_ROYAL).getItem()).bumbleType(slot(SLOT_ROYAL)) % 5 == 2 && ((IItemBumbleBee)slot(SLOT_ROYAL).getItem()).bumbleAttack(slot(SLOT_ROYAL), ST.meta_(slot(SLOT_ROYAL)), aEntity);
	}
	
	private boolean checkEnvironment(NBTTagCompound aBumbleTag) {
		return UT.Code.inside(Util.getTemperatureMin(aBumbleTag), Util.getTemperatureMax(aBumbleTag), mTemperature) && UT.Code.inside_(Util.getHumidityMin(aBumbleTag), Util.getHumidityMax(aBumbleTag), mHumidity) && (mSky ? Util.getOutsideActive(aBumbleTag) : Util.getInsideActive(aBumbleTag));
	}
	
	private boolean checkWork(NBTTagCompound aBumbleTag) {
		if (mSky) {
			if (worldObj.isThundering() && !Util.getStormproof(aBumbleTag)) return F;
			if (worldObj.isRaining() && mHumidity > 0 && !Util.getRainproof(aBumbleTag)) return F;
		}
		return worldObj.isDaytime() ? Util.getDayActive(aBumbleTag) : Util.getNightActive(aBumbleTag);
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.bumbliary";}
	
	public class MultiTileEntityGUICommonBumbliary extends ContainerCommon {
		public MultiTileEntityGUICommonBumbliary(InventoryPlayer aInventoryPlayer, MultiTileEntityBumbliary aTileEntity, int aGUIID) {
			super(aInventoryPlayer, aTileEntity, aGUIID);
		}
		
		@Override
		public int addSlots(InventoryPlayer aInventoryPlayer) {
			addSlotToContainer(new Slot_Normal(mTileEntity,  0,   8,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  1,  26,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  2,  44,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  3,  62,  8).setCanPut(F).setCanTake(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  4,  80,  8).setCanPut(F).setCanTake(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  5,  98,  8).setCanPut(F).setCanTake(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  6, 116,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  7, 134,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  8, 152,  8).setCanPut(F));
			
			addSlotToContainer(new Slot_Normal(mTileEntity,  9,   8, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 10,  26, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 11,  44, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 12,  62, 26).setCanPut(F).setCanTake(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 13,  80, 26).setCanTake(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 14,  98, 26).setCanPut(F).setCanTake(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 15, 116, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 16, 134, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 17, 152, 26).setCanPut(F));
			
			addSlotToContainer(new Slot_Normal(mTileEntity, 18,   8, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 19,  26, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 20,  44, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 21,  62, 44).setCanPut(F).setCanTake(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 22,  80, 44).setCanTake(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 23,  98, 44).setCanPut(F).setCanTake(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 24, 116, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 25, 134, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 26, 152, 44).setCanPut(F));
			
			addSlotToContainer(new Slot_Normal(mTileEntity, 27,   8, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 28,  26, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 29,  44, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 30,  62, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 31,  80, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 32,  98, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 33, 116, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 34, 134, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 35, 152, 62).setCanPut(F));
			
			return super.addSlots(aInventoryPlayer);
		}
		
		@Override public int getSlotCount() {return 36;}
		@Override public int getShiftClickSlotCount() {return 36;}
	}

	public class MultiTileEntityGUICommonBumbliaryScoop extends ContainerCommon {
		public MultiTileEntityGUICommonBumbliaryScoop(InventoryPlayer aInventoryPlayer, MultiTileEntityBumbliary aTileEntity, int aGUIID) {
			super(aInventoryPlayer, aTileEntity, aGUIID);
		}
		
		@Override
		public int addSlots(InventoryPlayer aInventoryPlayer) {
			addSlotToContainer(new Slot_Normal(mTileEntity,  0,   8,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  1,  26,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  2,  44,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  3,  62,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  4,  80,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  5,  98,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  6, 116,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  7, 134,  8).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity,  8, 152,  8).setCanPut(F));
			
			addSlotToContainer(new Slot_Normal(mTileEntity,  9,   8, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 10,  26, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 11,  44, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 12,  62, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 13,  80, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, 14,  98, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 15, 116, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 16, 134, 26).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 17, 152, 26).setCanPut(F));
			
			addSlotToContainer(new Slot_Normal(mTileEntity, 18,   8, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 19,  26, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 20,  44, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 21,  62, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 22,  80, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, 23,  98, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 24, 116, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 25, 134, 44).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 26, 152, 44).setCanPut(F));
			
			addSlotToContainer(new Slot_Normal(mTileEntity, 27,   8, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 28,  26, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 29,  44, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 30,  62, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 31,  80, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 32,  98, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 33, 116, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 34, 134, 62).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 35, 152, 62).setCanPut(F));
			
			return super.addSlots(aInventoryPlayer);
		}
		
		@Override public int getSlotCount() {return 36;}
		@Override public int getShiftClickSlotCount() {return 36;}
	}
	
	@SideOnly(Side.CLIENT)
	public class MultiTileEntityGUIClientBumbliary extends ContainerClient {
		public MultiTileEntityGUIClientBumbliary(InventoryPlayer aInventoryPlayer, MultiTileEntityBumbliary aTileEntity, int aGUIID) {
			super(new MultiTileEntityGUICommonBumbliary(aInventoryPlayer, aTileEntity, aGUIID), RES_PATH_GUI + "machines/Bumbliary.png");
		}
	}
	
	@SideOnly(Side.CLIENT)
	public class MultiTileEntityGUIClientBumbliaryScoop extends ContainerClient {
		public MultiTileEntityGUIClientBumbliaryScoop(InventoryPlayer aInventoryPlayer, MultiTileEntityBumbliary aTileEntity, int aGUIID) {
			super(new MultiTileEntityGUICommonBumbliaryScoop(aInventoryPlayer, aTileEntity, aGUIID), RES_PATH_GUI + "machines/Bumbliary.png");
		}
	}
}
